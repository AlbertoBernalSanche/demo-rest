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
import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.dto.PaymentMethodDTO;
import co.edu.usbcali.demo.dto.ShoppingCartDTO;
import co.edu.usbcali.demo.mapper.ShoppingCartMapper;
import co.edu.usbcali.demo.service.ShoppingCartService;

@RestController
@RequestMapping("/api/shoppingCart")
@CrossOrigin("*")
public class ShoppingCartController {

	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	ShoppingCartMapper shoppingCartMapper;
	
	private final static Logger log = LoggerFactory.getLogger(ShoppingCartController.class);
	
	//------------------------------------------------------------
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody ShoppingCartDTO shoppingCartDTO) throws Exception{
		
		ShoppingCart shoppingCart = shoppingCartMapper.shoppingCartDTOToShoppingCart(shoppingCartDTO);
		shoppingCart = shoppingCartService.save(shoppingCart);

		return ResponseEntity.ok().body(shoppingCartMapper.shoppingCartToShoppingCartDTO(shoppingCart));
	}
	
	//------------------------------------------------------------
	
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody ShoppingCartDTO shoppingCartDTO) throws Exception {

		ShoppingCart shoppingCart = shoppingCartMapper.shoppingCartDTOToShoppingCart(shoppingCartDTO);
		shoppingCart = shoppingCartService.update(shoppingCart);

		return ResponseEntity.ok().body(shoppingCartMapper.shoppingCartToShoppingCartDTO(shoppingCart));
	}
	
	//------------------------------------------------------------
	
	
	@DeleteMapping("/delete/{carId}")
	public ResponseEntity<?> delete(@PathVariable("carId") Integer carId)throws Exception {
		
		
		
			shoppingCartService.deleteById(carId);

			return ResponseEntity.ok().build();
			
	}
	
	//------------------------------------------------------------
	
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll()throws Exception{
		
		return ResponseEntity.ok()
				.body(shoppingCartMapper.listShoppingCartToListShoppingCartDTO(shoppingCartService.findAll()));
	}
	
	//------------------------------------------------------------
	
	
	@GetMapping("/findById/{carId}")
	public ResponseEntity<?> findById(@PathVariable("carId") Integer carId)throws Exception {
		
		ShoppingCart shoppingCart = (shoppingCartService.findById(carId).isPresent() == true)
				? shoppingCartService.findById(carId).get()
				: null;

		return ResponseEntity.ok().body(shoppingCartMapper.shoppingCartToShoppingCartDTO(shoppingCart));
			
		
	}
}
