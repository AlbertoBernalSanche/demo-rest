package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.dto.PaymentMethodDTO;

@Mapper
public interface PaymentMethodMapper {

	public PaymentMethodDTO toPaymentDTO(PaymentMethod payment);

	public PaymentMethod toPayment(PaymentMethodDTO paymentDTO);

	public List<PaymentMethodDTO> toPaymentMethodsDTO(List<PaymentMethod> paymets);

	public List<PaymentMethod> toPaymentsMedthods(List<PaymentMethodDTO> paymentsDTO);
}
