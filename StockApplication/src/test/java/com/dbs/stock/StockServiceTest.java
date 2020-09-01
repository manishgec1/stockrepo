package com.dbs.stock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.dbs.stock.model.Stock;
import com.dbs.stock.repository.StockRepository;
import com.dbs.stock.service.StockServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {

	@Mock
	private StockRepository stockRepsository;

	@InjectMocks
	private StockServiceImpl stockService;
	
	private static Stock mockData ;

	@BeforeAll
	static void setupCommon() {
		mockData = new Stock();
		mockData.setStockSymbol("D05:SGX");
		mockData.setBidPrice(BigDecimal.valueOf(50.00));
		mockData.setAskPrice(BigDecimal.valueOf(70.00));
	}
	
	@Test
	public void whenFindAll_thenReturnStockList() {
		

		List<Stock> expectedStocks = Arrays.asList(mockData);

		doReturn(expectedStocks).when(stockRepsository).findAll();

		// when
		List<Stock> actualStocks = stockService.getAllStock();

		// then
		assertThat(actualStocks).isEqualTo(expectedStocks);
	}
	
	@Test
	public void findbyAll() {
		List<Stock> actualStocks = stockRepsository.findAll();
		int nOfCities = 0;

		assertThat(actualStocks).hasSize(nOfCities);
	}
	
	@Test
	public void findByQuey() {
		setupCommon();
		
	//	List<Stock> expectedStocks = Arrays.asList(mockData);
		Optional<Stock>  expectedStocks= Optional.of(mockData);

		doReturn(expectedStocks).when(stockRepsository).findByQuey(mockData.getStockSymbol(), mockData.getBidPrice().toString(), mockData.getAskPrice().toString());
		
		Optional<Stock> actualStocks = stockRepsository.findByQuey(mockData.getStockSymbol(), mockData.getBidPrice().toString(), mockData.getAskPrice().toString());
		
		assertThat(actualStocks).isEqualTo(expectedStocks);
	}
}
