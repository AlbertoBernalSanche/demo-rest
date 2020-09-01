package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
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
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Product;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ProductRepositoryTest {

	private final static String proId = "100";

	private final static Logger log = LoggerFactory.getLogger(ProductRepositoryTest.class);

	@Autowired
	ProductRepository productRepository;

	@Test
	@Transactional
	@Order(1)
	void save() {

		log.info("save");

		Optional<Product> productOptional = productRepository.findById(proId);

		assertFalse(productOptional.isPresent(), "el producto ya existe");

		Product product = new Product();
		product.setDetail("Huawei mate 10");
		product.setEnable("Y");
		product.setImage("link image");
		product.setName("Celular Huawei");
		product.setPrice(Integer.toUnsignedLong(150000));
		product.setProId(proId);

		productRepository.save(product);
	}

	@Test
	@Transactional
	@Order(2)
	void findById() {

		log.info("find by id");

		Optional<Product> productOptional = productRepository.findById(proId);

		assertTrue(productOptional.isPresent(), "el producto no existe");

		log.info("el nombre del producto: " + productOptional.getClass().getName());
	}

	@Test
	@Transactional
	@Order(3)
	void update() {

		log.info("update");

		Optional<Product> productOptional = productRepository.findById(proId);

		assertTrue(productOptional.isPresent(), "el producto no existe");

		Product product = productOptional.get();
		product.setEnable("N");
		productRepository.save(product);
	}

	@Test
	@Transactional
	@Order(11)
	void delete() {

		log.info("delete");

		Optional<Product> productOptional = productRepository.findById(proId);

		assertTrue(productOptional.isPresent(), "el producto no existe");

		Product product = productOptional.get();

		productRepository.delete(product);
	}

	@Test
	@Transactional
	@Order(5)
	void findAll() {

		log.info("find all");

		productRepository.findAll().forEach(product -> {
			log.info("id producto:" + product.getProId());
			log.info("nombre producto:" + product.getName());
		});
	}

	@Test
	@Transactional
	@Order(6)
	void count() {

		log.info("count:" + productRepository.count());

	}

	@Test
	@Transactional
	@Order(7)
	void findByEnableId() {

		List<Product> products = productRepository.findByEnableAndProId("Y", proId);
		assertFalse(products.isEmpty());

		products.forEach(product -> {
			log.info("id producto:" + product.getProId());
			log.info("nombre producto:" + product.getName());
			log.info("enable:" + product.getEnable());
		});

	}

	@Test
	@Transactional
	@Order(8)
	void findByProductPriceGreaterThan() {

		List<Product> products = productRepository.findByPriceGreaterThan(Long.valueOf(160000));
		assertFalse(products.isEmpty());

		products.forEach(product -> {
			log.info("id producto:" + product.getProId());
			log.info("nombre producto:" + product.getName());
			log.info("price:" + product.getPrice());
		});

	}

	@Test
	@Transactional
	@Order(9)
	void findByProductPriceLessThan() {

		List<Product> products = productRepository.findByPriceLessThan(160000L);
		assertFalse(products.isEmpty());

		products.forEach(product -> {
			log.info("id producto:" + product.getProId());
			log.info("nombre producto:" + product.getName());
			log.info("price:" + product.getPrice());
		});

	}

	@Test
	@Transactional
	@Order(10)
	void findByNameContainsIgnoreCase() {

		List<Product> products = productRepository.findByNameContainsIgnoreCase("H");
		assertFalse(products.isEmpty());

		products.forEach(product -> {
			log.info("id producto:" + product.getProId());
			log.info("nombre producto:" + product.getName());
			log.info("price:" + product.getPrice());
		});

	}
	
	@Test
	@Transactional
	@Order(4)
	void findByNameStartingWith() {

		List<Product> products = productRepository.findByNameStartingWith("i");
		assertFalse(products.isEmpty());

		products.forEach(product -> {
			log.info("id producto:" + product.getProId());
			log.info("nombre producto:" + product.getName());
			log.info("price:" + product.getPrice());
		});

	}

}
