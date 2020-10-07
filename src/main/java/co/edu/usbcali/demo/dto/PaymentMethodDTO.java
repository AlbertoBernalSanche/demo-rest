package co.edu.usbcali.demo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PaymentMethodDTO {

	
	@Positive
	private Integer payId;
	
	@NotNull
	@Size(min=1,max=1)
	private String enable;
	
	@NotNull
	@Size(min=1,max=255)
	private String name;

	public PaymentMethodDTO(@NotNull @Positive Integer payId, @NotNull @Size(min = 1, max = 1) String enable,
			@NotNull @Size(min = 1, max = 255) String name) {
		super();
		this.payId = payId;
		this.enable = enable;
		this.name = name;
	}

	public PaymentMethodDTO() {
		super();
	}

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
