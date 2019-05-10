package starbucks.starbucksteam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import starbucks.starbucksteam.security.UserService;
import starbucks.starbucksteam.model.User;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

   /* @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }*/


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@RequestBody User user) {
        //ModelAndView modelAndView = new ModelAndView();
    	String result = new String();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
           result =  "There is already a user registered with the email provided";
        }
        else {
            userService.saveUser(user);
            result = "User has been registered successfully";

        }
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return result  + auth;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public String home(){
        //ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        //modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
       // modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
       // modelAndView.setViewName("admin/home");
        return user.getName() + user.getLastName() + user.getEmail();
    }


}
