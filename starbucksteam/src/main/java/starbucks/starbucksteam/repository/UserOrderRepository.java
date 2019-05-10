package starbucks.starbucksteam.repository;

//import com.cmpe202.starbucks.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import starbucks.starbucksteam.model.UserOrder;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

}
