package com.isa.services.controller;

import com.isa.services.dto.SearchDataDTO;
import com.isa.services.dto.ServiceDTO;
import com.isa.services.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping("/search")
    public ResponseEntity<List<ServiceDTO>> searchServices(@RequestBody SearchDataDTO dto){
        return new ResponseEntity(serviceService.search(dto), HttpStatus.OK);
    }
}
