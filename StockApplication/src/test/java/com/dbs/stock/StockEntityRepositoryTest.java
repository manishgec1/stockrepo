package com.dbs.stock;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.dbs.stock.repository.StockRepository;
import com.dbs.stock.service.StockService;
import com.dbs.stock.service.StockServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StockEntityRepositoryTest {

	@Mock
	private EntityManager entityManager;

	@Mock 
	private StockRepository stockRepository;
	
	@Mock 
	private StockService stockService;
	
	@InjectMocks
	private StockServiceImpl stockServiceImpl;

	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(entityManager).isNotNull();
		assertThat(stockRepository).isNotNull();
		assertThat(stockService).isNotNull();
		assertThat(stockServiceImpl).isNotNull();
	}
}