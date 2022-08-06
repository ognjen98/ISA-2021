package com.isa.services.service;

import com.isa.services.*;
import com.isa.services.dto.*;
import com.isa.services.repository.*;
import com.isa.users.Client;
import com.isa.users.repository.ClientRepository;
import com.isa.users.repository.ReservationRepository;
import com.isa.users.service.ClientService;
import com.isa.users.service.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;


//import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service
public class ReservationService {

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    TimePeriodRepository timePeriodRepository;

    @Autowired
    CottageRepository cottageRepository;

    @Autowired
    EarningPercentageRepository earningPercentageRepository;

    @Autowired
    EarningsRepository earningsRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    FishingLessonsRepository fishingLessonsRepository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ReservationRepository reservationRepository;

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
        List<com.isa.services.Service> result = new ArrayList<>();

        for(com.isa.services.Service s : services){
            List<TimePeriod> timePeriods = s.getPeriod();


            if(dto.getStartTime() != null && dto.getEndTime() != null){
                for(TimePeriod tp : timePeriods){

                    if(dto.getStartTime().isAfter(tp.getStart()) && dto.getEndTime().isBefore(tp.getEnd())){
                        if(result.contains(s)) {
                            result.remove(s);
                            break;
                        }
                        break;
                    }
                    if ((dto.getStartTime().isBefore(tp.getStart()) && dto.getEndTime().isAfter(tp.getEnd()))
                            || (dto.getStartTime().isAfter(tp.getStart()) && dto.getEndTime().isAfter(tp.getEnd()))
                            || (dto.getStartTime().isBefore(tp.getStart()) && dto.getEndTime().isBefore(tp.getEnd()))) {
                        result.add(s);

                    }


                }

            }
            if(!dto.getLocation().equals("") && !s.getAddress().getCity().toLowerCase().contains(dto.getLocation().toLowerCase())){
                result.add(s);

            }
            if(!dto.getGrade().equals("") && s.getGrade() != Double.parseDouble(dto.getGrade())){
                result.add(s);

            }
            if(!dto.getNoGuests().equals("") && s.getNoGuests() < Integer.parseInt(dto.getNoGuests())){
                result.add(s);

            }



        }

        services.removeAll(result);
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



    @Transactional
    public Reservation reserve(ReservationDTO dto, String email){
        Reservation reservation = null;
        if(dto.getId() != null){
            reservation = reservationRepository.getReservationById(dto.getId());
        }

        Client client = clientRepository.findByEmail(email);

        com.isa.services.Service service = serviceRepository.getServiceById(dto.getServiceId());
        for(Reservation r : client.getCancelledReservations()){
            if(r.getStartTime().isEqual(dto.getStart()) && r.getEndTime().isEqual(dto.getEnd()) && r.getService().getId() == service.getId()){
                return null;
            }
        }
        List<TimePeriod> removal = new ArrayList<>();
        List<TimePeriod> addition = new ArrayList<>();
        for(TimePeriod tp: service.getPeriod()){
            if((dto.getStart().isAfter(tp.getStart()) || dto.getStart().isEqual(tp.getStart())) && (dto.getEnd().isBefore(tp.getEnd()) || dto.getEnd().isEqual(tp.getEnd()))){
                removal.add(tp);
                TimePeriod first = new TimePeriod(tp.getStart(), dto.getStart());
                TimePeriod second = new TimePeriod(dto.getEnd(), tp.getEnd());
                timePeriodRepository.save(first);
                timePeriodRepository.save(second);

                addition.add(first);
                addition.add(second);
            }


        }
        if(addition.size() == 0){
            return null;
        }
        service.getPeriod().removeAll(removal);
        service.getPeriod().addAll(addition);
        if(reservation ==null){
            float price = 0;
            for(AdditionalInfo additionalInfo: dto.getAdditionalInfos()){
                price += additionalInfo.getPrice();
            }
            float hours = ChronoUnit.HOURS.between(dto.getStart(), dto.getEnd());
            price+=service.getPrice() * hours;
            EarningPercentage ep = earningPercentageRepository.getById(1L);
            Earnings earnings = new Earnings(LocalDateTime.now(), price*(ep.getPercentage()/100));
            earningsRepository.save(earnings);
            reservation = new Reservation(dto.getStart(), dto.getEnd(), dto.getNoPersons(),
                    dto.getAdditionalInfos(), price, service.getAddress(), service, client, false, true);

        }

        reservation.setReserved(true);
        reservation.setCancelled(false);
        reservation.setClient(client);
        serviceRepository.save(service);
//        reservation.get().setClient(client);
        reservationRepository.save(reservation);

        //emailSender.sendEmail(client.getEmail(), ClientService.buildEmail("", "", "RES"), "RES");
        return reservation;

    }


    @Transactional
    public String cancel(Long resId, String email){
        Reservation reservation = reservationRepository.getReservationById(resId);
        if(reservation.getCancelled() == true){
            return "It is already cancelled";
        }
        LocalDateTime now = LocalDateTime.now();
        float days = ChronoUnit.DAYS.between(now, reservation.getStartTime());
        if(days <= 3){
            return null;
        }
        com.isa.services.Service service = reservation.getService();
        Client client = clientRepository.findByEmail(email);

        TimePeriod t1 = new TimePeriod();
        TimePeriod t2 = new TimePeriod();
        for(TimePeriod tp: service.getPeriod()){
            if(reservation.getStartTime().isEqual(tp.getEnd())){
                t1 = tp;

            }
            else if(reservation.getEndTime().isEqual(tp.getStart())){
                t2 = tp;
            }


        }
        TimePeriod newPeriod = new TimePeriod(t1.getStart(), t2.getEnd());
        timePeriodRepository.save(newPeriod);

        service.getPeriod().remove(t1);
        service.getPeriod().remove(t2);
        service.getPeriod().add(newPeriod);

        serviceRepository.save(service);
        reservation.setReserved(false);
        reservation.setCancelled(true);
        reservation.setClient(null);
        //transactionTemplate.execute(transactionStatus -> reservationRepository.save(reservation.get()));
        client.getCancelledReservations().add(reservation);
        reservationRepository.save(reservation);
        clientRepository.save(client);

        return "Cancelled successfully";
    }

    public List<GetReservationDTO> getReservationsForClient(String email){
        Client client = clientRepository.findByEmail(email);
        List<Reservation> reservations = reservationRepository.getReservationsByClientId(client.getId());

        return discountReservationDTOMapper(reservations);
    }

    private List<GetReservationDTO> discountReservationDTOMapper(List<Reservation> discountReservations){
        List<GetReservationDTO> dtos = new ArrayList<>();
        int i = 0;
        for(Reservation discountReservation: discountReservations){
            if(!discountReservation.getCancelled()) {
                GetReservationDTO dto = new GetReservationDTO(discountReservation.getId(),
                        discountReservation.getStartTime(), discountReservation.getEndTime(),
                        discountReservation.getMaxCapacity(), discountReservation.getPrice(),
                        discountReservation.getAddress().getCity(), discountReservation.getAdditionalInfos());
                dtos.add(dto);
            }
        }

        return dtos;
    }

    @Transactional
    public EarningPercentage defineEarningPercentage(Float percentage){
        EarningPercentage ep = new EarningPercentage(1L, percentage);
        earningPercentageRepository.save(ep);
        return ep;
    }

}
