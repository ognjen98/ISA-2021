package com.isa.users.controller;

import com.isa.security.TokenUtils;
import com.isa.users.dto.AdminDTO;
import com.isa.users.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/systemAdmin")
public class SystemAdminController {

    @Autowired
    SystemAdminService service;

    @Autowired
    TokenUtils tokenUtils;

    @GetMapping("/checkFirstTimeLogin")
    public ResponseEntity checkFirstTimeLogin(HttpServletRequest request){
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity(service.checkFirstTimeLogin(email), HttpStatus.OK);
    }

    @PostMapping("/createAdmin")
    public ResponseEntity createAdmin(@RequestBody AdminDTO dto){
        return new ResponseEntity(service.createAdmin(dto), HttpStatus.OK);
    }

    @GetMapping("/changePass")
    public ResponseEntity changePass(@RequestParam String pass, HttpServletRequest request){
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity(service.changePass(pass, email), HttpStatus.OK);
    }
}
