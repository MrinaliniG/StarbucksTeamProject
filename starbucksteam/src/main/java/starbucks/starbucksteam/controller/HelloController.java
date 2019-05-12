package starbucks.starbucksteam.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*this is added for our testing endpoint initial springboot setup*/
@RestController
@RequestMapping("/v1/starbucks")
public class HelloController {

    @RequestMapping("/hello")
    public ResponseEntity<String> index() {
    	HttpHeaders headers = new HttpHeaders();
    	headers.set("Content-Type","*/*;charset=UTF-8");
    	return new ResponseEntity<String>("Greetings from Spring Boot!", headers, HttpStatus.OK);
    } 

}
