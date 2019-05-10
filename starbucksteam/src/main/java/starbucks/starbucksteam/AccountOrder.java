package starbucks.starbucksteam;

//import com.cmpe202.starbucks.model.enums.OrderPaymentStatusEnum;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Validated
@Entity
public class AccountOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Account account;

    @OneToMany(targetEntity = Payment.class)
    private List<Payment> payments = new ArrayList<>();

    @Column(nullable = false)
    private OrderPaymentStatusEnum orderPaymentStatusEnum = OrderPaymentStatusEnum.PENDING;


    public AccountOrder() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderPaymentStatusEnum getOrderPaymentStatusEnum() {
        return orderPaymentStatusEnum;
    }

    public void setOrderPaymentStatusEnum(OrderPaymentStatusEnum orderPaymentStatusEnum) {
        this.orderPaymentStatusEnum = orderPaymentStatusEnum;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}

