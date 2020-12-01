package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.dto.ShoppingCartDTO;

@Mapper
public interface ShoppingCartMapper {
	
	 @Mapping(source = "customer.email", target = "email")
	    @Mapping(source = "paymentMethod.payId", target = "payId")
	    public ShoppingCartDTO shoppingCartToShoppingCartDTO(
	        ShoppingCart shoppingCart);

	    @Mapping(source = "email", target = "customer.email")
	    @Mapping(source = "payId", target = "paymentMethod.payId")
	    public ShoppingCart shoppingCartDTOToShoppingCart(
	        ShoppingCartDTO shoppingCartDTO);

	    public List<ShoppingCartDTO> listShoppingCartToListShoppingCartDTO(
	        List<ShoppingCart> shoppingCarts);

	    public List<ShoppingCart> listShoppingCartDTOToListShoppingCart(
	        List<ShoppingCartDTO> shoppingCartDTOs);

}
