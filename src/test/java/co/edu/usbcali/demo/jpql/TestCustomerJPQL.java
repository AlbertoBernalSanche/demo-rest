package co.edu.usbcali.demo.jpql;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import co.edu.usbcali.demo.domain.Customer;

@SpringBootTest

class TestCustomerJPQL {
	
	private final static Logger log=LoggerFactory.getLogger(TestCustomerJPQL.class);
	

	@Autowired
	EntityManager entityManager;
	
	@BeforeEach
	public void beforeEach() {
		log.info("before all");
		assertNotNull(entityManager, "el entity manager es nulo");
	}
	
	
	@Test
	public void selectLike() {
		log.info("select all");
		String jpql="SELECT cus FROM Customer cus WHERE cus.name like '%a%'";
		List<Customer>customers=entityManager.createQuery(jpql, Customer.class).getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
		});	
	}
	
	@Test
	public void selectWhereEnable() {
		log.info("select all");
		String jpql="SELECT cus FROM Customer cus WHERE cus.enable='Y'";
		List<Customer>customers=entityManager.createQuery(jpql, Customer.class).getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
			log.info(customer.getEnable());
		});	
	}
	@Test
	public void selectWhereParam() {
		log.info("select all");
		String jpql="SELECT cus FROM Customer cus WHERE cus.enable=:enable and cus.email=:email";
		List<Customer>customers=entityManager.
				createQuery(jpql, Customer.class).
				setParameter("enable", "Y").
				setParameter("email", "kfibbings1@people.com.cn").
				getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
			log.info(customer.getEnable());
		});	
	}
	@Test
	public void selectAll() {
		log.info("select all");
		String jpql="SELECT cus FROM Customer cus";
		List<Customer>customers=entityManager.createQuery(jpql, Customer.class).getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
		});	
	
		
		
		
		
		
		
		
		
		
		
		
		//assertNull(entityManager,"el entity manager es nulo");
	}

}
