package com.dbs.stock.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class StockDTO {

	private long id;

	private String stockSymbol;

	private BigDecimal marketPrice;

	private Date eventtime;

	private boolean upDown;

	public StockDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockDTO(long id, String stockSymbol, BigDecimal marketPrice, Date eventtime) {
		super();
		this.id = id;
		this.stockSymbol = stockSymbol;
		this.marketPrice = marketPrice;
		this.eventtime = eventtime;
	}

	public StockDTO(long id, String stockSymbol, BigDecimal marketPrice, Date eventtime, boolean upDown) {
		super();
		this.id = id;
		this.stockSymbol = stockSymbol;
		this.marketPrice = marketPrice;
		this.eventtime = eventtime;
		this.upDown = upDown;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Date getEventtime() {
		return eventtime;
	}

	public void setEventtime(Date eventtime) {
		this.eventtime = eventtime;
	}

	public boolean isUpDown() {
		return upDown;
	}

	public void setUpDown(boolean upDown) {
		this.upDown = upDown;
	}

}
