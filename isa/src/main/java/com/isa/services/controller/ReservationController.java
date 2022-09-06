package com.isa.services.controller;

import com.isa.security.TokenUtils;
import com.isa.services.AdditionalInfo;
import com.isa.services.EarningPercentage;
import com.isa.services.Earnings;
import com.isa.services.Reservation;
import com.isa.services.dto.*;
import com.isa.services.service.ReservationService;
import com.isa.users.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    ReservationRepository reservationRepository;


    @PostMapping("/searchShips")
    public ResponseEntity<List<InhrShipDTO>> searchShips(@RequestBody SearchDataDTO dto){
        return new ResponseEntity(reservationService.searchShips(dto), HttpStatus.OK);
    }

    @PostMapping("/searchCottages")
    public ResponseEntity<List<InhrCottageDTO>> searchCottages(@RequestBody SearchDataDTO dto){
        return new ResponseEntity(reservationService.searchCottages(dto), HttpStatus.OK);
    }

    @PostMapping("/searchLessons")
    public ResponseEntity<List<ServiceDTO>> searchLessons(@RequestBody SearchDataDTO dto){
        return new ResponseEntity(reservationService.searchLessons(dto), HttpStatus.OK);
    }

    @PostMapping("/sort")
    public ResponseEntity<List<ServiceDTO>> sortServices(@RequestBody SortDTO dto){
        return new ResponseEntity(reservationService.sort(dto), HttpStatus.OK);
    }

    @PostMapping("/sortShips")
    public ResponseEntity<List<InhrShipDTO>> sortShips(@RequestBody SortDTOShip dto){
        return new ResponseEntity(reservationService.sortShips(dto), HttpStatus.OK);
    }

    @PostMapping("/sortCottages")
    public ResponseEntity<List<InhrCottageDTO>> sortCottages(@RequestBody SortDTOCottage dto){
        return new ResponseEntity(reservationService.sortCottages(dto), HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<InhrShipDTO>> filterServices(@RequestBody FilterDTO dto){
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

    @GetMapping("/cancel/{resId}")
    public ResponseEntity<Reservation> cancel(@PathVariable Long resId, HttpServletRequest request){
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity(reservationService.cancel(resId, email), HttpStatus.OK);
    }

    @GetMapping("/getResForClient")
    public ResponseEntity<List<GetReservationDTO>>  getReservationsForClient(HttpServletRequest request){
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity(reservationService.getReservationsForClient(email), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping("/definePercentage")
    public ResponseEntity<EarningPercentage> defineEarningPercentage(@RequestParam Float percentage){
        return new ResponseEntity(reservationService.defineEarningPercentage(percentage), HttpStatus.OK);
    }

    @PostMapping("/getReport")
    public ResponseEntity<List<DayMonthValueDTO>> getReport(@RequestBody ReportDTO dto){
        return new ResponseEntity(reservationService.getReport(dto),HttpStatus.OK);
    }

    @GetMapping("/getShipReservations")
    public ResponseEntity getShipReservations(HttpServletRequest request){
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity(reservationService.getPastShipReservations(email), HttpStatus.OK);
    }

    @GetMapping("/getLessonsReservations")
    public ResponseEntity getLessonsReservations(HttpServletRequest request){
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity(reservationService.getPastLessonsReservations(email), HttpStatus.OK);
    }

    @GetMapping("/getCottageReservations")
    public ResponseEntity getCottageReservations(HttpServletRequest request){
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity(reservationService.getPastCottageReservations(email), HttpStatus.OK);
    }

    @GetMapping("/getMisc")
    public ResponseEntity<CPPDTO> getMisc(HttpServletRequest request){
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity<>(reservationService.getMisc(email), HttpStatus.OK);
    }


}
