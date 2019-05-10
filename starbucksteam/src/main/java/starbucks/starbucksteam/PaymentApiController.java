package starbucks.starbucksteam;


//import com.cmpe202.starbucks.exception.BusinessException;
//import com.cmpe202.starbucks.exception.ResourceNotFoundException;
//import com.cmpe202.starbucks.model.AccountOrder;
//import com.cmpe202.starbucks.model.Payment;
//import com.cmpe202.starbucks.model.enums.OrderPaymentStatusEnum;
//import com.cmpe202.starbucks.model.enums.PaymentStatusEnum;
//import com.cmpe202.starbucks.repository.AccountOrderRepository;
//import com.cmpe202.starbucks.repository.PaymentRepository;
//import com.cmpe202.starbucks.util.FilterUtil;
//import com.cmpe202.starbucks.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/v1/starbucks")
public class PaymentApiController {

    @Autowired
    private AccountOrderRepository accountOrderRepository;

    @Autowired
    private PaymentRepository paymentRepository;


    @PostMapping("/order/{orderId}/payment")
    @Transactional
    public ResponseEntity<Object> createPayment(@PathVariable("orderId") Long orderId, @Valid @RequestBody Payment payment) {
        Optional<AccountOrder> existingOrder = accountOrderRepository.findById(orderId);
        if (!existingOrder.isPresent()) {
            throw new ResourceNotFoundException("Order does not exist :: " + orderId);
        }

        if (existingOrder.get().getOrderPaymentStatusEnum().equals(OrderPaymentStatusEnum.COMPLETE)) {
            throw new BusinessException("Payment is already done for this order :: " + orderId);
        }

        Validation.validatePayment(payment);

        if (payment.getPaymentStatus().equals(PaymentStatusEnum.COMPLETE)) {
            AccountOrder order = existingOrder.get();
            order.setOrderPaymentStatusEnum(OrderPaymentStatusEnum.COMPLETE);
            order = accountOrderRepository.save(order);
            payment.setOrder(order);
        }

        payment = paymentRepository.save(payment);

        return ResponseEntity.ok().body(FilterUtil.applyPaymentFilters(payment));
    }


    @GetMapping("/order/{orderId}/payment")
    @Transactional
    public ResponseEntity<Object> getAllPaymentsForOrder(@PathVariable("orderId") Long orderId) {
        Optional<AccountOrder> order = accountOrderRepository.findById(orderId);
        if (!order.isPresent()) {
            throw new ResourceNotFoundException("Order does not exist :: " + orderId);
        }
        return ResponseEntity.ok().body(FilterUtil.applyPaymentFilters(paymentRepository.findAllForOrder(orderId)));
    }

}