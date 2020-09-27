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

import co.edu.usbcali.demo.domain.PaymentMethod;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class PaymentMethodServiceTest {

	private final static Logger log = LoggerFactory.getLogger(PaymentMethodServiceTest.class);
	private static Integer payId = null;

	@Autowired
	PaymentMethodService paymentMethodService;

	@Test
	@Order(1)
	void save() throws Exception {

		log.info("save");

		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setEnable("Y");
		paymentMethod.setName("EFECTY");
		
		

		paymentMethod = paymentMethodService.save(paymentMethod);
		payId = paymentMethod.getPayId();
		assertNotNull(payId, "el payId es nulo");
	}

	@Test
	@Order(2)
	void findById() throws Exception {

		log.info("find by id");

		Optional<PaymentMethod> paymentOptional = paymentMethodService.findById(payId);

		assertTrue(paymentOptional.isPresent(), "el payment no existe");

		log.info(paymentOptional.get().getName());
	}

	@Test
	@Order(3)
	void update() throws Exception {

		log.info("update");

		Optional<PaymentMethod> paymentOptional = paymentMethodService.findById(payId);

		assertTrue(paymentOptional.isPresent(), "el payment no existe");

		PaymentMethod payment = paymentOptional.get();
		payment.setEnable("N");

		paymentMethodService.update(payment);

	}

	@Test
	@Order(4)
	void delete() throws Exception {

		log.info("delete");

		Optional<PaymentMethod> paymentOptional = paymentMethodService.findById(payId);

		assertTrue(paymentOptional.isPresent(), "el payment no existe");

		PaymentMethod payment = paymentOptional.get();

		paymentMethodService.delete(payment);

	}
}
