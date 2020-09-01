package com.dbs.stock.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.dbs.stock.model.Stock;

@Component
public class FileToList {
	private static final Logger log = LoggerFactory.getLogger(FileToList.class);

	@Value("classpath:stock_price.txt")
	Resource resourceFile;
	
	public List<String> fileToList(String file) throws IOException {
		log.info("fileToList ");
		System.out.println(" resourceFile.getFile : " + resourceFile.getFile());
		InputStream inputStream = resourceFile.getInputStream();
	//	Resource resource = new ClassPathResource("stock_price.txt");
	//	InputStream inputStream = resource.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		List<String> LineRead = new LinkedList<>();
		String line;
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			if (!line.isEmpty()) {
				LineRead.add(line);
			}
		}
		log.info("fileToList LineRead : " + LineRead.size());
		return LineRead;
	}

	public  List<Stock> createObject(List<String> stockStr) {
		log.info("createObject :  " + stockStr.size());
		List<String[]> convertedStockList = stockStr.stream().map(x -> x.split(",", -1)).collect(Collectors.toList());
		List<Stock> stocks = new ArrayList<Stock>();

		convertedStockList.forEach(array -> {
			Stock stock = new Stock();
			stock.setStockSymbol(array[0]);
			stock.setBidPrice(new BigDecimal(array[1]));
			stock.setAskPrice(new BigDecimal(array[2]));
			try {
				stock.setEventtime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(array[3]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			stocks.add(stock);
		});

		stocks.forEach(x -> System.out.println(x.getStockSymbol()));

		log.info("createObject stocks size :  " + stocks.size());
		return stocks;
	}

}
