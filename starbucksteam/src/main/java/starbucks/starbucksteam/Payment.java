package starbucks.starbucksteam;

//import com.cmpe202.starbucks.model.enums.PaymentInstrumentEnum;
//import com.cmpe202.starbucks.model.enums.PaymentStatusEnum;
import com.fasterxml.jackson.annotation.JsonFilter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Validated
@Entity
@JsonFilter("paymentFilter")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDate paymentDate = LocalDate.now();

    @NotNull
    @Column(nullable = false)
    private Float paymentAmount;

    @NotNull
    @Column(nullable = false)
    private PaymentInstrumentEnum paymentInstrument;

    @NotEmpty
    @Column(nullable = false)
    private String cardNumber;

    @NotNull
    @Column(nullable = false)
    private Integer securityCode;

    @NotEmpty
    @Column(nullable = false)
    private String nameOnCard;

    @NotEmpty
    @Column(nullable = false)
    private String billingAddress;

    @OneToOne
    private UserOrder order;

    @Column(nullable = false)
    private PaymentStatusEnum paymentStatus = PaymentStatusEnum.PENDING;

    public Payment() {
        super();
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public PaymentInstrumentEnum getPaymentInstrument() {
        return paymentInstrument;
    }

    public void setPaymentInstrument(PaymentInstrumentEnum paymentInstrument) {
        this.paymentInstrument = paymentInstrument;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(Integer securityCode) {
        this.securityCode = securityCode;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public PaymentStatusEnum getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatusEnum paymentStatus) {
        this.paymentStatus = paymentStatus;
    }


    public UserOrder getOrder() {
        return order;
    }

    public void setOrder(UserOrder order) {
        this.order = order;
    }
}

