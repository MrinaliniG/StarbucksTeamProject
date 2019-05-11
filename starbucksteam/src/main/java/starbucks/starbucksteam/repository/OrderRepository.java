package starbucks.starbucksteam.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import starbucks.starbucksteam.model.Order;

public interface OrderRepository extends CrudRepository<Order,Long>{
	
}
