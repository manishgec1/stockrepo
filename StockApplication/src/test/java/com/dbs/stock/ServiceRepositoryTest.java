package com.dbs.stock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.dbs.stock.model.Stock;
import com.dbs.stock.repository.StockRepository;
import com.dbs.stock.service.StockServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ServiceRepositoryTest {

	@InjectMocks
	private StockServiceImpl stockService;

	@Mock
	private TestEntityManager testEntityManager;

	@Mock
	private StockRepository stockRepositoryMock;

	private static Stock mockData ;
	
	@BeforeAll
	static void setupCommon() {
		mockData = new Stock();
		mockData.setStockSymbol("D05:SGX");
		mockData.setBidPrice(BigDecimal.valueOf(50.00));
		mockData.setAskPrice(BigDecimal.valueOf(70.00));
	}
	
	@Before
	public void setUp() {
		// given
		mockData = new Stock();
		mockData.setStockSymbol("D05:SGX");
		mockData.setBidPrice(BigDecimal.valueOf(50.00));
		mockData.setAskPrice(BigDecimal.valueOf(70.00));

		testEntityManager.persist(mockData);
	}

	@Test
	public void testInit() {
		assertNotNull(stockService);
		assertNotNull(testEntityManager);
		assertNotNull(stockRepositoryMock);
	}

	@Test
	public void findbyAll() {
		List<Stock> expected = stockRepositoryMock.findAll();
		int nOfCities = 0;
		assertThat(expected).hasSize(nOfCities);
	}

	@Test
	public void findById() {
		Optional<Stock> expectedStocks= Optional.of(mockData);
		Long id = 1l;
		 when(stockRepositoryMock.findById(id)).thenReturn(expectedStocks);

		Optional<Stock> actual = stockRepositoryMock.findById(id);

		assertEquals(expectedStocks, actual);
	}

}
