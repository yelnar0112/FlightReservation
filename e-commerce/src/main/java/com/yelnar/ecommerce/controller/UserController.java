package com.yelnar.ecommerce.controller;

import com.yelnar.ecommerce.exceptions.UserAlreadyRegistered;
import com.yelnar.ecommerce.exceptions.UserNotFound;
import com.yelnar.ecommerce.model.User;
import com.yelnar.ecommerce.repository.UserRepository;
import com.yelnar.ecommerce.service.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Slf4j
public class UserController {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SecurityService service;

    @Autowired
    public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, SecurityService service) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.service = service;
    }

    @GetMapping("/showReg")
    public String showRegistrationPage(){
        log.info("Inside showRegistrationPage()");
        return "flights/registerUser";
    }

    @PostMapping("/registerUser")
    public String register(@ModelAttribute("user")User user){
        log.info("{} Inside register()",user.getEmail());
        Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
        if(foundUser.isPresent()){
            log.error("User is already registered with email {}",user.getEmail());
            throw new UserAlreadyRegistered("Email exists: "+user.getEmail());
        }

        log.info("Email Exists: "+user.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "flights/login";
    }

    @GetMapping("/showLogin")
    public String showLoginPage(){
        log.info("Inside showLoginPage()");
        return "flights/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap map){
        log.info("{} Inside login()",email);
        Optional<User> foundUser=userRepository.findByEmail(email);
        if (foundUser.isEmpty()) {
            log.error("Email not found: "+email);
            throw new UserNotFound("UserNotFound: "+email);
        }
        log.info("Email exists: "+email);
        boolean loginResponse = service.login(email,password);

        if(loginResponse){
            map.addAttribute("msg","Successfully logged in");
            return "flights/findFlights";
        }else {
            log.info("User entered Invalid credentials email:{} and password:{}",email,password);
            map.addAttribute("msg","Invalid username or password");
        }
        return "flights/login";
    }


}
