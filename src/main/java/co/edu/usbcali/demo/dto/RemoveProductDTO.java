package co.edu.usbcali.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RemoveProductDTO {

	@NotNull
	private Integer carId;
	
	@NotNull
	@NotEmpty
	private String proId;
	
	public RemoveProductDTO() {
		super();
	}
	
	public RemoveProductDTO(@NotNull Integer carId, @NotNull @NotEmpty String proId) {
		super();
		this.carId = carId;
		this.proId = proId;
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
}
