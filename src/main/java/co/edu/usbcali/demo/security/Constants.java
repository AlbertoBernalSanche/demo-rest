package  co.edu.usbcali.demo.security;

/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org/
* www.zathuracode.org
* 
*/
public class Constants {

	// Spring Security

	public static final String LOGIN_URL = "/login";
	public static final String REGISTER_URL="/api/customer/save";
	public static final String UPDATE_URL="/api/customer/update";
	
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";

	// JWT

	public static final String ISSUER_INFO = "https://zathuracode.org";
	public static final String SUPER_SECRET_KEY = "z4tur4c0dv92020isth3b3stcodeg3n3ratorintheworldofj4v4v0rtex2020";
	public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day

}
