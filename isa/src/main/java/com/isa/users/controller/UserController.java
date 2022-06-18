package com.isa.users.controller;

import com.isa.security.TokenUtils;
import com.isa.users.User;
import com.isa.users.dto.UpdateInfoDTO;
import com.isa.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    TokenUtils tokenUtils;

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN') or hasRole('ROLE_CLIENT')")
//    @Secured({"SYSTEM_ADMIN"})
    @PostMapping("/updateInfo")
    public ResponseEntity<User> updateInfo(@RequestBody UpdateInfoDTO dto, HttpServletRequest request){
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        //System.out.println(request.isUserInRole("SYSTEM_ADMIN"));
        return new ResponseEntity(userService.updateInfo(dto, email), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN') or hasRole('ROLE_CLIENT')")
    @GetMapping("/userInfo")
    public ResponseEntity<UpdateInfoDTO> getInfo(HttpServletRequest request){
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity(userService.getInfo(email), HttpStatus.OK);
    }
}
