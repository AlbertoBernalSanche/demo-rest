package  co.edu.usbcali.demo.service;

import static java.util.Collections.emptyList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.security.UserApplication;
/**
* @author Zathura Code Generator Version 9.0 http://zathuracode.org/
* www.zathuracode.org
* 
*/

@Service
public class UserApplicationDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	CustomerService customerService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Customer customer=null;
		
		if(username==null) {
			throw new UsernameNotFoundException(username);
		}
		
		if (username.isBlank()==true) {
			throw new UsernameNotFoundException(username);
		}
		//------------------------------------------------------
		Optional<Customer> customerOptional;
		try {
			customerOptional = customerService.findById(username);
			if (customerOptional.isPresent()==false) {
				throw new UsernameNotFoundException(username);
			}
			customer=customerOptional.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
		UserApplication userApplication=new UserApplication(customer.getEmail(),bCryptPasswordEncoder.encode(customer.getToken()));
			
		return new User(userApplication.getUsername(), userApplication.getPassword(), emptyList());
	}

}
