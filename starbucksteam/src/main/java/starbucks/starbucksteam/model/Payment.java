package starbucks.starbucksteam.model;

import org.springframework.validation.annotation.Validated;
import starbucks.starbucksteam.model.enums.PaymentStatusEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Validated
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Float paymentAmount;

    @ManyToOne
    private Card card;

    @Column(nullable = false)
    private PaymentStatusEnum paymentStatus = PaymentStatusEnum.PENDING;

    public Payment() {
        super();
    }

    public Float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public PaymentStatusEnum getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatusEnum paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}

