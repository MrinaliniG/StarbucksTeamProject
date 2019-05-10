package starbucks.starbucksteam;

//import com.cmpe202.starbucks.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {

}
