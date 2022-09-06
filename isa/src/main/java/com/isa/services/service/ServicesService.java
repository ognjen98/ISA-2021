package com.isa.services.service;

import com.isa.services.*;
import com.isa.services.dto.*;
import com.isa.services.repository.CottageRepository;
import com.isa.services.repository.FishingLessonsRepository;
import com.isa.services.repository.ServiceRepository;
import com.isa.services.repository.ShipRepository;
import com.isa.users.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServicesService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private CottageRepository cottageRepository;

    @Autowired
    private FishingLessonsRepository fishingLessonsRepository;

    @Transactional
    public List<ServiceDTO> getShips(){
        List<com.isa.services.Service> ships =
                serviceRepository.findAll().stream().filter(s-> s instanceof Ship && !s.getDeleted()).collect(Collectors.toList());
        return serviceMapper(ships);
    }

    private List<ShipDTO> shipMapper(List<Ship> ships){
        List<ShipDTO> dtos = new ArrayList<>();
        for(Ship s: ships){
            ShipDTO dto = new ShipDTO(s.getId(), s.getName(), s.getPrice(), s.getGrade(), s.getNoGuests(),
                    s.getType(), s.getLength(), s.getNoEngines(), s.getMaxSpeed(), s.getEnginePower());
            dtos.add(dto);
        }

        return dtos;
    }

    @Transactional
    public List<ServiceDTO> getLessons(){
        List<com.isa.services.Service> fishingLessons =
                serviceRepository.findAll().stream().filter(s-> s instanceof FishingLessons && !s.getDeleted()).collect(Collectors.toList());
        return serviceMapper(fishingLessons);
    }

    private List<LessonDTO> lessonMapper(List<FishingLessons> lessons){
        List<LessonDTO> dtos = new ArrayList<>();
        for(FishingLessons lesson: lessons){
            LessonDTO dto = new LessonDTO(lesson.getId(), lesson.getName(), lesson.getPrice(), lesson.getGrade(),
                    lesson.getNoGuests());
            dtos.add(dto);
        }

        return dtos;
    }

    @Transactional
    public List<ServiceDTO> getCottages(){
        List<com.isa.services.Service> cottages =
                serviceRepository.findAll().stream().filter(s-> s instanceof Cottage && !s.getDeleted()).collect(Collectors.toList());
        return serviceMapper(cottages);
    }

    private List<CottageDTO> cottageMapper(List<Cottage> cottages){
        List<CottageDTO> dtos = new ArrayList<>();
        for(Cottage cottage: cottages){
            CottageDTO dto = new CottageDTO(cottage.getId(), cottage.getName(), cottage.getPrice(), cottage.getGrade(),
                    cottage.getNoGuests(), cottage.getNoRooms(), cottage.getNoBedsByRoom());
            dtos.add(dto);
        }

        return dtos;
    }

    private List<ServiceDTO> serviceMapper(List<com.isa.services.Service> services){
        List<ServiceDTO> dtos = new ArrayList<>();
        for(com.isa.services.Service service: services){
            dtos.add(new ServiceDTO(service.getId(), service.getName(),service.getGrade(),service.getPrice(),service.getAddress().getStreetName(),
                    service.getAddress().getNumber(),service.getAddress().getCity(),service.getAddress().getState(),
                    service.getImage()));

        }

        return dtos;
    }

    @Transactional
    public Set<DiscountReservationDTO> getAllDiscountReservationsForService(Long id){
        com.isa.services.Service service = serviceRepository.getServiceById(id);
        if(service.getDeleted()){
            return null;
        }
        return discountReservationDTOMapper(service.getReservations());
    }

    private Set<DiscountReservationDTO> discountReservationDTOMapper(Set<Reservation> discountReservations){
        Set<DiscountReservationDTO> dtos = new HashSet<>();
        for(Reservation discountReservation: discountReservations){
            if(discountReservation.getDiscPrice() != null && !discountReservation.getReserved()) {
                DiscountReservationDTO dto = new DiscountReservationDTO(discountReservation.getId(),
                        discountReservation.getStartTime(), discountReservation.getEndTime(),
                        discountReservation.getMaxCapacity(), discountReservation.getPrice(),
                        discountReservation.getAddress().getCity(), discountReservation.getDiscPrice(),
                        discountReservation.getAdditionalInfos(), discountReservation.getService().getImage());
                dtos.add(dto);
            }
        }

        return dtos;
    }

    @Transactional
    public com.isa.services.Service deleteService(Long id){
        com.isa.services.Service service = serviceRepository.getServiceById(id);
        List<Reservation> reservations = reservationRepository.getReservationsByServiceId(service.getId());
        if (reservations == null || reservations.size() == 0) {
            service.setDeleted(true);

        } else {
            return null;
        }

        return service;
    }

    @Transactional
    public List<ServiceDTO> getAllServices(){
        List<com.isa.services.Service> services = serviceRepository.findAll().stream().filter(s -> !s.getDeleted()).collect(Collectors.toList());
        List<ServiceDTO> dtos = new ArrayList<>();
        for(com.isa.services.Service service : services){

            dtos.add(new ServiceDTO(service.getId(), service.getName(), service.getGrade(), service.getPrice(),
                    service.getAddress().getStreetName(), service.getAddress().getNumber(),
                    service.getAddress().getCity(), service.getAddress().getState(), service.getImage()));
        }
        return dtos;
    }

    @Transactional
    public com.isa.services.Service findOneById(Long id) {


        com.isa.services.Service service = serviceRepository.getServiceById(id);

        return service;
    }
}
