package com.capital.api.java.samples.common;

public final class Constants {
    public static final String CAP_APP_KEY = "X-CAP-API-KEY";
    public static final String CLIENT_SSO_TOKEN_NAME = "CST";
    public static final String ACCOUNT_SSO_TOKEN_NAME = "X-SECURITY-TOKEN";

    public static final String API = "/api";
    public static final String V_1 = "/v1";
    public static final String API_V1 = API.concat(V_1);

    public static final String SESSION = "/session";
    public static final String ACCOUNTS = "/accounts";
    public static final String MARKET_NAVIGATION = "/marketnavigation";
    public static final String MARKETS = "/markets";
    public static final String POSITIONS = "/positions";
    public static final String ORDERS = "/workingorders";
    public static final String CONNECT = "/connect";

    public static final String API_V1_SESSION = API_V1.concat(SESSION);
    public static final String API_V1_ACCOUNTS = API_V1.concat(ACCOUNTS);
    public static final String API_V1_MARKET_NAVIGATION = API_V1.concat(MARKET_NAVIGATION);
    public static final String API_V1_MARKETS = API_V1.concat(MARKETS);
    public static final String API_V1_POSITIONS = API_V1.concat(POSITIONS);
    public static final String API_V1_ORDERS = API_V1.concat(ORDERS);
    public static final String API_V1_CONFIRMS = API_V1.concat("/confirms");
    public static final String API_V1_TIME = API_V1.concat("/time");
    public static final String API_V1_CLIENTSENTIMENT = API_V1.concat("/clientsentiment");
    public static final String API_V1_PRICES = API_V1.concat("/prices");
    public static final String API_V1_WATCHLISTS = API_V1.concat("/watchlists");
    public static final String API_V1_HISTORY = API_V1.concat("/history");

    public static final String API_V1_SESSION_ENCRYPTION_KEY = API_V1_SESSION.concat("/encryptionKey");
    public static final String API_V1_ACCOUNTS_PREFERENCES = API_V1_ACCOUNTS.concat("/preferences");
    public static final String API_V1_HISTORY_TRANSACTIONS = API_V1_HISTORY.concat("/transactions");

}
