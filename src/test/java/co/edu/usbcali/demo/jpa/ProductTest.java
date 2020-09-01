package co.edu.usbcali.demo.jpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Product;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ProductTest {

	
	private final static String proId="1520";
	
	private final static Logger log=LoggerFactory.getLogger(ProductTest.class);
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		assertNotNull(entityManager, "el entity manager es nulo");
		Product product=entityManager.find(Product.class, proId);
		
		assertNull(product,"el producto con id "+proId+" ya existe");
		
		product=new Product();
		product.setDetail("pc marca Acer");
		product.setEnable("Y");
		product.setImage("link image");
		product.setName("Acer pc");
		product.setPrice(Integer.toUnsignedLong(150000));
		product.setProId(proId);
		
		entityManager.persist(product);
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		assertNotNull(entityManager, "el entity manager es nulo");
		Product product=entityManager.find(Product.class, proId);
		
		assertNotNull(product,"el producto con id "+proId+" ya existe");
		
		
		log.info(product.getName());
	}
	
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		assertNotNull(entityManager, "el entity manager es nulo");
		Product product=entityManager.find(Product.class, proId);
		
		assertNotNull(product,"el producto con id "+proId+" no existe");
		
		product.setEnable("N");
		
		entityManager.merge(product);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		assertNotNull(entityManager, "el entity manager es nulo");
		Product product=entityManager.find(Product.class, proId);
		
		assertNotNull(product,"el producto con id "+proId+" no existe");
		
		entityManager.remove(product);
		
		
	}
}
