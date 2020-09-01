package com.dbs.stock.repository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dbs.stock.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
	static final Logger log = LoggerFactory.getLogger(StockRepository.class);

	@Query("SELECT s FROM Stock s WHERE LOWER(s.stockSymbol) = LOWER(:stockSymbol) AND LOWER(s.bidPrice) = LOWER(:bidPrice) AND LOWER(s.askPrice) = LOWER(:askPrice)")
	Optional<Stock> findByQuey(@Param("stockSymbol") String stockSymbol,
								@Param("bidPrice") String bidPrice,
								@Param("askPrice") String askPrice);
 
}