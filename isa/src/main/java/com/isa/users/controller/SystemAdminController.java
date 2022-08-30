package com.isa.users.controller;

import com.isa.loyalties.Category;
import com.isa.loyalties.Points;
import com.isa.loyalties.dto.CategoryDTO;
import com.isa.security.TokenUtils;
import com.isa.users.dto.AdminDTO;
import com.isa.users.dto.UserDTO;
import com.isa.users.service.SystemAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @PostMapping("/createCategory")
    public ResponseEntity createCategory(@RequestBody Category dto){
        return new ResponseEntity(service.createCategory(dto),HttpStatus.OK);
    }

    @PostMapping("/setPoints")
    public ResponseEntity setPoints(@RequestBody Points dto){
        return new ResponseEntity(service.setPoints(dto), HttpStatus.OK);
    }

    @GetMapping("/regRequests")
    public ResponseEntity<List<UserDTO>> regRequests(){
        return new ResponseEntity(service.getRegRequests(), HttpStatus.OK);
    }

    @GetMapping("/acceptReg/{id}")
    public ResponseEntity acceptReg(@PathVariable Long id){
        return new ResponseEntity(service.acceptReg(id),HttpStatus.OK);
    }

    @GetMapping("/rejectReg/{id}")
    public ResponseEntity rejectReg(@PathVariable Long id, @RequestParam String message){
        return new ResponseEntity(service.rejectReg(id, message),HttpStatus.OK);
    }


}
