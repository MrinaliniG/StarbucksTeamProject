package starbucks.starbucksteam;

//import com.cmpe202.starbucks.exception.BusinessException;
//import com.cmpe202.starbucks.model.Payment;

public class Validation {

    public static void validatePayment(Payment payment) {
        if (payment.getCardNumber().length() < 15 || payment.getCardNumber().length() > 16) {
            throw new BusinessException("Invalid credit card number.");
        }
        for (char c : payment.getCardNumber().toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new BusinessException("Invalid credit card number.");
            }
        }
        if (String.valueOf(payment.getSecurityCode()).length() != 3) {
            throw new BusinessException("Invalid security code.");
        }
    }


}
