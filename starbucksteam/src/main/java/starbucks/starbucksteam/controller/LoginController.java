package starbucks.starbucksteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import starbucks.starbucksteam.model.User;
import starbucks.starbucksteam.service.UserService;

@RestController
@RequestMapping("/v1/starbucks")
public class LoginController {

    @Autowired
    private UserService userService;

   @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) throws JsonProcessingException{
	   String result = new String();
       User userExists = userService.findUserByEmail(user.getEmail());
       if (userExists != null) {
    	   ObjectMapper mapper = new ObjectMapper();
    	// Java object to JSON string
    		String jsonString = mapper.writeValueAsString(userExists);
    		result = jsonString;
       }
       else {
           result = "Please register the user first";
       }
       return result;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@RequestBody User user) {
    	String result = new String();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
           result =  "There is already a user registered with the email provided";
        }
        else {
            userService.saveUser(user);
            result = "User has been registered successfully";
        }
        return result;
    }

}
