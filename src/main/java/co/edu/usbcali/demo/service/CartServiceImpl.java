package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;


@Service
@Scope("singleton")
public class CartServiceImpl implements CartService{
	
	@Autowired
	CustomerService customerService;
	@Autowired
	ShoppingCartService shoppingCartService;
	@Autowired
	ProductService productService;
	@Autowired
	ShoppingProductService shoppingProductService;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingCart createCart(String email) throws Exception {
		Customer customer =null;
		
		if (email==null || email.isBlank()==true) {
			throw new Exception("el email del cliente es nulo");
		}
		
		Optional<Customer> customerOptional=customerService.findById(email);
		if (customerOptional.isPresent()==false) {
			throw new Exception("no existe un customer con el email:"+email);
		}
		
		customer=customerOptional.get();
		
		if(customer.getEnable()==null || customer.getEnable().equals("N")==true) {
			throw new Exception("El cliente con email: "+email+" no esta habilitado");
		}
		
		ShoppingCart shoppingCart=new ShoppingCart(0, customer, null,0, 0L, "Y", null);
		
		shoppingCart=shoppingCartService.save(shoppingCart);
		
		return shoppingCart;
	}
	

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingProduct addProduct(Integer carId, String proId, Integer quantity) throws Exception {
		ShoppingCart shoppingCart=null;
		Product product=null;
		Long totalShoppingProduct=0L;
		Long totalShoppingCart=0L;
		if (carId==null||carId<=0) {
			throw new Exception("el cartId es nulo");
			
		}
		if (proId==null||proId.isBlank()==true) {
			throw new Exception("el proId es nulo");
		}
		if (quantity==null||quantity<=0) {
			throw new Exception("el quantity es nulo");
			
		}
		if (shoppingCartService.findById(carId).isPresent()==false) {
			throw new Exception("el shopping cart esta inhabilitado");
		}
		shoppingCart=shoppingCartService.findById(carId).get();
		if (shoppingCart.getEnable().equals("N")==true) {
			throw new Exception("el shopping cart esta inhabilitado");
		}
		if (productService.findById(proId).isPresent()==false) {
			throw new Exception("el product no existe");
		}
		product=productService.findById(proId).get();
		if (product.getEnable().equals("N")==true) {
			throw new Exception("el product esta inhabilitado");
		}
		totalShoppingProduct=Long.valueOf(product.getPrice()*quantity);
		
		ShoppingProduct shoppingProduct=new ShoppingProduct();
		shoppingProduct.setProduct(product);
		shoppingProduct.setQuantity(quantity);
		shoppingProduct.setShoppingCart(shoppingCart);
		shoppingProduct.setShprId(0);
		totalShoppingProduct=Long.valueOf(product.getPrice()*quantity);
		shoppingProduct.setTotal(totalShoppingProduct);
		
		shoppingProduct=shoppingProductService.save(shoppingProduct);
		
		totalShoppingCart=shoppingProductService.totalShoppingProductByShoppingCart(carId);
		
		shoppingCart.setTotal(totalShoppingCart);
		shoppingCartService.update(shoppingCart);
		
		return shoppingProduct;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeProduct(Integer carId, String proId) throws Exception {
		
		ShoppingCart shoppingCart=null;
		Product product=null;
		Integer shprId=null;
		
		if (carId==null||carId<=0) {
			throw new Exception("el cartId es nulo");
			
		}
		if (shoppingCartService.findById(carId).isPresent()==false) {
			throw new Exception("el shopping cart esta inhabilitado");
		}
		shoppingCart=shoppingCartService.findById(carId).get();
		if (shoppingCart.getEnable().equals("N")==true) {
			throw new Exception("el shopping cart esta inhabilitado");
		}
		
		if (proId==null||proId.isBlank()==true) {
			throw new Exception("el proId es nulo");
		}
		if (productService.findById(proId).isPresent()==false) {
			throw new Exception("el product no existe");
		}
		product=productService.findById(proId).get();
		if (product.getEnable().equals("N")==true) {
			throw new Exception("el product esta inhabilitado");
		}
		 
		shprId=shoppingProductService.findByCarIdAndProId(carId, proId);
		
		shoppingProductService.deleteById(shprId);
		
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void cleanCart(Integer carId) throws Exception {
		
		ShoppingCart shoppingCart=null;
		if (carId==null||carId<=0) {
			throw new Exception("el cartId es nulo");
			
		}
		if (shoppingCartService.findById(carId).isPresent()==false) {
			throw new Exception("el shopping cart esta inhabilitado");
		}
		shoppingCart=shoppingCartService.findById(carId).get();
		if (shoppingCart.getEnable().equals("N")==true) {
			throw new Exception("el shopping cart esta inhabilitado");
		}
		
		shoppingProductService.deleteShoppingProductsByShoppingCart(carId);
		
		
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<ShoppingProduct> findShoppingProductByShoppingCart(Integer carId) throws Exception {
		
		ShoppingCart shoppingCart=null;
		
		//List<ShoppingProduct> listShoppingProduct=null;
		
		if (carId==null||carId<=0) {
			throw new Exception("el cartId es nulo");
			
		}
		if (shoppingCartService.findById(carId).isPresent()==false) {
			throw new Exception("el shopping cart esta inhabilitado");
		}
		shoppingCart=shoppingCartService.findById(carId).get();
		if (shoppingCart.getEnable().equals("N")==true) {
			throw new Exception("el shopping cart esta inhabilitado");
		}
		
		
		
		return shoppingProductService.findShoppingProductByShoppingCart(carId);
	}

}