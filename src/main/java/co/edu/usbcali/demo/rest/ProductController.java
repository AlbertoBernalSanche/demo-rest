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

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.dto.ProductDTO;
import co.edu.usbcali.demo.dto.ShoppingCartDTO;
import co.edu.usbcali.demo.mapper.ProductMapper;
import co.edu.usbcali.demo.service.ProductService;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductMapper productMapper;
	
	private final static Logger log =LoggerFactory.getLogger(ProductController.class);
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody ProductDTO productDTO) throws Exception{
		
			Product product = productMapper.toProduct(productDTO);
			product=productService.save(product);
			productDTO=productMapper.toProductDTO(product);
			
			return ResponseEntity.ok().body(productDTO);
		
	}
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody ProductDTO productDTO) throws Exception{
		
			Product product = productMapper.toProduct(productDTO);
			product=productService.update(product);
			productDTO=productMapper.toProductDTO(product);
			
			return ResponseEntity.ok().body(productDTO);
		
	}
	
	@DeleteMapping("/delete/{proId}")
	public ResponseEntity<?> delete(@PathVariable("proId") String proId)throws Exception {
		
		
		
			productService.deleteById(proId);

			return ResponseEntity.ok().build();
			
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll()throws Exception{
		
			List<Product> products=productService.findAll();
			
			List<ProductDTO> productsDTO=productMapper.toProductsDTO(products);
			
			
			return ResponseEntity.ok().body(productsDTO);
		
	}
	
	
	@GetMapping("/findById/{proId}")
	public ResponseEntity<?> findById(@PathVariable("proId") String proId)throws Exception {
		
			Optional<Product> productOptional= productService.findById(proId);
			
			if(productOptional.isPresent()==false) {
				return ResponseEntity.ok().body("Product not found");
			}
			
			Product product=productOptional.get();
			
			ProductDTO productDTO=productMapper.toProductDTO(product);

			return ResponseEntity.ok().body(productDTO);
			
		
	}
	
	@GetMapping("/findProductAvalaible")
	public ResponseEntity<?> findProductAvalaible()throws Exception{
		
			List<Product> products=productService.findProductAvalaible();
			
			List<ProductDTO> productsDTO=productMapper.toProductsDTO(products);
			
			
			return ResponseEntity.ok().body(productsDTO);
		
	}
	
	@GetMapping("/findProductByWordAndPrice/{word}/{max}/{min}")
	public ResponseEntity<?> findProductByWordAndPrice(@PathVariable("word") String word, @PathVariable("max")Long max,@PathVariable("min") Long min) throws Exception {
		
		
		List<Product> products=productService.findProductByWordAndPrice(word, max, min);
		
		List<ProductDTO> productsDTO=productMapper.toProductsDTO(products);
		
		
		return ResponseEntity.ok().body(productsDTO);
		
	}
	
	@GetMapping("/findByNameContainsIgnoreCase/{word}")
	public ResponseEntity<?> findByNameContainsIgnoreCase(@PathVariable("word") String word) throws Exception {
		
		
		List<Product> products=productService.findByNameContainsIgnoreCase(word);
		
		List<ProductDTO> productsDTO=productMapper.toProductsDTO(products);
		
		
		return ResponseEntity.ok().body(productsDTO);
		
	}
}
