package co.edu.usbcali.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddProductDTO {

	@NotNull
	private Integer carId;
	@NotNull
	@NotEmpty
	private String proId;
	@NotNull
	private Integer quantity;

	public AddProductDTO() {
		super();
	}
	
	public AddProductDTO(Integer carId, String proId, Integer quantity) {
		super();
		this.carId = carId;
		this.proId = proId;
		this.quantity = quantity;
	}
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
