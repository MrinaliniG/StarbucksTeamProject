package starbucks.starbucksteam.model.enums;

public enum OrderPaymentStatusEnum {

    COMPLETE("COMPLETE"),

    PENDING("PENDING");


    private String value;

    OrderPaymentStatusEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
