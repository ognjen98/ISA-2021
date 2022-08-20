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
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
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
    EntityManager entityManager;

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
            List<com.isa.services.Service> ships =
                    serviceRepository.findAll().stream().filter(s-> s instanceof Ship && !s.getDeleted()).collect(Collectors.toList());
            services = ships;

        }
        else if(dto.getEntity().equals("COTTAGE")){
            List<com.isa.services.Service> cottages =
                    serviceRepository.findAll().stream().filter(s-> s instanceof Cottage && !s.getDeleted()).collect(Collectors.toList());
            services = cottages;
        }
        else {
            List<com.isa.services.Service> fishingLessons =
                    serviceRepository.findAll().stream().filter(s-> s instanceof FishingLessons && !s.getDeleted()).collect(Collectors.toList());
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
        if(s.getDeleted()){
            return null;
        }
        Set<AdditionalInfo> addInfos = s.getAdditionalInfos();
        return addInfos;
    }



    @Transactional
    public Reservation reserve(ReservationDTO dto, String email){
        Reservation reservation = null;
        Client client = clientRepository.findByEmail(email);
        if(client.getPenalties()>3){
            return null;
        }
        if(client.getDeleted()){
            return null;
        }
        if(dto.getId() != null){
            reservation = reservationRepository.getReservationById(dto.getId());
            if(reservation.getDeleted()){
                return null;
            }
        }



        com.isa.services.Service service = serviceRepository.getServiceById(dto.getServiceId());
        if(service.getDeleted()){
            return null;
        }
        for(Reservation r : client.getCancelledReservations()){
            if(r.getStartTime().isEqual(dto.getStart()) && r.getEndTime().isEqual(dto.getEnd()) && r.getService().getId() == service.getId()){
                return null;
            }
        }

        List<Reservation> serviceReservations = reservationRepository.getReservationsByServiceId(service.getId());
        for(Reservation res : serviceReservations){
            if(dto.getId() == null) {
                if (((dto.getStart().isBefore(res.getStartTime()) || dto.getStart().isEqual(res.getStartTime())) && (dto.getEnd().isAfter(res.getStartTime())))
                        || ((dto.getStart().isBefore(res.getEndTime())) && (dto.getEnd().isAfter(res.getEndTime()) || dto.getEnd().isEqual(res.getEndTime())))) {
                    return null;
                }
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


        serviceRepository.save(service);

        if(reservation ==null){
            float price = 0;
            float hours = ChronoUnit.HOURS.between(dto.getStart(), dto.getEnd());
            price+=service.getPrice() * hours;
            for(AdditionalInfo additionalInfo: dto.getAdditionalInfos()){
                price += additionalInfo.getPrice();
            }
            EarningPercentage ep = earningPercentageRepository.getById(1L);
            Earnings earnings = new Earnings(LocalDate.now(), price*(ep.getPercentage()/100));
            earningsRepository.save(earnings);

            reservation = new Reservation(dto.getStart(), dto.getEnd(), dto.getNoPersons(),
                    dto.getAdditionalInfos(), price, service.getAddress(), service, client, false, true, false);
            reservationRepository.save(reservation);
            return  reservation;

        }



        EarningPercentage ep = earningPercentageRepository.getById(1L);
        Earnings earnings = new Earnings(LocalDate.now(), reservation.getDiscPrice()*(ep.getPercentage()/100));
        earningsRepository.save(earnings);
        reservation.setReserved(true);
        reservation.setCancelled(false);
        reservation.setClient(client);
        reservation.setDeleted(false);

//        reservation.get().setClient(client);
        entityManager.persist(reservation);

        //emailSender.sendEmail(client.getEmail(), ClientService.buildEmail("", "", "RES"), "RES");
        return reservation;

    }


    @Transactional
    public String cancel(Long resId, String email){
        Reservation reservation = reservationRepository.getReservationById(resId);
        Client client = clientRepository.findByEmail(email);
        if(reservation.getDeleted()){
            return "Reservation is deleted";
        }
        if(reservation.getCancelled() == true){
            return "Reservation is already cancelled";
        }

        LocalDateTime now = LocalDateTime.now();
        float days = ChronoUnit.DAYS.between(now, reservation.getStartTime());
        if(days <= 3){
            return null;
        }
        com.isa.services.Service service = reservation.getService();


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
        int penalties = client.getPenalties();

        client.setPenalties(++penalties);
        reservationRepository.save(reservation);
        clientRepository.save(client);

        return "Cancelled successfully";
    }

    public List<GetReservationDTO> getReservationsForClient(String email){
        Client client = clientRepository.findByEmail(email);
        if(client.getDeleted()){
            return null;
        }
        List<Reservation> reservations = reservationRepository.getReservationsByClientId(client.getId());

        return discountReservationDTOMapper(reservations);
    }

    private List<GetReservationDTO> discountReservationDTOMapper(List<Reservation> discountReservations){
        List<GetReservationDTO> dtos = new ArrayList<>();
        int i = 0;
        for(Reservation discountReservation: discountReservations){
            if(!discountReservation.getCancelled() || !discountReservation.getDeleted()) {
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

    public List<DayMonthValueDTO> getReport(ReportDTO dto){
        if(dto.getType().equals("Year")){
            List<Earnings> earnings =
                    earningsRepository.findAll().stream().filter(e -> e.getDateTime().getYear() == dto.getYear()).collect(Collectors.toList());
            Collections.sort(earnings, Comparator.comparing(e -> e.getDateTime()));
            List<Float> result = new ArrayList<>();

            float count = 0;
            float sum = 0;
            List<DayMonthValueDTO> dayMonthValueDTOList = new ArrayList<>();

            for(int i = 1; i <= 12; i++){
                int finalI = i;
                dayMonthValueDTOList.add(new DayMonthValueDTO(0, i,0, 0 ));
                List<Earnings> earningByMonth =
                        earnings.stream().filter(e -> e.getDateTime().getMonthValue() == finalI).collect(Collectors.toList());
                if(earningByMonth != null || earningByMonth.size() != 0){
                    for(Earnings earnings1: earningByMonth){
                        sum += earnings1.getMoney();
                        count++;


                    }
                    if(sum != 0 && count != 0){
                        dayMonthValueDTOList.get(i-1).setValue(sum);
                        dayMonthValueDTOList.get(i-1).setCount(count);
                        dayMonthValueDTOList.get(i-1).setAvg(sum/count);
                        sum = 0;
                        count = 0;
                    }

                }



            }
//            for(int i = 1; i <= 12; i++){
//                YearMonth yearMonthObject = YearMonth.of(dto.getYear(), i);
//                int daysInMonth = yearMonthObject.lengthOfMonth(); //28
//                for(int j =0; j < daysInMonth; j++){
//                    if(earnings.get())
//                }
//            }
//
//
//            for(Earnings e : earnings){
//
//                if(e.getDateTime().getMonthValue() == dayMonthValueDTOList.contains()){
//                    sum += e.getMoney();
//                    count++;
//                }
//
//            }
//
//            if(sum != 0 && count != 0){
//                result.add(sum/count);
//                sum = 0;
//                count = 0;
//            }
//            else{
//                result.add(0F);
//            }



            return dayMonthValueDTOList;
        }
        else if(dto.getType().equals("Month")){
            List<Earnings> earnings =
                    earningsRepository.findAll().stream().filter(e -> e.getDateTime().getYear() == dto.getYear() && e.getDateTime().getMonthValue() == dto.getMonth()).collect(Collectors.toList());
            Collections.sort(earnings, Comparator.comparing(e -> e.getDateTime()));
            YearMonth yearMonthObject = YearMonth.of(dto.getYear(), dto.getMonth());
            int daysInMonth = yearMonthObject.lengthOfMonth(); //28
            List<DayMonthValueDTO> dayMonthValueDTOList = new ArrayList<>();
            List<Float> result = new ArrayList<>();
            for(int i = 0; i < daysInMonth; i++){
                int finalI = i;
                dayMonthValueDTOList.add(new DayMonthValueDTO(0, i+1,0, 0 ));
                List<Earnings> earningByDay =
                        earnings.stream().filter(e -> e.getDateTime().getDayOfMonth() == finalI+1).collect(Collectors.toList());
                if(earningByDay != null || earningByDay.size() != 0){
                    for(Earnings earnings1 : earningByDay){
                        float earn = dayMonthValueDTOList.get(i).getValue();
                        float earnIncr = earn + earnings1.getMoney();
                        dayMonthValueDTOList.get(i).setValue(earnIncr);
                    }
                }


            }
//            for(Earnings e : earnings){
//                result.add(e.getMoney());
//            }
            return dayMonthValueDTOList;
        }
        else{
            List<Earnings> earnings =
                    earningsRepository.findAll().stream().filter(e -> (e.getDateTime().isAfter(dto.getStartTime()) || e.getDateTime().isEqual(dto.getStartTime())) && e.getDateTime().isBefore(dto.getEndTime()) ).collect(Collectors.toList());
            Collections.sort(earnings, Comparator.comparing(e -> e.getDateTime()));

            List<Float> result = new ArrayList<>();
            List<DayMonthValueDTO> dayMonthValueDTOList = new ArrayList<>();
            long days = ChronoUnit.DAYS.between(dto.getStartTime(), dto.getEndTime());
            for(int i = 0; i < days; i++){
                int finalI = i;
                dayMonthValueDTOList.add(new DayMonthValueDTO(0, i+1,0, 0 ));

                if(earnings != null || earnings.size() != 0){
                    for(Earnings earnings1 : earnings){
                        YearMonth yearMonthObject = YearMonth.of(dto.getStartTime().getYear(), dto.getStartTime().getMonthValue());
                        int daysInMonth = yearMonthObject.lengthOfMonth(); //28
                        if(dto.getStartTime().plusDays(i).isEqual(earnings1.getDateTime())) {
                            float earn = dayMonthValueDTOList.get(i).getValue();
                            float earnIncr = earn + earnings1.getMoney();
                            dayMonthValueDTOList.get(i).setValue(earnIncr);
                        }

                    }
                }


            }
            return dayMonthValueDTOList;
        }


    }

}
