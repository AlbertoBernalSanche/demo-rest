package co.edu.usbcali.demo.dto;

import javax.validation.constraints.NotNull;

public class CleanCartDTO {

	@NotNull
	private Integer carId;
	
	
	public CleanCartDTO() {
		super();
	}
	

	public CleanCartDTO(@NotNull Integer carId) {
		super();
		this.carId = carId;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}
}
