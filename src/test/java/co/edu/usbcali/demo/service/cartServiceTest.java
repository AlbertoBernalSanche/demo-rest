package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

@SpringBootTest
@Rollback(false)
class cartServiceTest {
	
	@Autowired
	CartService cartService;

	@Test
	void debeCrearUnShoppingCart()throws Exception {
		//arrange
		String email="abaglowbn@furl.net";
		
		ShoppingCart shoppingCart=null;
		//act
		
		shoppingCart=cartService.createCart(email);
		
		//assert
		assertNotNull(shoppingCart);
	}
	
	@Test
	void debeAgregarProductShoppingCart()throws Exception {
		//arrange
		Integer carId=11;
		String proId="APPL45";
		Integer quantity=10;
		ShoppingProduct shoppingProduct=null;
		
		//act
		shoppingProduct=cartService.addProduct(carId, proId, quantity);
		
		//assert
		assertNotNull(shoppingProduct,"el shopping product es nulo");
		
	}
	
	
	

}
