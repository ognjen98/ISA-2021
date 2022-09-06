package com.isa.services.controller;

import com.isa.services.Service;
import com.isa.services.dto.DiscountReservationDTO;
import com.isa.services.dto.ServiceDTO;
import com.isa.services.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @GetMapping("/getDiscRes/{serviceId}")
    public ResponseEntity<Set<DiscountReservationDTO>> getDiscountReservations(@PathVariable Long serviceId){
        return new ResponseEntity(servicesService.getAllDiscountReservationsForService(serviceId), HttpStatus.OK);
    }

    @CrossOrigin
    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @DeleteMapping("/deleteService/{id}")
    public ResponseEntity<Service> deleteService(@PathVariable Long id){
        return new ResponseEntity(servicesService.deleteService(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping("/allServices")
    public ResponseEntity<List<ServiceDTO>> allServices(){
        return new ResponseEntity(servicesService.getAllServices(), HttpStatus.OK);
    }
}
