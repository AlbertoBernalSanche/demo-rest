package co.edu.usbcali.demo.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;
import co.edu.usbcali.demo.dto.AddPaymentMethodDTO;
import co.edu.usbcali.demo.dto.AddProductDTO;
import co.edu.usbcali.demo.dto.CleanCartDTO;
import co.edu.usbcali.demo.dto.CreateCartDTO;
import co.edu.usbcali.demo.dto.RemoveProductDTO;
import co.edu.usbcali.demo.dto.ShoppingCartDTO;
import co.edu.usbcali.demo.dto.ShoppingProductDTO;
import co.edu.usbcali.demo.mapper.ShoppingCartMapper;
import co.edu.usbcali.demo.mapper.ShoppingProductMapper;
import co.edu.usbcali.demo.service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("*")
public class CartServiceController {

	@Autowired
	CartService cartService;
	
	@Autowired
	ShoppingCartMapper shoppingCartMapper;
	
	@Autowired
	ShoppingProductMapper shoppingProductMapper;
	
	@PostMapping("/createCart")
	public ResponseEntity<?> createCart(@RequestBody @Valid CreateCartDTO createCartDTO) throws Exception{
		ShoppingCart shoppingCart=cartService.createCart(createCartDTO.getEmail());
		ShoppingCartDTO shoppingCartDTO=shoppingCartMapper.shoppingCartToShoppingCartDTO(shoppingCart);
		return ResponseEntity.ok().body(shoppingCartDTO);
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<?> addProduct(@RequestBody @Valid AddProductDTO addProductDTO) throws Exception {
		ShoppingProduct shoppingProduct=cartService.addProduct(addProductDTO.getCarId(), addProductDTO.getProId(), addProductDTO.getQuantity());
		ShoppingProductDTO shoppingProductDTO=shoppingProductMapper.shoppingProductToShoppingProductDTO(shoppingProduct);
		return ResponseEntity.ok().body(shoppingProductDTO);
	}
	
	@PostMapping("/removeProduct")
	public ResponseEntity<?> removeProduct(@RequestBody @Valid RemoveProductDTO removeProductDTO) throws Exception{
		cartService.removeProduct(removeProductDTO.getCarId(), removeProductDTO.getProId());
		return ResponseEntity.ok().body("");
	}
	
	@PostMapping("/clearCart")
	public ResponseEntity<?> clearCart(@RequestBody @Valid CleanCartDTO clearCartDTO) throws Exception{
		cartService.cleanCart(clearCartDTO.getCarId());
		return ResponseEntity.ok().body("");
	}
	
	@GetMapping("/findShoppingProductByShoppingCart/{carId}")
	public ResponseEntity<?> findShoppingProductByShoppingCart(@PathVariable("carId") Integer carId) throws Exception {
		List<ShoppingProduct> shoppingProducts=cartService.findShoppingProductByShoppingCart(carId);
		List<ShoppingProductDTO> shoppingProductsDtos=shoppingProductMapper.listShoppingProductToListShoppingProductDTO(shoppingProducts);
		return ResponseEntity.ok().body(shoppingProductsDtos);
	}
	
	@GetMapping("/findShoppingCartAvailable/{email}")
	public ResponseEntity<?> findShoppingCartAvailable(@PathVariable("email") String email) throws Exception {
		
		ShoppingCart shoppingCart=cartService.findShoppingCartAvailable(email);
		ShoppingCartDTO shoppingCartDTO=shoppingCartMapper.shoppingCartToShoppingCartDTO(shoppingCart);
		
		return ResponseEntity.ok().body(shoppingCartDTO);
	}
	
	/*@PostMapping("/addPaymentMethod")
	public ResponseEntity<?> addPaymentMethod(@RequestBody @Valid AddPaymentMethodDTO addPaymentMethodDTO) throws Exception {
		ShoppingCart shoppingCart=cartService.addPaymentMethod(addPaymentMethodDTO.getCarId(), addPaymentMethodDTO.getPayId());
		ShoppingCartDTO shoppingCartDTO=shoppingCartMapper.toShoppingCartDTO(shoppingCart);
		return ResponseEntity.ok().body(shoppingCartDTO);
	}*/
	
	
}
