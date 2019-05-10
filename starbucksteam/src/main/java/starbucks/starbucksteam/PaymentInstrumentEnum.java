package starbucks.starbucksteam;

public enum PaymentInstrumentEnum {
    CREDIT("CREDIT"),

    DEBIT("DEBIT");

    private String value;

    PaymentInstrumentEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
