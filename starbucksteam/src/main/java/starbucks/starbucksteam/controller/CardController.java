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
import starbucks.starbucksteam.model.CardPayment;
import starbucks.starbucksteam.model.User;
import starbucks.starbucksteam.repository.CardRepository;
import starbucks.starbucksteam.repository.UserRepository;
import starbucks.starbucksteam.service.CardService;
import starbucks.starbucksteam.service.UserService;

/**
 * @author SAHANA
 * This is a class used for creating maintaining starbucks reward cards and assign it to
 * the user and also get it for display
 */
@RestController
@RequestMapping("/v1/starbucks")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping(path = "/addcard", consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
    public String addNewCard(@RequestBody CardPayment cardpayment) {

        String cardResult = cardService.saveCard(cardpayment);
        return cardResult;
    }

    @GetMapping(path = "/getCards/{email}")
    public String getCardsForUser(@PathVariable("email") String email) throws JsonProcessingException {

        List<Card> cards = cardService.getCardsForEmail(email);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(cards);
        return json;
    }
}
