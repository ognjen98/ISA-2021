package com.isa.services.controller;

import com.isa.security.TokenUtils;
import com.isa.services.AdditionalInfo;
import com.isa.services.Reservation;
import com.isa.services.dto.*;
import com.isa.services.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    TokenUtils tokenUtils;


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

    @GetMapping("/getInfos/{serviceId}")
    public ResponseEntity<Set<AdditionalInfo>> getInfosForService(@PathVariable Long serviceId){
        return new ResponseEntity(serviceService.getAdditionalInfoForService(serviceId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping("/reserve")
    public ResponseEntity<Reservation> reserve(@RequestBody ReservationDTO dto, HttpServletRequest request){
        if(dto.getStart().equals("") || dto.getStart().equals(null) || dto.getEnd().equals("") || dto.getEnd().equals(null)){
            return new ResponseEntity("Start or end date can't be null", HttpStatus.BAD_REQUEST);
        }
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        Reservation r = serviceService.reserve(dto,email);
        if(r == null){
            return new ResponseEntity("Reservation is null, bad period entered", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(r, HttpStatus.OK);
    }
}
