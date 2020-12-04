package co.edu.usbcali.demo.service;

import java.util.List;

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingProduct;

public interface ProductService extends GenericService<Product, String>{

	public List<Product> findProductAvalaible();
}
