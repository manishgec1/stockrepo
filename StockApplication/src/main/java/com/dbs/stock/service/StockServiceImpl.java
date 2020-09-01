package com.dbs.stock.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.stock.exception.ResourceNotFoundException;
import com.dbs.stock.model.Stock;
import com.dbs.stock.model.StockDTO;
import com.dbs.stock.repository.StockRepository;
import com.dbs.stock.utility.FileToList;

@Service
@Transactional
public class StockServiceImpl implements StockService {

	private static final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);

	private static final String txt = "C:/dev/workspaceGIS/StockApplication/src/main/resources/stock_price.txt";

	@Autowired
	private StockRepository stockRepository;

	@Autowired
	FileToList file;

	@Override
	public List<StockDTO> viewStock() {
		log.info("Enter viewStock");
		List<Stock> stockLists = new LinkedList<Stock>();
		try {
			stockLists = file.createObject(file.fileToList(txt));
			stockLists.forEach(s -> log.info("File Reading stockList  => " + s.getId() + " : " + s.getStockSymbol() + " : " + s.getBidPrice() + " : " + s.getAskPrice()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Stock stock : stockLists) {
			Stock saveStock = createStock(stock);
		}

		List<Stock> stockList = this.stockRepository.findAll();
		stockList.forEach(s -> log.info("stockRepository stockList  => " + s.getId() + " : " + s.getStockSymbol() + " : " + s.getBidPrice() + " : " + s.getAskPrice()));

		BigDecimal divisor = BigDecimal.valueOf(2);

		Set<String> stockSymbolSets = stockList.stream().map(x -> x.getStockSymbol()).collect(Collectors.toSet());
		stockSymbolSets.forEach(x -> log.info("Get Unique stockSymbolSets =>" + (x)));

		// TO create sorted Map of stockSymbolSets and List of Stocks available for that stock symbol
		final SortedMap<String, List<StockDTO>> sortedMap = new TreeMap<String, List<StockDTO>>();
		stockSymbolSets.stream().filter(Objects::nonNull).forEach(sss -> {
			List<StockDTO> stockDTOList = new LinkedList<>();
			stockList.stream().filter(sdtoss -> sdtoss != null && sss.equals(sdtoss.getStockSymbol())).forEach(sdtoss -> {
				StockDTO dto = new StockDTO(sdtoss.getId(), sdtoss.getStockSymbol(), sdtoss.getBidPrice().add(sdtoss.getAskPrice()).divideToIntegralValue(divisor), sdtoss.getEventtime());
				stockDTOList.add(dto);
			});

			// Create a sort List based on event time
			stockDTOList.sort((s1, s2) -> {
				return (int) (s2.getEventtime().getTime() - s1.getEventtime().getTime());
			});

			if (stockDTOList.size() > 0) {
				sortedMap.put(sss, stockDTOList);
			}

		});

		sortedMap.forEach((k, v) -> {
			log.info("getStockSymbol key : " + k + "price : " + v.get(0).getMarketPrice() + " getEventtime  : " + v.get(0).getEventtime());

		});

		List<StockDTO> stockDTOList = new LinkedList<StockDTO>();
		sortedMap.forEach((k, v) -> {
			StockDTO stockDTO = new StockDTO();
			stockDTO.setId(v.get(0).getId());
			stockDTO.setStockSymbol(v.get(0).getStockSymbol());
			stockDTO.setMarketPrice(v.get(0).getMarketPrice());
			stockDTO.setEventtime(v.get(0).getEventtime());
			if (v.size() >= 2) {
				if (-1 == v.get(0).getMarketPrice().compareTo(v.get(1).getMarketPrice())) {
					stockDTO.setUpDown(false);
				} else {
					stockDTO.setUpDown(true);
				}
			}
			stockDTOList.add(stockDTO);
		});
		stockDTOList.forEach(x -> log.info("stockListStream  => " + x.getStockSymbol() + " : " + x.getMarketPrice() + " : " + x.isUpDown() + " : " + x.getEventtime()));

		return stockDTOList;
	}

	@Override
	public Stock createStock(Stock stock) {
		log.info("stockRepository.findByQuey => ");
		Optional<Stock> existingStock = stockRepository.findByQuey(stock.getStockSymbol(), String.valueOf(stock.getBidPrice()), String.valueOf(stock.getAskPrice()));
		Stock stockDb = new Stock();
		if (existingStock.isPresent()) {
			stockDb = existingStock.get();
			log.info("Existing Record found in stockRepository => ");
		} else {
			stockDb = this.stockRepository.save(stock);
			log.info("New Record saved successfully in stockRepository => ");
		}
		log.info("Record saved successfully in stockRepository => ");
		return stockDb;
	}

	@Override
	public Stock updateStock(Stock stock) {
		log.info("Record Id to update in stockRepository => " + stock.getId());
		Optional<Stock> stockDb = this.stockRepository.findById(stock.getId());

		if (stockDb.isPresent()) {
			Stock stockUpdate = stockDb.get();
			stockUpdate.setId(stock.getId());
			stockUpdate.setStockSymbol(stock.getStockSymbol());
			stockUpdate.setBidPrice(stock.getBidPrice());
			stockUpdate.setAskPrice(stock.getAskPrice());
			stockRepository.save(stockUpdate);
			log.info("Record update successfully in stockRepository => ");
			return stockUpdate;
		} else {
			throw new ResourceNotFoundException("Stock Record not found with id : " + stock.getId());
		}
	}

	@Override
	public List<Stock> getAllStock() {
		return this.stockRepository.findAll();
	}

	@Override
	public Stock getStockById(long stockId) {
		Optional<Stock> stockDb = this.stockRepository.findById(stockId);
		if (stockDb.isPresent()) {
			log.info("Record Id found in stockRepository => " + stockId);
			return stockDb.get();
		} else {
			throw new ResourceNotFoundException("Stock Record not found with id : " + stockId);
		}
	}

	@Override
	public void deleteStock(long stockId) {
		Optional<Stock> stockDb = this.stockRepository.findById(stockId);

		if (stockDb.isPresent()) {
			this.stockRepository.delete(stockDb.get());
			log.info("Record Id deleted in stockRepository => " + stockId);
		} else {
			throw new ResourceNotFoundException("Stock Record not found with id : " + stockId);
		}
	}
}