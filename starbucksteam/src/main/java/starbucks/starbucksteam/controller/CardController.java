package starbucks.starbucksteam.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import starbucks.starbucksteam.model.Card;
import starbucks.starbucksteam.model.User;
import starbucks.starbucksteam.repository.CardRepository;
import starbucks.starbucksteam.repository.UserRepository;

/**
 * 
 * @author SAHANA
 * This is a class used for creating maintaining starbucks reward cards and assign it to 
 * the user and also get it for display
 *
 */
@RestController
@RequestMapping("/v1/starbucks")
public class CardController {
	@Autowired 
	private CardRepository cardRepo;
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping(path="/addcard/{email}",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
	public String addNewCard (@RequestBody Card card,@PathVariable("email") String email) {
   
    	card.setCardcode(card.getCardcode());
    	card.setUser(userRepo.findByEmail(email));
    	Calendar calendar = Calendar.getInstance();
        java.sql.Date dateObj = new java.sql.Date(calendar.getTime().getTime());
    	card.setCardcreateddate(dateObj);
    	
		Card cardResult = cardRepo.save(card);
		return "Saved the card with card id :  " + cardResult.getCardid() + " and card code: " + cardResult.getCardcode() 
		+ "and balance : " + cardResult.getBalance();
	}
	
	@GetMapping(path="/getCards/{email}")
	public String getCardsForUser(@PathVariable("email") String email) throws JsonProcessingException {
		User user = userRepo.findByEmail(email);
		List<Card> cardResult = cardRepo.findByUser(user);

	    ObjectMapper mapper = new ObjectMapper();
		 String json = mapper.writeValueAsString(cardResult);
			return json;
	}
}
