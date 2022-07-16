package com.isa.services.controller;

import com.isa.security.TokenUtils;
import com.isa.services.AdditionalInfo;
import com.isa.services.Reservation;
import com.isa.services.dto.*;
import com.isa.services.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/service")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    TokenUtils tokenUtils;


    @PostMapping("/search")
    public ResponseEntity<List<ServiceDTO>> searchServices(@RequestBody SearchDataDTO dto){
        return new ResponseEntity(reservationService.search(dto), HttpStatus.OK);
    }

    @PostMapping("/sort")
    public ResponseEntity<List<ServiceDTO>> sortServices(@RequestBody SortDTO dto){
        return new ResponseEntity(reservationService.sort(dto), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ServiceDTO>> filterServices(@RequestBody FilterDTO dto){
        return new ResponseEntity(reservationService.filter(dto), HttpStatus.OK);
    }

    @GetMapping("/getInfos/{serviceId}")
    public ResponseEntity<Set<AdditionalInfo>> getInfosForService(@PathVariable Long serviceId){
        return new ResponseEntity(reservationService.getAdditionalInfoForService(serviceId), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping("/reserve")
    public ResponseEntity<Reservation> reserve(@RequestBody ReservationDTO dto, HttpServletRequest request){
        if(dto.getStart().equals("") || dto.getStart().equals(null) || dto.getEnd().equals("") || dto.getEnd().equals(null)){
            return new ResponseEntity("Start or end date can't be null", HttpStatus.BAD_REQUEST);
        }
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        Reservation r = reservationService.reserve(dto,email);
        if(r == null){
            return new ResponseEntity("Reservation is null, bad period entered", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(r, HttpStatus.OK);
    }

    @PostMapping("/cancel/{resId}")
    public ResponseEntity<Reservation> cancel(@PathVariable Long resId){
        return new ResponseEntity(reservationService.cancel(resId), HttpStatus.OK);
    }
}
