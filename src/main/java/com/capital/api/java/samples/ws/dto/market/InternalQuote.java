package com.capital.api.java.samples.ws.dto.market;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class InternalQuote {
	private String epic;
	private String product;
	private double bid;
	private double bidQty;
	private double ofr;
	private double ofrQty;
	private long timestamp;


	public String getEpic() {
		return epic;
	}


	public double getBid() {
		return bid;
	}


	public double getBidQty() {
		return bidQty;
	}


	public double getOfr() {
		return ofr;
	}


	public double getOfrQty() {
		return ofrQty;
	}


	public long getTimestamp() {
		return timestamp;
	}

	public String getProduct() {
		return product;
	}
}
