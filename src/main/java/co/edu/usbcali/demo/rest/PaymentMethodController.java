package co.edu.usbcali.demo.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.dto.PaymentMethodDTO;
import co.edu.usbcali.demo.dto.ProductDTO;
import co.edu.usbcali.demo.mapper.PaymentMethodMapper;
import co.edu.usbcali.demo.service.PaymentMethodService;

@RestController
@RequestMapping("/api/paymentMethod")
@CrossOrigin("*")
public class PaymentMethodController {

	@Autowired
	PaymentMethodMapper paymentMethodMapper;
	
	@Autowired
	PaymentMethodService paymentMethodService;
	
	private final static Logger log = LoggerFactory.getLogger(PaymentMethodController.class);
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody PaymentMethodDTO paymentDTO) throws Exception{
		
			PaymentMethod payment = paymentMethodMapper.toPayment(paymentDTO);
			payment=paymentMethodService.save(payment);
			paymentDTO=paymentMethodMapper.toPaymentDTO(payment);
			
			return ResponseEntity.ok().body(paymentDTO);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody PaymentMethodDTO paymentDTO) throws Exception{
		
		PaymentMethod payment = paymentMethodMapper.toPayment(paymentDTO);
		payment=paymentMethodService.update(payment);
		paymentDTO=paymentMethodMapper.toPaymentDTO(payment);
		
		return ResponseEntity.ok().body(paymentDTO);
		
	}
	
	@DeleteMapping("/delete/{payId}")
	public ResponseEntity<?> delete(@PathVariable("payId") Integer payId)throws Exception {
		
		
		
			paymentMethodService.deleteById(payId);

			return ResponseEntity.ok().build();
			
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll()throws Exception{
		
			List<PaymentMethod> paymentMethods=paymentMethodService.findAll();
			
			List<PaymentMethodDTO> paymentMethodsDTO=paymentMethodMapper.toPaymentMethodsDTO(paymentMethods);
			
			
			return ResponseEntity.ok().body(paymentMethodsDTO);
		
	}
	
	
	@GetMapping("/findById/{payId}")
	public ResponseEntity<?> findById(@PathVariable("payId") Integer payId)throws Exception {
		
			Optional<PaymentMethod> paymentMethodOptional= paymentMethodService.findById(payId);
			
			if(paymentMethodOptional.isPresent()==false) {
				return ResponseEntity.ok().body("PaymentMethods not found");
			}
			
			PaymentMethod payment=paymentMethodOptional.get();
			
			PaymentMethodDTO paymentMethodDTO=paymentMethodMapper.toPaymentDTO(payment);

			return ResponseEntity.ok().body(paymentMethodDTO);
			
		
	}
	
	@GetMapping("/findPaymentMethodAvalaible")
	public ResponseEntity<?> findPaymentMethodAvalaible()throws Exception{
		
			List<PaymentMethod> payments=paymentMethodService.findPaymentMethodAvalaible();
			
			List<PaymentMethodDTO> paymentsDTO=paymentMethodMapper.toPaymentMethodsDTO(payments);
			
			
			return ResponseEntity.ok().body(paymentsDTO);
		
	}
}
