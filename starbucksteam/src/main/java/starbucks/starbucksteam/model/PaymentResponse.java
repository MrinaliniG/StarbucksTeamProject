package starbucks.starbucksteam.model;

import starbucks.starbucksteam.model.enums.PaymentStatusEnum;

public class PaymentResponse {

    private Integer cardId;

    private Double balance;

    private PaymentStatusEnum paymentStatus;

    public PaymentResponse() {
        super();
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public PaymentStatusEnum getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatusEnum paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
