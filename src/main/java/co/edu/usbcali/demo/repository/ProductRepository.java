package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbcali.demo.domain.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

	public List<Product> findByEnableAndProId(String enable,String Id);
	
	public List<Product> findByPriceGreaterThan(Long price);
	
	public List<Product> findByPriceLessThan(Long price);
	
	public List<Product> findByNameContainsIgnoreCase(String word);
	
	public List<Product> findByNameStartingWith(String word);
	
}
