package com.isa.users.controller;

import com.isa.security.TokenAuthenticationRequest;
import com.isa.security.TokenUtils;
import com.isa.security.UserTokenState;
import com.isa.users.User;
import com.isa.users.repository.UserRepository;
import com.isa.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody TokenAuthenticationRequest authenticationRequest, HttpServletResponse response) {
        User u = userRepository.findByEmail(authenticationRequest.getUsername());
        if(u.getDeleted()){
            return new ResponseEntity<>("User is deleted", HttpStatus.BAD_REQUEST);
        }
        if(!u.getEnabled()){
            return new ResponseEntity<>("Email not verified", HttpStatus.BAD_REQUEST);
        }
        if(u.getApproved() == 2 || u.getApproved() == 0){
            return new ResponseEntity<>("User not approved by admin", HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenUtils.generateToken(authentication);
        int expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }
}
