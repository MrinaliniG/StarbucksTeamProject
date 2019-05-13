package starbucks.starbucksteam.controller;

import starbucks.starbucksteam.model.Menu;
import starbucks.starbucksteam.model.User;
import starbucks.starbucksteam.model.TempOrder;
import starbucks.starbucksteam.service.OrderService;
import starbucks.starbucksteam.repository.UserRepository;
import starbucks.starbucksteam.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.ArrayList;

/**
 * @author MRINALNI
 * This is a class used for creating and maintaining starbucks orders. Customers
 * can get the menu details. They can select the coffee and topping types. 
 * The bill is generated for the customer order.
 * 
 */
@Controller
@RequestMapping("/v1/starbucks")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@RequestMapping(value="/manageOrder", method= RequestMethod.POST)
    public ResponseEntity<String> manageOrder(@RequestBody TempOrder tempOrder)
    {
		float billAmount;
		User user = userRepository.findByEmail(tempOrder.getEmail());
		if(user == null)
		{
			return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
		}
		
		try {
			 billAmount = orderService.createOrder(user, tempOrder);
		}
		catch(Exception e)
		{
			 e.printStackTrace();
	         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(billAmount > 0)
		{
			String result = "Order received. Bill Amount " + billAmount;
			return new ResponseEntity<String>(result,HttpStatus.OK);
		}
		else 
		{
			String result = "Order not accepted. Please re-check order details.";
			return new ResponseEntity<String>(result,HttpStatus.OK);
		}
    }
			
	
	@RequestMapping(method=RequestMethod.GET, value = "/getMenu")
    public ResponseEntity<List<Menu>> getHackathonsByTeamMember(@RequestParam("email") String email)
    {
		User user = userRepository.findByEmail(email);
		if(user == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		List<Menu> menulist = new ArrayList<>();
		for(Menu m: menuRepository.findAll())
		{
			menulist.add(m);
		}
		
		return new ResponseEntity<>(menulist,HttpStatus.OK);
    }
}

