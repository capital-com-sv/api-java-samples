package com.capital.api.java.samples.callback;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OHLCBar {
	String resolution;
	String epic; // Symbol
	String type;
	String priceType;
	long   t; // Time
	double h; // High
	double l; // Low
	double o; // Open
	double c; // Close

	public OHLCBar(String id, String resolution, long timestamp, double price, String type, String priceType) {
		this.epic = id;
		this.resolution = resolution;
		this.t = timestamp;
		this.h = this.l = this.o = this.c = price;
		this.type = type;
		this.priceType = priceType;
	}

	public double getH() {
		return h;
	}

	public double getL() {
		return l;
	}

	public double getO() {
		return o;
	}

	public double getC() {
		return c;
	}

	public long getT() {
		return t;
	}

	public String getResolution() {
		return resolution;
	}

	public String getEpic() {
		return epic;
	}

	public String getType() {
		return type;
	}

	public String getPriceType() {
		return priceType;
	}

	public void update(double price) {
		if (h < price) {
			h = price;
		} else if (l > price) {
			l = price;
		}

		c = price;
	}

	public void setH(double h) {
		this.h = h;
	}

	public void setL(double l) {
		this.l = l;
	}

	public void setO(double o) {
		this.o = o;
	}

	public void setC(double c) {
		this.c = c;
	}
}
