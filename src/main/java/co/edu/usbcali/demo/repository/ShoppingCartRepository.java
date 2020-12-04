package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

	@Query("SELECT cart FROM ShoppingCart cart WHERE cart.customer.email=:email and cart.paymentMethod.payId IS NUll")
	public ShoppingCart findShoppingCartAvailable(String email);
	
	@Query("SELECT shpr FROM ShoppingCart shpr WHERE shpr.customer.email=:email")
	public List<ShoppingCart> findShoppingCartByEmail(String email);
	
	
	//@Query("Update ShoppingCart cart SET cart.paymentMethod.payId:=payId WHERE cart.carId=:carId")
	//public ShoppingCart addPaymentMethod(Integer carId, Integer payId);
}
