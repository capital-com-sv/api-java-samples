package com.capital.api.java.samples;

import com.capital.api.java.samples.common.CapitalUserConfig;
import com.capital.api.java.samples.common.Enviroment;
import com.capital.api.java.samples.common.SettingsConfig;
import com.capital.api.java.samples.rest.ApiClient;
import com.capital.api.java.samples.rest.AuthenticationApiClient;
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
import com.capital.api.java.samples.rest.dto.prices.GetPricesResponse;
import com.capital.api.java.samples.rest.dto.session.CreateSessionRequest;
import com.capital.api.java.samples.rest.dto.session.GetSessionResponse;
import com.capital.api.java.samples.rest.dto.time.ServerTime;
import com.capital.api.java.samples.rest.util.DtoGenerator;
import com.capital.api.java.samples.service.Core;
import com.capital.api.java.samples.ws.WsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.util.*;

@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private ApiClient apiClient;

	@Autowired
	private WsClient wsClient;

	@Autowired
	private SettingsConfig settingsConfig;

	@Autowired
	private CapitalUserConfig capitalUserConfig;

	@Autowired
	private AuthenticationApiClient authenticationService;

	private ConversationContext conversationContext = null;

	@Override
	public void run(String... args) throws Exception {
		logger.info("START!");
		apiClient.setEnviroment(capitalUserConfig.getEnv());
		authenticationService.setEnviroment(capitalUserConfig.getEnv());

		conversationContext = authenticate(capitalUserConfig.getLogin(), capitalUserConfig.getPassword(), true, capitalUserConfig.getApiKey());
		printAccounts();
		GetAccountsResponse response = apiClient.getAccounts(conversationContext);
		response.getAccounts().forEach(el -> {
			logger.info(el.getBalance().getAvailable().toString());
			logger.info("amount : " + settingsConfig.getQuantityPresents() * el.getBalance().getAvailable() / 100.0);
			logger.info("sltp : " + settingsConfig.getSltpPresents() * el.getBalance().getAvailable() / 100.0);
		});
		printSession();
		printMarketNavigation();
		printServerTime();

		wsClient.connect(conversationContext);

		Core core = new Core(apiClient, wsClient, conversationContext, settingsConfig);
		core.rsi(settingsConfig.getEpics(), settingsConfig.getResolutions(), settingsConfig.getRSIPeriod());
	}

	@Scheduled(fixedRate = 30000, initialDelay = 30000)
	public void ping() throws Exception {
		apiClient.getTime(conversationContext);
		wsClient.ping();
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(WebApplicationType.NONE).run(args);
	}

	private ConversationContext authenticate(String identifier, String password, boolean encryptedPassword, String apiKey) {
		CreateSessionRequest request = new CreateSessionRequest();
		request.setIdentifier(identifier);
		request.setPassword(password);
		request.setEncryptedPassword(encryptedPassword);
		return authenticationService.createSession(request, apiKey);
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

	private void printMarketDetailsList(String epics) throws Exception {
		GetMarketDetailsListResponse response = apiClient.getMarketDetailsList(conversationContext, epics);
		logger.info("Markets details list: " + response);
	}

	private CreatePositionResponse createPosition() throws Exception {
		CreatePositionRequest request = DtoGenerator.createPosition();
		CreatePositionResponse response = apiClient.createPosition(conversationContext, request);
		logger.info("Position created, deal reference: " + response.getDealReference());
		return response;
	}

	private CreatePositionResponse createPosition(String epic, BigDecimal qty, Direction direction) throws Exception {
		CreatePositionRequest request = CreatePositionRequest.builder().build();
		request.setEpic(epic);
		request.setSize(qty);
		request.setDirection(direction);
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
