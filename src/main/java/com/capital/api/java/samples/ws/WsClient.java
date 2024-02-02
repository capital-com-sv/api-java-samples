package com.capital.api.java.samples.ws;

import com.capital.api.java.samples.callback.OHLCBar;
import com.capital.api.java.samples.callback.OnMarketDataCallback;
import com.capital.api.java.samples.common.Constants;
import com.capital.api.java.samples.rest.ConversationContext;
import com.capital.api.java.samples.ws.dto.Request;
import com.capital.api.java.samples.ws.dto.Response;
import com.capital.api.java.samples.ws.dto.market.MarketDataSubscribe;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

@Service
public class WsClient {

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

    private static final Logger logger = LoggerFactory.getLogger(WsClient.class);

    private ConversationContext conversationContext;

    private WebSocketClient client;
    private Session session;

    private Map<String, OnMarketDataCallback> marketDataCallbacks = new HashMap<>();
    private URI wsUrl;

    public void connect(ConversationContext conversationContext) throws Exception {
        this.conversationContext = conversationContext;
        wsUrl = new URI(conversationContext.getStreamingURL().concat(Constants.CONNECT));
        client = new WebSocketClient();
        if (!client.isRunning()) {
            client.start();
        }

    }


    private Session connect() throws IOException  {
        ClientUpgradeRequest request = new ClientUpgradeRequest();
        request.setTimeout(10_000, TimeUnit.MILLISECONDS);
        logger.info("WS URL: {}", wsUrl);
        try {
            return client.connect(new ClientSocket(), wsUrl, request).get(15_000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            throw new ConnectException(e.getMessage());
        }
    }


    private synchronized Session getSession() throws IOException {
         if (session == null) {
            session = connect();
         } else if (!session.isOpen()) {
             session.close();
             session = connect();
         }
         return session;
    }

    public void ping() throws IOException {
        Request requestPing = new Request();
        requestPing.setDestination("ping");
        requestPing.setCorrelationId(UUID.randomUUID().toString());
        requestPing.setPayload(null);
        requestPing.setCst(conversationContext.getClientSecurityToken());
        requestPing.setSecurityToken(conversationContext.getAccountSecurityToken());

        getSession().getRemote().sendString(OBJECT_MAPPER.writeValueAsString(requestPing));
    }

    public void subscribeOHLCMarketData(List<String> instruments, List<String> resolutions, OnMarketDataCallback callback) throws IOException {
        Request request = new Request();
        request.setDestination("OHLCMarketData.subscribe");
        request.setCorrelationId(UUID.randomUUID().toString());
        request.setPayload(new MarketDataSubscribe(instruments, resolutions));
        request.setCst(conversationContext.getClientSecurityToken());
        request.setSecurityToken(conversationContext.getAccountSecurityToken());

        getSession().getRemote().sendString(OBJECT_MAPPER.writeValueAsString(request));
        instruments.forEach(e -> marketDataCallbacks.put(e, callback));
    }

    public void close() throws Exception {
        getSession().disconnect();
        client.stop();
    }

    @WebSocket
    public class ClientSocket {
        @OnWebSocketMessage
        public void onMessage(String message) throws Exception {
            Response response = OBJECT_MAPPER.readValue(message, Response.class);

            if (response.getDestination().equals("ping")) {
                return;
            }

            System.out.println("Response from Server: " + response);

            if (response.getDestination().equals("ohlc.event")) {
                OHLCBar q = OBJECT_MAPPER.convertValue(response.getPayload(), OHLCBar.class);
                OnMarketDataCallback callback = marketDataCallbacks.get(q.getEpic());
                if (callback != null) {
                    callback.onMarketData(q);
                }
            }
        }
        @OnWebSocketClose
        public void onClose(int statusCode, String reason) {
            System.out.println("WebSocket broken pipe Closed: " + statusCode);
            System.out.println("Time: " + System.currentTimeMillis());
        }
    }
}
