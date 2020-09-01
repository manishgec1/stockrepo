package com.dbs.stock.stockapplication.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.stock.model.Stock;
import com.dbs.stock.model.StockDTO;
import com.dbs.stock.service.StockService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class StockController {
	
	private static final Logger log = LoggerFactory.getLogger(StockController.class);
	
	@Autowired
	private StockService stockService;

	@GetMapping("/stockspricing")
	public ResponseEntity<List<StockDTO>> viewStock() {
		log.info("Request to view Stocks pricing => ");
		return ResponseEntity.ok().body(stockService.viewStock());
	}

	@GetMapping("/stocks")
	public ResponseEntity<List<Stock>> getAllStock() {
		log.info("GET Request to view All Stocks => ");
		return ResponseEntity.ok().body(stockService.getAllStock());
	}

	@GetMapping("/stocks/{id}")
	public ResponseEntity<Stock> getStockById(@PathVariable long id) {
		log.info("GET Request to view All Stocks by ID => " +id);
		return ResponseEntity.ok().body(stockService.getStockById(id));
	}

	@PostMapping("/stocks")
	public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
		log.info("POST Request to create new Stocks => " +stock);
		return ResponseEntity.ok().body(this.stockService.createStock(stock));
	}

	@PutMapping("/stocks/{id}")
	public ResponseEntity<Stock> updateStock(@PathVariable long id, @RequestBody Stock stock) {
		log.info("PUT Request to Update new Stocks => " +id);
		stock.setId(id);
		return ResponseEntity.ok().body(this.stockService.updateStock(stock));
	}

	@DeleteMapping("/stocks/{id}")
	public HttpStatus deleteStock(@PathVariable long id) {
		log.info("DELETE Request to delete new Stocks by id => " +id);
		this.stockService.deleteStock(id);
		return HttpStatus.OK;
	}
}
