package starbucks.starbucksteam.controller;

import starbucks.starbucksteam.model.Order;
import starbucks.starbucksteam.model.Menu;
import starbucks.starbucksteam.model.User;
import starbucks.starbucksteam.model.TempOrder;
import starbucks.starbucksteam.service.OrderService;
import starbucks.starbucksteam.repository.OrderRepository;
import starbucks.starbucksteam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/manageOrder", method= RequestMethod.POST)
    public ResponseEntity<String> manageOrder(@RequestBody TempOrder tempOrder)
    {
		User user = userRepository.findByEmail(tempOrder.getEmail());
		if(user == null)
		{
			return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
		}
		
		try {
			orderService.createOrder(user, tempOrder);
		}
		catch(Exception e)
		{
			 e.printStackTrace();
	         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>("Success.",HttpStatus.OK);
    }
			
}
