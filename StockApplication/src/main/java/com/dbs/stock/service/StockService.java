package com.dbs.stock.service;

import java.util.List;

import com.dbs.stock.model.Stock;
import com.dbs.stock.model.StockDTO;

public interface StockService {

	List<StockDTO> viewStock();

	Stock createStock(Stock stock);

	Stock updateStock(Stock stock);

	List<Stock> getAllStock();

	Stock getStockById(long stockId);

	void deleteStock(long id);

}
