package com.isa.services.controller;

import com.isa.services.Cottage;
import com.isa.services.DiscountReservation;
import com.isa.services.FishingLessons;
import com.isa.services.Ship;
import com.isa.services.dto.DiscountReservationDTO;
import com.isa.services.dto.ServiceDTO;
import com.isa.services.dto.ShipDTO;
import com.isa.services.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServicesService servicesService;

    @GetMapping("/getShips")
    public ResponseEntity<List<ServiceDTO>> getShips(){
        return new ResponseEntity(servicesService.getShips(), HttpStatus.OK);
    }

    @GetMapping("/getLessons")
    public ResponseEntity<List<ServiceDTO>> getLessons(){
        return new ResponseEntity(servicesService.getLessons(), HttpStatus.OK);
    }

    @GetMapping("/getCottages")
    public ResponseEntity<List<ServiceDTO>> getCottages(){
        return new ResponseEntity(servicesService.getCottages(), HttpStatus.OK);
    }

    @GetMapping("/getDiscRes/{serviceId}")
    public ResponseEntity<Set<DiscountReservationDTO>> getDiscountReservations(@PathVariable Long serviceId){
        return new ResponseEntity(servicesService.getAllDiscountReservationsForService(serviceId), HttpStatus.OK);
    }
}
