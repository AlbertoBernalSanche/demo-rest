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


@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class CustomerServiceTest {

	private  final static Logger log=LoggerFactory.getLogger(CustomerServiceTest.class);
	private final static String email="abernals@correo.usbcali.edu.co";
	
	@Autowired
	CustomerService customerService;
	
	@Test
	@Order(1)
	void save()throws Exception {
		
		log.info("save");
		
		Customer customer=new Customer();
		customer.setAddress("Avenida siempre viva");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("Alberto Bernal");
		customer.setPhone("320 686 1460");
		customer.setToken("JNASIBFIJWE23R4");
		
		customerService.save(customer);
	}
	
	@Test
	@Order(2)
	void findById() throws Exception {
		
		log.info("find by id");
		
		Optional<Customer> customerOptional=customerService.findById(email);

		assertTrue(customerOptional.isPresent(),"el customer no existe");
	
		log.info(customerOptional.get().getName());
	}
	
	@Test
	@Order(3)
	void update() throws Exception {
		
		log.info("update");
		
		Optional<Customer> customerOptional=customerService.findById(email);

		assertTrue(customerOptional.isPresent(),"el customer no existe");
	
		Customer customer= customerOptional.get();
		
		customer.setEnable("N");
		
		customerService.update(customer);
		
		
	}
	
	@Test
	@Order(4)
	void delete() throws Exception {
		
		log.info("delete");
		
		Optional<Customer> customerOptional=customerService.findById(email);

		assertTrue(customerOptional.isPresent(),"el customer no existe");
	
		Customer customer= customerOptional.get();
		
		customerService.delete(customer);
		
		
	}
}
