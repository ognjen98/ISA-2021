package com.isa.services.service;

import com.isa.services.*;
import com.isa.services.dto.FilterDTO;
import com.isa.services.dto.SearchDataDTO;
import com.isa.services.dto.ServiceDTO;
import com.isa.services.dto.SortDTO;
import com.isa.services.repository.CottageRepository;
import com.isa.services.repository.FishingLessonsRepository;
import com.isa.services.repository.ServiceRepository;
import com.isa.services.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ServiceService {

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    CottageRepository cottageRepository;

    @Autowired
    FishingLessonsRepository fishingLessonsRepository;

    @Autowired
    ServiceRepository serviceRepository;

    public List<ServiceDTO> search (SearchDataDTO dto){
        List<com.isa.services.Service> services;
        if(dto.getEntity().equals("SHIP")){
            List<com.isa.services.Service> ships = serviceRepository.findAll().stream().filter(s-> s instanceof Ship).collect(Collectors.toList());
            services = ships;
        }
        else if(dto.getEntity().equals("COTTAGE")){
            List<com.isa.services.Service> cottages =
                    serviceRepository.findAll().stream().filter(s-> s instanceof Cottage).collect(Collectors.toList());
            services = cottages;
        }
        else {
            List<com.isa.services.Service> fishingLessons =
                    serviceRepository.findAll().stream().filter(s-> s instanceof FishingLessons).collect(Collectors.toList());
            services = fishingLessons;
        }
        List<com.isa.services.Service> removal = new ArrayList<>();

        for(com.isa.services.Service s : services){
            List<TimePeriod> timePeriods = s.getPeriod();
            if(!dto.getLocation().equals("") && !s.getAddress().getCity().toLowerCase().contains(dto.getLocation().toLowerCase())){
                removal.add(s);

            }
            if(!dto.getGrade().equals("") && s.getGrade() != Double.parseDouble(dto.getGrade())){
                removal.add(s);

            }
            if(!dto.getNoGuests().equals("") && s.getNoGuests() != Integer.parseInt(dto.getNoGuests())){
                removal.add(s);

            }
            if(dto.getStartTime() != null && dto.getEndTime() != null){
                for(TimePeriod tp : timePeriods){
                    if(dto.getStartTime().isBefore(tp.getStart()) || dto.getEndTime().isAfter(tp.getEnd())){
                        removal.add(s);
                    }
                }

            }


        }
        services.removeAll(removal);

        return serviceMapper(services);
    }


    private List<ServiceDTO> serviceMapper(List<com.isa.services.Service> services){
        List<ServiceDTO> dtos = new ArrayList<>();
        for(com.isa.services.Service s: services){
            dtos.add(new ServiceDTO(s.getId(), s.getName(),s.getGrade(),s.getPrice(),s.getAddress().getStreetName(),
                    s.getAddress().getNumber(),s.getAddress().getCity(),s.getAddress().getState()));
        }

        return dtos;
    }

    public List<ServiceDTO> sort(SortDTO dto) {
        if(dto.getSortParam().equals("NAME_ASC")){

            Collections.sort(dto.getDto(), Comparator.comparing(d -> d.getName().toLowerCase()));
            return dto.getDto();
        }
        else if(dto.getSortParam().equals("NAME_DESC")){

            Collections.sort(dto.getDto(), Comparator.comparing(d -> d.getName().toLowerCase()));
            Collections.reverse(dto.getDto());
            return dto.getDto();
        }
        else if(dto.getSortParam().equals("CITY_ASC")){

            Collections.sort(dto.getDto(), Comparator.comparing(d -> d.getCity().toLowerCase()));
            return dto.getDto();
        }
        else if(dto.getSortParam().equals("CITY_DESC")){

            Collections.sort(dto.getDto(), Comparator.comparing(d -> d.getCity().toLowerCase()));
            Collections.reverse(dto.getDto());
            return dto.getDto();
        }
        else if(dto.getSortParam().equals("PRICE_ASC")){

            Collections.sort(dto.getDto(), Comparator.comparing(d -> d.getPrice()));
            return dto.getDto();
        }
        else if(dto.getSortParam().equals("PRICE_DESC")){

            Collections.sort(dto.getDto(), Comparator.comparing(d -> d.getPrice()));
            Collections.reverse(dto.getDto());
            return dto.getDto();
        }
        else if(dto.getSortParam().equals("GRADE_ASC")){

            Collections.sort(dto.getDto(), Comparator.comparing(d -> d.getGrade()));
            return dto.getDto();
        }
        else if(dto.getSortParam().equals("GRADE_DESC")){

            Collections.sort(dto.getDto(), Comparator.comparing(d -> d.getGrade()));
            Collections.reverse(dto.getDto());
            return dto.getDto();
        }


        return dto.getDto();
    }

    public List<ServiceDTO> filter(FilterDTO dto) {

        if(dto.getEntity().equals("SHIP")){
            List<ServiceDTO> removal = new ArrayList<>();
            for(ServiceDTO d: dto.getDtos()){
                Ship s = shipRepository.getShipById(d.getId());
                if(!dto.getType().contains(s.getType())){
                    removal.add(d);
                }
//                if(dto.getType().contains(s.getType()) && dto.getDtos().contains(d)){
//                    removal.add(d);
//                }
            }

            dto.getDtos().removeAll(removal);
            return dto.getDtos();
        }
        return dto.getDtos();
    }


    public Set<AdditionalInfo> getAdditionalInfoForService(Long serviceId){
        com.isa.services.Service s = serviceRepository.getServiceById(serviceId);
        Set<AdditionalInfo> addInfos = s.getAdditionalInfos();
        return addInfos;
    }
}
