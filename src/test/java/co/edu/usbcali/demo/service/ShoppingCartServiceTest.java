package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.repository.CustomerRepository;
import co.edu.usbcali.demo.repository.PaymentMethodRepository;


@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ShoppingCartServiceTest {

	private final static Logger log = LoggerFactory.getLogger(ShoppingCartServiceTest.class);
	private static Integer carId = null;
	private Integer payId=1;
	private static String email="abeamondqq@harvard.edu";
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	PaymentMethodService paymentMethodService;
	
	@Autowired
	CustomerService customerService;

	
	@Test
	@Order(1)
	void save() throws Exception {

		log.info("save");

		ShoppingCart shoppingCart= new ShoppingCart();
		shoppingCart.setEnable("Y");
		shoppingCart.setItems(10);
		shoppingCart.setTotal(1000000L);
		Optional<Customer> customerOptional=customerService.findById(email);
		assertTrue(customerOptional.isPresent(),"el customer "+email+" no existe");
		
		Customer customer=customerOptional.get();
		shoppingCart.setCustomer(customer);
		
		Optional<PaymentMethod> paymentMethodOptional=paymentMethodService.findById(payId);
		assertTrue(paymentMethodOptional.isPresent(),"el metodo de pago "+payId+" no existe");
		
		PaymentMethod paymentMethod=paymentMethodOptional.get();
		shoppingCart.setPaymentMethod(paymentMethod);
		
		

		shoppingCart = shoppingCartService.save(shoppingCart);
		carId = shoppingCart.getCarId();
		assertNotNull(carId, "el payId es nulo");
	}

	@Test
	@Order(2)
	void findById() throws Exception {

		log.info("find by id");

		Optional<ShoppingCart> shoppingOptional = shoppingCartService.findById(carId);

		assertTrue(shoppingOptional.isPresent(), "el shoppingCart no existe");

	
	}

	@Test
	@Order(3)
	void update() throws Exception {

		log.info("update");

		Optional<ShoppingCart> shoppingOptional = shoppingCartService.findById(carId);

		assertTrue(shoppingOptional.isPresent(), "el shoppingCart no existe");

		ShoppingCart shopping = shoppingOptional.get();
		shopping.setEnable("N");

		shoppingCartService.update(shopping);

	}

	@Test
	@Order(4)
	void delete() throws Exception {

		log.info("delete");

		Optional<ShoppingCart> shoppingOptional = shoppingCartService.findById(carId);

		assertTrue(shoppingOptional.isPresent(), "el shoppingCart no existe");


		ShoppingCart shopping = shoppingOptional.get();

		shoppingCartService.delete(shopping);

	}
}
