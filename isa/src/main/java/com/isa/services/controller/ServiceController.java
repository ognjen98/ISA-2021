package com.isa.services.controller;

import com.isa.services.dto.FilterDTO;
import com.isa.services.dto.SearchDataDTO;
import com.isa.services.dto.ServiceDTO;
import com.isa.services.dto.SortDTO;
import com.isa.services.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/sort")
    public ResponseEntity<List<ServiceDTO>> sortServices(@RequestBody SortDTO dto){
        return new ResponseEntity(serviceService.sort(dto), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ServiceDTO>> filterServices(@RequestBody FilterDTO dto){
        return new ResponseEntity(serviceService.filter(dto), HttpStatus.OK);
    }
}
