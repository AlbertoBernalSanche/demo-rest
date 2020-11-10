package co.edu.usbcali.demo.rest;

import co.edu.usbcali.demo.domain.*;
import co.edu.usbcali.demo.dto.ShoppingProductDTO;
import co.edu.usbcali.demo.mapper.ShoppingProductMapper;
import co.edu.usbcali.demo.service.ShoppingProductService;

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

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/shoppingProduct")
@CrossOrigin(origins = "*")

public class ShoppingProductController {
	@Autowired
	private ShoppingProductService shoppingProductService;
	@Autowired
	private ShoppingProductMapper shoppingProductMapper;

	@GetMapping(value = "/{shprId}")
	public ResponseEntity<?> findById(@PathVariable("shprId") Integer shprId) throws Exception {

		ShoppingProduct shoppingProduct = (shoppingProductService.findById(shprId).isPresent() == true)
				? shoppingProductService.findById(shprId).get()
				: null;

		return ResponseEntity.ok().body(shoppingProductMapper.shoppingProductToShoppingProductDTO(shoppingProduct));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {

		return ResponseEntity.ok().body(
				shoppingProductMapper.listShoppingProductToListShoppingProductDTO(shoppingProductService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody ShoppingProductDTO shoppingProductDTO) throws Exception {

		ShoppingProduct shoppingProduct = shoppingProductMapper.shoppingProductDTOToShoppingProduct(shoppingProductDTO);
		shoppingProduct = shoppingProductService.save(shoppingProduct);

		return ResponseEntity.ok().body(shoppingProductMapper.shoppingProductToShoppingProductDTO(shoppingProduct));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody ShoppingProductDTO shoppingProductDTO) throws Exception {

		ShoppingProduct shoppingProduct = shoppingProductMapper.shoppingProductDTOToShoppingProduct(shoppingProductDTO);
		shoppingProduct = shoppingProductService.update(shoppingProduct);

		return ResponseEntity.ok().body(shoppingProductMapper.shoppingProductToShoppingProductDTO(shoppingProduct));
	}

	@DeleteMapping(value = "/{shprId}")
	public ResponseEntity<?> delete(@PathVariable("shprId") Integer shprId) throws Exception {

		shoppingProductService.deleteById(shprId);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(shoppingProductService.count());
	}
}
