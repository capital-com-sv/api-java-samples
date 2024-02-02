package com.capital.api.java.samples.rest;

import com.capital.api.java.samples.common.Enviroment;
import com.capital.api.java.samples.rest.dto.account.GetAccountsResponse;
import com.capital.api.java.samples.rest.dto.account.preference.GetPreferencesResponse;
import com.capital.api.java.samples.rest.dto.account.preference.UpdatePreferencesRequest;
import com.capital.api.java.samples.rest.dto.account.preference.UpdatePreferencesResponse;
import com.capital.api.java.samples.rest.dto.account.update.UpdateActiveAccountRequest;
import com.capital.api.java.samples.rest.dto.account.update.UpdateActiveAccountResponse;
import com.capital.api.java.samples.rest.dto.clientsentiment.GetClientSentimentResponse;
import com.capital.api.java.samples.rest.dto.history.GetActivityHistoryResponse;
import com.capital.api.java.samples.rest.dto.history.GetTransactionHistoryResponse;
import com.capital.api.java.samples.rest.dto.market.details.GetMarketDetailsListResponse;
import com.capital.api.java.samples.rest.dto.market.details.GetMarketDetailsResponse;
import com.capital.api.java.samples.rest.dto.market.navigation.GetMarketNavigationNodeResponse;
import com.capital.api.java.samples.rest.dto.market.navigation.GetMarketNavigationResponse;
import com.capital.api.java.samples.rest.dto.orders.CreateWorkingOrderRequest;
import com.capital.api.java.samples.rest.dto.orders.CreateWorkingOrderResponse;
import com.capital.api.java.samples.rest.dto.orders.GetWorkingOrdersResponse;
import com.capital.api.java.samples.rest.dto.orders.confimation.GetDealConfirmationResponse;
import com.capital.api.java.samples.rest.dto.positions.CreatePositionRequest;
import com.capital.api.java.samples.rest.dto.positions.CreatePositionResponse;
import com.capital.api.java.samples.rest.dto.orders.GetPositionsResponse;
import com.capital.api.java.samples.rest.dto.prices.GetPricesResponse;
import com.capital.api.java.samples.rest.dto.session.GetSessionResponse;
import com.capital.api.java.samples.rest.dto.time.ServerTime;
import com.capital.api.java.samples.rest.dto.watchlists.CreateWatchlistRequest;
import com.capital.api.java.samples.rest.dto.watchlists.CreateWatchlistResponse;
import com.capital.api.java.samples.rest.dto.watchlists.GetWatchlistsResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.capital.api.java.samples.common.Constants.*;

@Service
public class ApiClient extends AbstractApiClient {
    // account
    public GetAccountsResponse getAccounts(ConversationContext conversationContext) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<GetAccountsResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_ACCOUNTS), HttpMethod.GET, requestEntity, GetAccountsResponse.class);
        return response.getBody();
    }

    public GetSessionResponse getSession(ConversationContext conversationContext) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<GetSessionResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_SESSION), HttpMethod.GET, requestEntity, GetSessionResponse.class);
        return response.getBody();
    }

    // account preferences
    public GetPreferencesResponse getPreferences(ConversationContext conversationContext) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<GetPreferencesResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_ACCOUNTS_PREFERENCES), HttpMethod.GET, requestEntity, GetPreferencesResponse.class);
        return response.getBody();
    }

    public UpdateActiveAccountResponse updateActiveAccount(ConversationContext conversationContext, UpdateActiveAccountRequest request) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, request);
        ResponseEntity<UpdateActiveAccountResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_SESSION), HttpMethod.PUT, requestEntity, UpdateActiveAccountResponse.class);
        return response.getBody();
    }

    public UpdatePreferencesResponse updatePreferences(ConversationContext conversationContext, UpdatePreferencesRequest request) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, request);
        ResponseEntity<UpdatePreferencesResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_ACCOUNTS_PREFERENCES), HttpMethod.PUT, requestEntity, UpdatePreferencesResponse.class);
        return response.getBody();
    }

    // market
    public GetMarketNavigationResponse getMarketNavigation(ConversationContext conversationContext) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<GetMarketNavigationResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_MARKET_NAVIGATION), HttpMethod.GET, requestEntity, GetMarketNavigationResponse.class);
        return response.getBody();
    }

    public GetMarketNavigationNodeResponse getMarketNavigationIndicesNode(ConversationContext conversationContext, String nodeId) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<GetMarketNavigationNodeResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_MARKET_NAVIGATION).concat("/").concat(nodeId), HttpMethod.GET, requestEntity, GetMarketNavigationNodeResponse.class);
        return response.getBody();
    }

    public GetMarketDetailsListResponse getMarketDetailsList(ConversationContext conversationContext, String epics) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        String url = getApiDomainURL().concat(API_V1_MARKETS).concat("?epics=").concat(epics);
        ResponseEntity<GetMarketDetailsListResponse> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, GetMarketDetailsListResponse.class);
        return response.getBody();
    }

    public GetMarketDetailsResponse getMarketDetails(ConversationContext conversationContext, String epic) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<GetMarketDetailsResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_MARKETS).concat("/").concat(epic), HttpMethod.GET, requestEntity, GetMarketDetailsResponse.class);
        return response.getBody();
    }

    // position
    public CreatePositionResponse createPosition(ConversationContext conversationContext, CreatePositionRequest request) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, request);
        ResponseEntity<CreatePositionResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_POSITIONS), HttpMethod.POST, requestEntity, CreatePositionResponse.class);
        return response.getBody();
    }

    public GetPositionsResponse getPositions(ConversationContext conversationContext) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<GetPositionsResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_POSITIONS), HttpMethod.GET, requestEntity, GetPositionsResponse.class);
        return response.getBody();
    }

    // orders
    public GetWorkingOrdersResponse getOrders(ConversationContext conversationContext) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<GetWorkingOrdersResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_ORDERS), HttpMethod.GET, requestEntity, GetWorkingOrdersResponse.class);
        return response.getBody();
    }

    public CreateWorkingOrderResponse createOrder(ConversationContext conversationContext, CreateWorkingOrderRequest request) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, request);
        ResponseEntity<CreateWorkingOrderResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_ORDERS), HttpMethod.POST, requestEntity, CreateWorkingOrderResponse.class);
        return response.getBody();
    }

    public GetDealConfirmationResponse getConfirmation(ConversationContext conversationContext, String dealReference) {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<GetDealConfirmationResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_CONFIRMS).concat("/" + dealReference), HttpMethod.GET, requestEntity, GetDealConfirmationResponse.class);
        return response.getBody();
    }

    // history
    public GetTransactionHistoryResponse getTransactionHistory(ConversationContext conversationContext) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<GetTransactionHistoryResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_HISTORY_TRANSACTIONS), HttpMethod.GET, requestEntity, GetTransactionHistoryResponse.class);
        return response.getBody();
    }

    public GetActivityHistoryResponse getActivityHistory(ConversationContext conversationContext) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<GetActivityHistoryResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_HISTORY_TRANSACTIONS), HttpMethod.GET, requestEntity, GetActivityHistoryResponse.class);
        return response.getBody();
    }

    // watchlists
    public CreateWatchlistResponse createWatchlist(ConversationContext conversationContext, CreateWatchlistRequest createWatchlistRequest) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, createWatchlistRequest);
        ResponseEntity<CreateWatchlistResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_WATCHLISTS), HttpMethod.POST, requestEntity, CreateWatchlistResponse.class);
        return response.getBody();
    }

    public GetWatchlistsResponse getWatchlists(ConversationContext conversationContext) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<GetWatchlistsResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_WATCHLISTS), HttpMethod.GET, requestEntity, GetWatchlistsResponse.class);
        return response.getBody();
    }

    //instrument prices
    public GetPricesResponse getPrices(ConversationContext conversationContext, String max, String pageSize, String epic, String from, String to, String resolution) {
        String uri = epic == null ? "" : "/".concat(epic);
        uri = uri + (max != null ? (uri.contains("?") ? "&" : "?") + "max=" + max : "");
        uri = uri + (resolution != null ? (uri.contains("?") ? "&" : "?") + "resolution=" + resolution : "");
        uri = uri + (from != null ? (uri.contains("?") ? "&" : "?") + "from=" + from : "");
        uri = uri + (to != null ? (uri.contains("?") ? "&" : "?") + "to=" + to : "");
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<GetPricesResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_PRICES).concat(uri), HttpMethod.GET, requestEntity, GetPricesResponse.class);
        return response.getBody();
    }

    //client sentiment
    public GetClientSentimentResponse getClientSentiment(ConversationContext conversationContext, String marketId) throws Exception {
        String uri = marketId == null ? "" : "/".concat(marketId);
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<GetClientSentimentResponse> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_CLIENTSENTIMENT).concat(uri), HttpMethod.GET, requestEntity, GetClientSentimentResponse.class);
        return response.getBody();
    }

    // server time
    public ServerTime getTime(ConversationContext conversationContext) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        ResponseEntity<ServerTime> response = restTemplate.exchange(getApiDomainURL().concat(API_V1_TIME), HttpMethod.GET, requestEntity, ServerTime.class);
        return response.getBody();
    }

    // logout
    public void logout(ConversationContext conversationContext) throws Exception {
        HttpEntity<?> requestEntity = buildHttpEntity(conversationContext, null);
        restTemplate.exchange(getApiDomainURL().concat(API_V1_SESSION), HttpMethod.DELETE, requestEntity, Void.class);
    }

}
