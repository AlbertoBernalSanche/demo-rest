package co.edu.usbcali.demo.service;

import java.util.List;

import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.domain.Product;

public interface PaymentMethodService extends GenericService<PaymentMethod, Integer>{

	public List<PaymentMethod> findPaymentMethodAvalaible();
}
