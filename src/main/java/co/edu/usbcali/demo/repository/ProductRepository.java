package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

	public List<Product> findByEnableAndProId(String enable,String Id);
	
	public List<Product> findByPriceGreaterThan(Long price);
	
	public List<Product> findByPriceLessThan(Long price);
	
	public List<Product> findByNameContainsIgnoreCase(String word);
	
	@Query("SELECT prod from Product prod WHERE UPPER( prod.name) LIKE UPPER(CONCAT('%',:word,'%')) AND prod.enable='Y'")
	public List<Product> findByNameContainsIgnoreCaseAndEnable(String word);
	
	public List<Product> findByNameStartingWith(String word);
	
	@Query("SELECT prod FROM Product prod WHERE prod.enable='Y' ")
	public List<Product> findProductAvalaible();
	
	@Query("SELECT prod from Product prod WHERE  prod.price>=:min AND prod.price<=:max AND prod.name LIKE %:word%")
	public List<Product> findProductByWordAndPrice(String word,Long max, Long min);
	
	//cambios
}
