package starbucks.starbucksteam.repository;

//import com.cmpe202.starbucks.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import starbucks.starbucksteam.model.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("select p from Payment p where p.order.id = ?1")
    List<Payment> findAllForOrder(Long orderId);

}
