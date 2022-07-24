package com.isa.services.service;

import com.isa.services.Cottage;
import com.isa.services.FishingLessons;
import com.isa.services.Ship;
import com.isa.services.dto.CottageDTO;
import com.isa.services.dto.LessonDTO;
import com.isa.services.dto.ServiceDTO;
import com.isa.services.dto.ShipDTO;
import com.isa.services.repository.CottageRepository;
import com.isa.services.repository.FishingLessonsRepository;
import com.isa.services.repository.ServiceRepository;
import com.isa.services.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServicesService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private CottageRepository cottageRepository;

    @Autowired
    private FishingLessonsRepository fishingLessonsRepository;

    public List<ServiceDTO> getShips(){
        List<com.isa.services.Service> ships = serviceRepository.findAll().stream().filter(s-> s instanceof Ship).collect(Collectors.toList());
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

    public List<ServiceDTO> getLessons(){
        List<com.isa.services.Service> fishingLessons =
                serviceRepository.findAll().stream().filter(s-> s instanceof FishingLessons).collect(Collectors.toList());
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

    public List<ServiceDTO> getCottages(){
        List<com.isa.services.Service> cottages =
                serviceRepository.findAll().stream().filter(s-> s instanceof Cottage).collect(Collectors.toList());
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
                    service.getAddress().getNumber(),service.getAddress().getCity(),service.getAddress().getState()));

        }

        return dtos;
    }


}
