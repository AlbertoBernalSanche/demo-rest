package co.edu.usbcali.demo.service;

import java.util.List;

import co.edu.usbcali.demo.domain.ShoppingProduct;



public interface ShoppingProductService extends GenericService<ShoppingProduct, Integer> {
	public Long totalShoppingProductByShoppingCart(Integer carId);
	
	public void deleteShoppingProductsByShoppingCart(Integer carId);
	
	public List<ShoppingProduct> findShoppingProductByShoppingCart(Integer carId);
	
	public Integer findByCarIdAndProId(Integer carId, String proId);
}
