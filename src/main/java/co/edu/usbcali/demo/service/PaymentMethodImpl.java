package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.repository.PaymentMethodRepository;

@Service
@Scope("singleton")
public class PaymentMethodImpl implements PaymentMethodService {
	
	@Autowired
	PaymentMethodRepository paymentMethodRepository;

	@Override
	@Transactional(readOnly = true)
	public List<PaymentMethod> findAll() {
		
		return paymentMethodRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PaymentMethod save(PaymentMethod entity) throws Exception {
		validate(entity);

		return paymentMethodRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public PaymentMethod update(PaymentMethod entity) throws Exception {
		validate(entity);
		if (paymentMethodRepository.existsById(entity.getPayId())==false) {
			throw new Exception("el paymentMethod con id:"+entity.getPayId()+" no existe");
		}
		return paymentMethodRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(PaymentMethod entity) throws Exception {
		if(entity == null) {
			throw new Exception("el paymentMethod esta vacio");
		}
		if(entity.getPayId() == null || entity.getPayId()<1) {
			throw new Exception("el PayId es obligatorio");
		}
		if (paymentMethodRepository.existsById(entity.getPayId())==false) {
			throw new Exception("el paymentMethod con id:"+entity.getPayId()+" no existe");
		}
		paymentMethodRepository.findById(entity.getPayId()).ifPresent(payment->{
			if(payment.getShoppingCarts()!=null && payment.getShoppingCarts().isEmpty()==false) {
				throw new RuntimeException("el paymentMethod con id:"+entity.getPayId()+" no se puede borrar tiene shoppingcarts");
			}
		});
		paymentMethodRepository.deleteById(entity.getPayId());
		
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		if (id==null || id<1) {
			throw new Exception("el PayId es obligatorio");
		}
		if (paymentMethodRepository.existsById(id)) {
			delete(paymentMethodRepository.findById(id).get());
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PaymentMethod> findById(Integer id) throws Exception {
		
		return paymentMethodRepository.findById(id);
	}

	@Override
	public void validate(PaymentMethod entity) throws Exception {
		if(entity == null) {
			throw new Exception("el paymentMethod esta vacio");
		}
		
		if(entity.getName() == null || entity.getName().isBlank()==true) {
			throw new Exception("el name es obligatorio");
		}
		if(entity.getEnable() == null || entity.getEnable().isBlank()==true) {
			throw new Exception("el enable es obligatorio");
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		
		return paymentMethodRepository.count();
	}

	@Override
	public List<PaymentMethod> findPaymentMethodAvalaible() {
		
		return paymentMethodRepository.findPaymentMethodAvalaible();
	}

}
