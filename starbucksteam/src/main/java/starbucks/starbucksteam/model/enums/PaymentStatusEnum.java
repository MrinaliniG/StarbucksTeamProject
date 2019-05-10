package starbucks.starbucksteam.model.enums;

public enum PaymentStatusEnum {
    COMPLETE("COMPLETE"),

    PENDING("PENDING"),

    CANCELLED("CANCELLED");

    private String value;

    PaymentStatusEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
