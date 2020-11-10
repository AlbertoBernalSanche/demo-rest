package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Rollback(false)
class ShoppinProductServiceTest {
	
	@Autowired
	ShoppingProductService shoppingProductService;

	@Test
	void test() {
		Long total=0L;
		Integer carId=11;
		
		total=shoppingProductService.totalShoppingProductByShoppingCart(carId);
		
		assertTrue(total>0);
	}

}
