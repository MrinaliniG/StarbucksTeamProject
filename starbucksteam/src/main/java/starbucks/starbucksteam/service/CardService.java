package starbucks.starbucksteam.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import starbucks.starbucksteam.repository.CardRepository;
import starbucks.starbucksteam.repository.UserRepository;
import starbucks.starbucksteam.exception.BusinessException;
import starbucks.starbucksteam.model.Card;
import starbucks.starbucksteam.model.CardPayment;
import starbucks.starbucksteam.model.User;

@Service("cardService")
public class CardService {

	@Autowired 
	private CardRepository cardRepo;
	
	@Autowired
	private UserRepository userRepo;

    public String saveCard(CardPayment cardpayment) {
    	
    	
    	String validation = creditCardValidate(cardpayment);
    	
    	String cardresult = null;
    
    	
    	if(validation != null && !validation.isEmpty()) {
	    	return validation;
	    	
    	} else {
    		Card card = new Card();
    		if(cardpayment.getCardid().matches("[0-9]+") && cardpayment.getCardid().length() != 9) {
    			cardresult = "Card Id is invalid. Please enter a correct card number";
    			return cardresult;
    		} 
    		
    		if(userRepo.findByEmail(cardpayment.getEmail()) == null) {
    			return "Email is not valid";
    		}

    		
    		List<Integer> ids = new ArrayList<Integer>();
    		ids.add(Integer.valueOf(cardpayment.getCardid()));
    		
    		List<Card> cardList = cardRepo.findAllById(ids);
    		
    		if(cardList.size() == 0) {
    		
		    	card.setCardcode(cardpayment.getCardcode());
		    	card.setUser(userRepo.findByEmail(cardpayment.getEmail()));
		    	Calendar calendar = Calendar.getInstance();
		        java.sql.Date dateObj = new java.sql.Date(calendar.getTime().getTime());
		    	card.setCardcreateddate(dateObj);
		    	card.setBalance(cardpayment.getAmount());
		    	card.setCardid(Integer.valueOf(cardpayment.getCardid()));
		    	card = cardRepo.save(card);
		    	
		    	cardresult =  "Saved the card with card id :  " + card.getCardid() + " and card code: " + card.getCardcode() 
				+ "and balance : " + card.getBalance();
    		} else {
    			cardresult = "Enter a new reward card number as it already exists";
    		}
    	}
    	
    	return cardresult;
    }
    
    
    public List<Card> getCardsForEmail(String email) {
    	User user = userRepo.findByEmail(email);
		List<Card> cardResult = cardRepo.findByUser(user);
		return cardResult;
    }
    
    /**
     * Ideally this should be sent to payment gateway with tokenization for
     * credit card validation through secure channels and then returned.
     */
    private String creditCardValidate(CardPayment payment) {
    	String validate = null;
    	
    	String cardType = payment.getCardType();
    	
    	if(cardType.equals("MASTERCARD") || cardType.equals("VISA")) {
    		if(payment.getCardNumber().matches("[0-9]+") && payment.getCardNumber().length() != 16) {
    			return "Credit Card Number is invalid";
    		} else if(String.valueOf(payment.getCvv()).length() != 3) {
    			return "Credit Card CVV is invalid";
    		}
    	} else if(cardType.equals("AMEX")) {
    		if(payment.getCardNumber().matches("[0-9]+") && payment.getCardNumber().length() != 15) {
    			return "Credit Card Number is invalid";
    		} else if(String.valueOf(payment.getCvv()).length() != 4) {
    			return "Credit Card CVV is invalid";
    		}
    	} else {
    		validate = "Invalid card type";
    	}
    	
    	return validate;
    }
    

}
