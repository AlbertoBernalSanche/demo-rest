package co.edu.usbcali.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddPaymentMethodDTO {
	
	@NotNull
	private Integer carId;
	@NotNull
	private Integer payId;
	public AddPaymentMethodDTO(@NotNull Integer carId, @NotNull Integer payId) {
		super();
		this.carId = carId;
		this.payId = payId;
	}
	public AddPaymentMethodDTO() {
		super();
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public Integer getPayId() {
		return payId;
	}
	public void setPayId(Integer payId) {
		this.payId = payId;
	}
	

}
