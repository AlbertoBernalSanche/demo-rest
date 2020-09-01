package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class CustomerRepositoryTest {

	private  final static Logger log=LoggerFactory.getLogger(CustomerRepositoryTest.class);
	private final static String email="abernals@correo.usbcali.edu.co";
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		
		log.info("save");
		
		Optional<Customer> customerOptional=customerRepository.findById(email);

		assertFalse(customerOptional.isPresent(),"el customer ya existe");
	
		Customer customer=new Customer();
		customer.setAddress("Avenida siempre viva");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("Alberto Bernal");
		customer.setPhone("320 686 1460");
		customer.setToken("JNASIBFIJWE23R4");
		
		customerRepository.save(customer);
	}
	

	@Test
	@Transactional
	@Order(2)
	void findById() {
		
		log.info("find by id");
		
		Optional<Customer> customerOptional=customerRepository.findById(email);

		assertTrue(customerOptional.isPresent(),"el customer no existe");
	
		log.info(customerOptional.get().getName());
	}
	

	@Test
	@Transactional
	@Order(3)
	void update() {
		
		log.info("update");
		
		Optional<Customer> customerOptional=customerRepository.findById(email);

		assertTrue(customerOptional.isPresent(),"el customer no existe");
	
		Customer customer= customerOptional.get();
		
		customer.setEnable("N");
		
		customerRepository.save(customer);
		
		
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		
		log.info("delete");
		
		Optional<Customer> customerOptional=customerRepository.findById(email);

		assertTrue(customerOptional.isPresent(),"el customer no existe");
	
		Customer customer= customerOptional.get();
		
		
		
		customerRepository.delete(customer);
		
		
	}
	
	@Test
	@Transactional
	@Order(5)
	void findAll() {
		
		customerRepository.findAll().forEach(customer->{
			log.info("name:"+customer.getName());
			log.info("email:"+customer.getEmail());
		});
		
		
	}
	
	@Test
	@Transactional
	@Order(6)
	void count() {
		
		log.info("count:"+customerRepository.count());
		
		
	}
	
	@Test
	@Transactional
	@Order(7)
	void findByEnableEmail() {
		
		List<Customer> customers=customerRepository.findByEnableAndEmail("Y", email);
		assertFalse(customers.isEmpty());
		
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());
		});
	}
	
	@Test
	@Transactional
	@Order(8)
	void findCustomerLikeMar() {
		
		List<Customer> customers=customerRepository.findCustomerLikemar();
		assertFalse(customers.isEmpty());
		
		customers.forEach(customer->{
			log.info("Name:"+customer.getName());
			log.info("Email:"+customer.getEmail());
		});
	}
}
