package co.edu.usbcali.demo.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.domain.PaymentMethod;

public class ShoppingCartDTO {

	private Integer carId;

	

	@NotNull
	@Positive
	private Integer items;

	@NotNull
	@Positive
	@Max(99999999L)
	private Long total;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 1)
	private String enable;

	// -------------------------------------------------------------
	// constructores
	public ShoppingCartDTO() {
		super();
	}

	

	public ShoppingCartDTO(Integer carId, @NotNull @Positive Integer items,
			@NotNull @Positive @Max(99999999) Long total, @NotNull @NotEmpty @Size(min = 1, max = 1) String enable) {
		super();
		this.carId = carId;
		this.items = items;
		this.total = total;
		this.enable = enable;
	}



	// -------------------------------------------------------------
	// gets and sets
	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	

	public Integer getItems() {
		return items;
	}

	public void setItems(Integer items) {
		this.items = items;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

}
