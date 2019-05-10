package starbucks.starbucksteam.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import starbucks.starbucksteam.model.Card;
import starbucks.starbucksteam.repository.CardRepository;

@RestController
public class HelloController {
	
	@Autowired 
	private CardRepository cardRepo;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @PostMapping(path="/addcard",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
	public @ResponseBody String addNewCard (@RequestBody Card card) {
   
    	card.setCardid(card.getCardid());
    	card.setCardcode(card.getCardcode());
    	card.setUserid(card.getUserid());
    	Calendar calendar = Calendar.getInstance();
        java.sql.Date dateObj = new java.sql.Date(calendar.getTime().getTime());
    	card.setCardcreateddate(dateObj);
    	
		cardRepo.save(card);
		return "Saved";
	}
    
    

}
