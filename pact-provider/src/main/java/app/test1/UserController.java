package app.test1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Elbrus Garayev on 2/1/2021
 */
@RestController
public class UserController {

    @GetMapping("/user")
    public User getUser(HttpServletResponse response){
        response.addHeader("Accept-Language", "en");
        return new User("Bill Doe", 1000, new Contact("billdoe@gmail.com", "56565656"));
    }
}
