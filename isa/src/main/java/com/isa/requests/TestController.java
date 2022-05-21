package com.isa.requests;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @CrossOrigin
    @GetMapping(value = "/define", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> define() {

        return new ResponseEntity<>("all good", HttpStatus.CREATED);
    }
}
