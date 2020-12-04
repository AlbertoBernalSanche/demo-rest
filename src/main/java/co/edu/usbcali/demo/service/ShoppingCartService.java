package co.edu.usbcali.demo.service;

import java.util.List;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

public interface ShoppingCartService extends GenericService<ShoppingCart, Integer>{
	
	public List<ShoppingCart> findShoppingCartByEmail(String email) throws Exception;

}
