package com.capital.api.java.samples.callback;

import com.capital.api.java.samples.ws.dto.market.InternalQuote;

public interface OnMarketDataCallback {

    public void onMarketData(OHLCBar quote) throws Exception;
}
