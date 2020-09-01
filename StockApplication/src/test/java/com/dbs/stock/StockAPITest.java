package com.dbs.stock;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;

import com.dbs.stock.model.Stock;
import com.dbs.stock.service.StockService;


@ExtendWith({SpringExtension.class})
@WebMvcTest
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StockAPITest {
		
	@Autowired
	private MockMvc mockMvc;

	@Mock
	private StockService stockService;

	private static Stock mockData ;
	
	@Mock
	private static ResultHandler printResponse = mvcResult -> {
		System.out.println(mvcResult.getResponse().getContentAsString());
	};


	@BeforeAll
	static void setupCommon() {
		mockData = new Stock();
		mockData.setStockSymbol("D05:SGX");
		mockData.setBidPrice(BigDecimal.valueOf(50.00));
		mockData.setAskPrice(BigDecimal.valueOf(70.00));
	}
	
	@Test
   public void testInit() {
        assertNotNull(stockService);
    
    }
		
	
}
