package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

@SpringBootTest
@Rollback(false)
class cartServiceTest {
	
	private  final static Logger log=LoggerFactory.getLogger(cartServiceTest.class);
	
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
	void noDebeCrearUnShoppingCartPorCustomerDisable()throws Exception {
		//Arrange
		String email="abeamondqq@harvard.edu";
		
		//Act
		assertThrows(Exception.class, ()->cartService.createCart(email));
		
	}
	
	@Test
	void debeAgregarProductShoppingCart()throws Exception {
		//arrange
		Integer carId=11;
		String proId="L380";
		Integer quantity=10;
		ShoppingProduct shoppingProduct=null;
		
		//act
		shoppingProduct=cartService.addProduct(carId, proId, quantity);
		
		//assert
		assertNotNull(shoppingProduct,"el shopping product es nulo");
		
	}
	
	@Test
	void debeBorrarTodosLosProductosDelCart()throws Exception {
		Integer carId=11;
		
		cartService.cleanCart(carId);
		
		
	}
	
	@Test
	void debeBorrarElShoppingProduct()throws Exception{
		
		Integer carId=11;
		String proId="APPL45";
		
		cartService.removeProduct(carId, proId);
		
	}
	
	@Test
	void debeMostraTodosLosProductosDelCart()throws Exception{
		Integer carId=11;
		
		cartService.findShoppingProductByShoppingCart(carId).forEach(shoppingProduct->{
			
			log.info("id de shoppig product "+shoppingProduct.getShprId().toString());
			log.info("id del producto "+shoppingProduct.getProduct().getProId());
			log.info("cantidad "+shoppingProduct.getQuantity().toString());
			log.info("total "+shoppingProduct.getTotal().toString());
			log.info("-------------------------");
			
		});
		
		
		
		
	}
	
	
	

}
