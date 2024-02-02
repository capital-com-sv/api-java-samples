package com.capital.api.java.samples.service;

import com.capital.api.java.samples.common.SettingsConfig;
import com.capital.api.java.samples.rest.ApiClient;
import com.capital.api.java.samples.rest.ConversationContext;
import com.capital.api.java.samples.rest.dto.account.AccountItem;
import com.capital.api.java.samples.rest.dto.account.GetAccountsResponse;
import com.capital.api.java.samples.rest.dto.account.preference.GetPreferencesResponse;
import com.capital.api.java.samples.rest.dto.account.preference.LeverageGroup;
import com.capital.api.java.samples.rest.dto.account.preference.UpdatePreferencesRequest;
import com.capital.api.java.samples.rest.dto.account.preference.UpdatePreferencesResponse;
import com.capital.api.java.samples.rest.dto.account.update.UpdateActiveAccountRequest;
import com.capital.api.java.samples.rest.dto.market.details.GetMarketDetailsListResponse;
import com.capital.api.java.samples.rest.dto.market.navigation.GetMarketNavigationNodeResponse;
import com.capital.api.java.samples.rest.dto.market.navigation.GetMarketNavigationResponse;
import com.capital.api.java.samples.rest.dto.orders.*;
import com.capital.api.java.samples.rest.dto.orders.confimation.GetDealConfirmationResponse;
import com.capital.api.java.samples.rest.dto.positions.CreatePositionRequest;
import com.capital.api.java.samples.rest.dto.positions.CreatePositionResponse;
import com.capital.api.java.samples.rest.dto.positions.PositionItem;
import com.capital.api.java.samples.rest.dto.prices.GetPricesResponse;
import com.capital.api.java.samples.rest.dto.session.GetSessionResponse;
import com.capital.api.java.samples.rest.dto.time.ServerTime;
import com.capital.api.java.samples.rest.util.DtoGenerator;
import com.capital.api.java.samples.ws.WsClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;


@AllArgsConstructor
public class Core {
    private static final Logger logger = LoggerFactory.getLogger(Core.class);

    private ApiClient apiClient;
    private WsClient wsClient;
    private ConversationContext conversationContext;

    @Autowired
    SettingsConfig settingsConfig;

    public void rsi(List<String> symbols, List<String> timeframes, Integer period) throws Exception {
        RSI bidRsi = new RSI(period);
        RSI askRsi = new RSI(period);
        setupPrices(period, symbols, timeframes, bidRsi, true);
        setupPrices(period, symbols, timeframes, askRsi, false);
        AtomicReference<AccountItem> account = new AtomicReference<>(apiClient.getAccounts(conversationContext).getAccounts()
                .stream()
                .filter(el -> el.getAccountId().equals(settingsConfig.getFinacc()))
                .findAny().orElseThrow(
                        () -> new RuntimeException("Cannot find any account according to properties")
                ));
        if (!apiClient.getSession(conversationContext).getAccountId().equals(settingsConfig.getFinacc())) {
            switchAccount(account.get().getAccountId());
        }
        wsClient.subscribeOHLCMarketData(symbols, timeframes, quote -> {
            boolean bidSide = quote.getPriceType().equalsIgnoreCase("bid");
            Double rsiValue = bidSide ? bidRsi.putQuoteAndGetRSI(quote.getEpic(), quote.getResolution(), quote.getC()) :
                    askRsi.putQuoteAndGetRSI(quote.getEpic(), quote.getResolution(), quote.getC());
            logger.info(rsiValue.toString());

            if (rsiValue < 30 && !bidSide) {
                try{
                    var resp1 = apiClient.getPositions(conversationContext);
                    Thread.sleep(1000);
                    if (resp1
                            .getPositions()
                            .stream()
                            .filter(positionItem -> positionItem.getMarket().getEpic().equals(quote.getEpic()))
                            .map(PositionItem::getPosition)
                            .anyMatch(position -> position.getDirection().equals(Direction.BUY))) {
                        return;
                    }

                    account.set(apiClient.getAccounts(conversationContext).getAccounts()
                            .stream()
                            .filter(el -> el.getAccountId().equals(settingsConfig.getFinacc()))
                            .findAny().orElseThrow(
                                    () -> new RuntimeException("Cannot find any account according to properties")
                            ));
                    var equity = account.get().getBalance().getAvailable();

                    AtomicReference<BigDecimal> quantity = new AtomicReference<>(BigDecimal.ZERO);
                    var re  = getMarketDetailsListResponse(quote.getEpic());
                    AtomicReference<Double> variable = new AtomicReference<>((double) 0);
                    re.getMarketDetails().forEach(el -> {
                        if (settingsConfig.getQuantityUsingManual()) {
                            quantity.set(BigDecimal.valueOf(el.getDealingRules().getMinDealSize().getValue() + settingsConfig.getQuantityManual()));
                        } else {
                            quantity.set(BigDecimal.valueOf(el.getDealingRules().getMinDealSize().getValue() + (settingsConfig.getQuantityPresents() * equity) / 100));
                        }
                        // var pair = checkSLTPOffset(
                        //         BigDecimal.valueOf(el.getDealingRules().getMinStopOrProfitDistance().getValue()),
                        //         BigDecimal.valueOf(el.getDealingRules().getMaxStopOrProfitDistance().getValue()),
                        //         BigDecimal.valueOf(el.getDealingRules().getMinStepDistance().getValue()),
                        //         BigDecimal.valueOf(quote.getC()),
                        //         quantity.get());
                        // logger.info(pair.toString());
                        var pips = settingsConfig.getSltpPipsFromLowestPrice() * el.getDealingRules().getMinStepDistance().getValue();
                        var presents = settingsConfig.getSltpPresents().doubleValue() * equity / 100;
                        if (settingsConfig.getSltpUsingPips()) {
                            variable.set(pips);
                        } else {
                            variable.set(presents);
                        }
                    });

                    CreatePositionResponse resp = createPosition(
                            quote.getEpic(),
                            quantity.get(), // quantity
                            Direction.BUY,
                            BigDecimal.valueOf(variable.get()), // tp
                            BigDecimal.valueOf(variable.get()) // sl
                    );
                    Thread.sleep(1000);
                    GetDealConfirmationResponse confirmation = apiClient.getConfirmation(conversationContext, resp.getDealReference());
                    System.out.println("confirmation: " + confirmation);
                } catch (Exception e) {
                    System.err.println("Can not buy BTC: " + e);
                }
            } else if (rsiValue > 70 && bidSide) {
                try {
                    var resp1 = apiClient.getPositions(conversationContext);
                    Thread.sleep(1000);
                    if (resp1
                            .getPositions()
                            .stream()
                            .filter(positionItem -> positionItem.getMarket().getEpic().equals(quote.getEpic()))
                            .map(PositionItem::getPosition)
                            .anyMatch(position -> position.getDirection().equals(Direction.SELL))) {
                        return;
                    }

                    account.set(apiClient.getAccounts(conversationContext).getAccounts()
                            .stream()
                            .filter(el -> el.getAccountId().equals(settingsConfig.getFinacc()))
                            .findAny().orElseThrow(
                                    () -> new RuntimeException("Cannot find any account according to properties")
                            ));
                    var equity = account.get().getBalance().getAvailable();
                    AtomicReference<BigDecimal> quantity = new AtomicReference<>(BigDecimal.ZERO);
                    var re  = getMarketDetailsListResponse(quote.getEpic());
                    AtomicReference<Double> variable = new AtomicReference<>((double) 0);
                    re.getMarketDetails().forEach(el -> {
                        if (settingsConfig.getQuantityUsingManual()) {
                            quantity.set(BigDecimal.valueOf(el.getDealingRules().getMinDealSize().getValue() + settingsConfig.getQuantityManual()));
                        } else {
                            quantity.set(BigDecimal.valueOf(el.getDealingRules().getMinDealSize().getValue() + (settingsConfig.getQuantityPresents() * equity) / 100));
                        }
                        // var pair = checkSLTPOffset(
                        //         BigDecimal.valueOf(el.getDealingRules().getMinStopOrProfitDistance().getValue()),
                        //         BigDecimal.valueOf(el.getDealingRules().getMaxStopOrProfitDistance().getValue()),
                        //         BigDecimal.valueOf(el.getDealingRules().getMinStepDistance().getValue()),
                        //         BigDecimal.valueOf(quote.getC()),
                        //         quantity.get());
                        // logger.info(pair.toString());
                        System.out.println("Suggested sl tp value for price: " + quote.getC());
                        var pips = settingsConfig.getSltpPipsFromLowestPrice() * el.getDealingRules().getMinStepDistance().getValue();
                        var presents = settingsConfig.getSltpPresents().doubleValue() * equity / 100;
                        if (settingsConfig.getSltpUsingPips()) {
                            variable.set(pips);
                        } else {
                            variable.set(presents);
                        }
                    });

                    CreatePositionResponse resp = createPosition(
                            quote.getEpic(),
                            quantity.get(), // quantity
                            Direction.SELL,
                            BigDecimal.valueOf(variable.get()), // tp
                            BigDecimal.valueOf(variable.get()) // sl
                    );

                    Thread.sleep(1000);
                    GetDealConfirmationResponse confirmation = apiClient.getConfirmation(conversationContext, resp.getDealReference());
                    System.out.println("confirmation: " + confirmation);
                } catch (Exception e) {
                    System.err.println("Can not buy BTC: " + e);
                }
            }
        });
    }

    private void setupPrices(Integer period, List<String> symbols, List<String> timeframes, RSI rsi, boolean bid) {
        symbols.forEach(symbol ->
                timeframes.forEach(resolution ->
                        apiClient.getPrices(conversationContext, period.toString(), null, symbol, null, null, resolution)
                                .getPrices().forEach(pricesItem ->
                                        rsi.putQuote(symbol, resolution, bid ? pricesItem.getClosePrice().getBid().doubleValue() : pricesItem.getClosePrice().getAsk().doubleValue()))));
    }

    private CreatePositionResponse createPosition(String epic, BigDecimal qty, Direction direction, BigDecimal stopDistance, BigDecimal profitDistance) throws Exception {
        CreatePositionRequest request = CreatePositionRequest.builder()
                .epic(epic)
                .size(qty)
                .direction(direction)
                .stopDistance(stopDistance)
                .profitDistance(profitDistance)
                .build();
        CreatePositionResponse response = apiClient.createPosition(conversationContext, request);
        logger.info("Position created, deal reference: " + response.getDealReference());
        return response;
    }

    // private OffsetPair checkSLTPOffset(BigDecimal minOffset, BigDecimal maxOffset, BigDecimal tickSize, BigDecimal price, BigDecimal quantity) {
    //     BigDecimal minPriceOffset = price.add(getOffset(quantity, price, minOffset, tickSize));
    //     BigDecimal maxPriceOffset = price.add(getOffset(quantity, price, maxOffset, tickSize));
    //     return new OffsetPair(minPriceOffset, maxPriceOffset);
    // }

    // private BigDecimal getOffset(BigDecimal quantity, BigDecimal quotePrice, BigDecimal priceOffsetPercent, BigDecimal tickSize) {
    //     BigDecimal offsetValue = quotePrice.multiply(priceOffsetPercent).divide(BigDecimal.valueOf(100), MathContext.DECIMAL64).divideToIntegralValue(tickSize).multiply(tickSize);
    //     return quantity.signum() > 0 ? offsetValue.negate() : offsetValue;
    // }

    private CreateWorkingOrderResponse createOrder() throws Exception {
        CreateWorkingOrderRequest request = DtoGenerator.createOrder();
        CreateWorkingOrderResponse response = apiClient.createOrder(conversationContext, request);
        logger.info("Order created: " + response.getDealReference());
        return response;
    }

    private void printAccounts() throws Exception {
        GetAccountsResponse response = apiClient.getAccounts(conversationContext);
        List<AccountItem> accounts = response.getAccounts();
        logger.info("All accounts:");
        accounts.forEach(a -> logger.info("AccountId: " + a.getAccountId() + ", AccountName: " + a.getAccountName() + ", AccountType: " + a.getAccountType()));
    }

    private void switchAccount(String accountId) throws Exception {
        logger.info("Switch account");
        UpdateActiveAccountRequest request = new UpdateActiveAccountRequest(accountId);
        apiClient.updateActiveAccount(conversationContext, request);
    }

    private void printSession() throws Exception {
        GetSessionResponse session = apiClient.getSession(conversationContext);
        logger.info("Active session: " + session.getAccountId() + ", clientId: " + session.getClientId());
    }

    private void printPreferences() throws Exception {
        GetPreferencesResponse preferences = apiClient.getPreferences(conversationContext);
        logger.info("Preferences: " + preferences.toString());
    }

    private void updatePreferences() throws Exception {
        UpdatePreferencesRequest request = new UpdatePreferencesRequest();
        request.setLeverages(Map.of(LeverageGroup.SHARES, 1, LeverageGroup.CURRENCIES, 1));
        UpdatePreferencesResponse response = apiClient.updatePreferences(conversationContext, request);
        logger.info("Preferences updates: " + response.getStatus());
    }

    private void printMarketNavigation() throws Exception {
        GetMarketNavigationResponse response = apiClient.getMarketNavigation(conversationContext);
        logger.info("Market navigation: " + response.getNodes());
    }

    private void printMarketNavigationNode() throws Exception {
        String nodeId = "hierarchy_v1.indices_group";
        GetMarketNavigationNodeResponse response = apiClient.getMarketNavigationIndicesNode(conversationContext, nodeId);
        logger.info("Market navigation by node: " + response.getNodes());
    }

    private GetMarketDetailsListResponse getMarketDetailsListResponse(String epics) throws Exception {
        return apiClient.getMarketDetailsList(conversationContext, epics);
    }

    private CreatePositionResponse createPosition() throws Exception {
        CreatePositionRequest request = DtoGenerator.createPosition();
        CreatePositionResponse response = apiClient.createPosition(conversationContext, request);
        logger.info("Position created, deal reference: " + response.getDealReference());
        return response;
    }

    private void printPositions() throws Exception {
        GetPositionsResponse response = apiClient.getPositions(conversationContext);
        logger.info("Positions: " + response.getPositions());
    }

    private void printOrders() throws Exception {
        GetWorkingOrdersResponse response = apiClient.getOrders(conversationContext);
        logger.info("Orders: " + response.getWorkingOrders());
    }

    private void printConfirmation(String dealReference) {
        GetDealConfirmationResponse response = apiClient.getConfirmation(conversationContext, dealReference);
        logger.info("Deal confirmation: " + response);
    }

    private void printPrices(String marketId) throws Exception {
        GetPricesResponse response = apiClient.getPrices(conversationContext, "", "", "", "", "", "");
        logger.info("Prices: " + response.getPrices());
    }

    private void printServerTime() throws Exception {
        ServerTime serverTime = apiClient.getTime(conversationContext);
        Date serverDate = new Date(serverTime.getServerTime());
        logger.info("Server time: " + serverDate);
    }
}
