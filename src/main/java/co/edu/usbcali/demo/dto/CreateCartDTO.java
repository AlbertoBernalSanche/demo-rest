package co.edu.usbcali.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CreateCartDTO {

	@NotNull
	@NotEmpty
	@Email
	private String email;
	
	public CreateCartDTO() {
	
	}

	
	public CreateCartDTO(@NotNull @NotEmpty @Email String email) {
		super();
		this.email = email;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
