package com.isa.users.controller;

import com.isa.users.Client;
import com.isa.users.ConfirmationToken;
import com.isa.users.User;
import com.isa.users.dto.RegistrationDTO;
import com.isa.users.repository.ClientRepository;
import com.isa.users.repository.ConfirmationTokenRepository;
import com.isa.users.repository.UserRepository;
import com.isa.users.service.ClientService;
import com.isa.users.service.UserService;
import com.isa.users.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailService;

//    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
//            MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<User> register(@RequestBody RegistrationDTO regDTO){
//        return new ResponseEntity(clientService.register(regDTO), HttpStatus.CREATED);
//    }


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegistrationDTO request) {
        return new ResponseEntity(clientService.register(request), HttpStatus.CREATED);
        //return "registered";
    }


    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return clientService.confirmToken(token);
    }

//    @GetMapping(path = "confirm")
//    public String confirm(@RequestParam("token") String token) {
//        return registrationService.confirmToken(token);
//    }


}
