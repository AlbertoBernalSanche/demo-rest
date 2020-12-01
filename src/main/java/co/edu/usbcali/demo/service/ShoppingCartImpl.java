package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.repository.CustomerRepository;
import co.edu.usbcali.demo.repository.PaymentMethodRepository;
import co.edu.usbcali.demo.repository.ShoppingCartRepository;

@Service
@Scope("singleton")
public class ShoppingCartImpl implements ShoppingCartService{
	
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	PaymentMethodRepository paymentMethodRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	@Transactional(readOnly = true)
	public List<ShoppingCart> findAll() {
		
		return shoppingCartRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingCart save(ShoppingCart entity) throws Exception {
		validate(entity);
		
		if (customerRepository.existsById(entity.getCustomer().getEmail())==false) {
			throw new Exception("el customer con email:"+entity.getCustomer().getEmail()+" no existe");
		}
		
		return shoppingCartRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingCart update(ShoppingCart entity) throws Exception {

		//log.debug("updating ShoppingCart instance");

		if (entity == null) {
			throw new Exception("ShoppingCart nulo");
		}

		validate(entity);

		if (shoppingCartRepository.existsById(entity.getCarId()) == false) {
			throw new Exception("No existe un ShoppingCart con Id:"+entity.getCarId());
		}

		return shoppingCartRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(ShoppingCart entity) throws Exception {
		if(entity == null) {
			throw new Exception("el shoppingCart esta vacio");
		}
		if(entity.getCarId() == null || entity.getCarId()<1) {
			throw new Exception("el cartId es obligatorio");
		}
		if (shoppingCartRepository.existsById(entity.getCarId())==false) {
			throw new Exception("el shoppingCart con id:"+entity.getCarId()+" no existe");
		}
		shoppingCartRepository.findById(entity.getCarId()).ifPresent(shoppingCart->{
			if(shoppingCart.getShoppingProducts()!=null && shoppingCart.getShoppingProducts().isEmpty()==false) {
				throw new RuntimeException("el shoppingcarts con id:"+entity.getCarId()+" no se puede borrar tiene shoppingProducts");
			}
		});
		shoppingCartRepository.deleteById(entity.getCarId());
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		if (id==null || id<1) {
			throw new Exception("el carId es obligatorio");
		}
		if (shoppingCartRepository.existsById(id)) {
			delete(shoppingCartRepository.findById(id).get());
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ShoppingCart> findById(Integer id) throws Exception {
		
		return shoppingCartRepository.findById(id);
	}

	@Override

	public void validate(ShoppingCart entity) throws Exception {
		if(entity == null) {
			throw new Exception("el shopping cart esta vacio");
		}
		if(entity.getEnable()==null || entity.getEnable().isBlank()==true) {
			throw new Exception("el enable es obligatorio");
		}
		if(entity.getItems()==null || entity.getItems()<0) {
			throw new Exception("el items es obligatorio");
		}
		if(entity.getTotal()==null || entity.getTotal()<0) {
			throw new Exception("el total es obligatorio");
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
	
		return shoppingCartRepository.count();
	}

}
