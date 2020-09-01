package co.edu.usbcali.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/operaciones/")
public class OperacionesMatematicas {

	@GetMapping("sumar/{n1}/{n2}")
	public Resultado sumar(@PathVariable("n1") Integer num1, @PathVariable("n2") Integer num2) {
		return new Resultado (num1 + num2);
	}

	@GetMapping("restar/{n1}/{n2}")
	public Resultado restar(@PathVariable("n1") Integer num1, @PathVariable("n2") Integer num2) {
		return new Resultado(num1 - num2);
	}
}
