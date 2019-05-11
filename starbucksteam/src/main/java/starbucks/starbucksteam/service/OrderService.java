package starbucks.starbucksteam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import starbucks.starbucksteam.repository.OrderRepository;
import starbucks.starbucksteam.repository.MenuRepository;
import starbucks.starbucksteam.model.TempOrder;
import starbucks.starbucksteam.model.Menu;
import starbucks.starbucksteam.model.Order;
import starbucks.starbucksteam.model.User;

import java.util.List;

@Service("orderService")
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	public float createOrder(User user, TempOrder tempOrder)
	{
		float billAmount = 0;
		int toppingCount = 0;
		
		for(Menu m: menuRepository.findAllByCategory("COFFEE"))
		{			
			if(m.getItemName().equals(tempOrder.getCoffeeType()))
			{
				billAmount += m.getPrice();
				break;
			}
		}
		
		if(billAmount == 0)
			return -1;
		
		List<Menu> toppingMenus = menuRepository.findAllByCategory("TOPPING");
		for(String tp: tempOrder.getToppingTypes())
		{
			for(Menu m: toppingMenus)
			{
				if(tp.equals(m.getItemName()))
				{					
					billAmount += m.getPrice();
					toppingCount++;
					break;
				}
			}
		}
		
		if(!(toppingCount == tempOrder.getToppingTypes().length))
			return -2;
		
		Order order = new Order();
		order.setUser(user);
		order.setBillAmount(billAmount);
		orderRepository.save(order);
		
		return billAmount;
	}
}
