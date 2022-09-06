package com.isa.services.service;

import com.isa.loyalties.Category;
import com.isa.loyalties.Points;
import com.isa.loyalties.repository.CategoryRepository;
import com.isa.loyalties.repository.PointsRepository;
import com.isa.services.*;
import com.isa.services.dto.*;
import com.isa.services.repository.*;
import com.isa.users.Client;
import com.isa.users.Seller;
import com.isa.users.repository.ClientRepository;
import com.isa.users.repository.ReservationRepository;
import com.isa.users.repository.SellerRepository;
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
    CategoryRepository categoryRepository;

    @Autowired
    PointsRepository pointsRepository;

    @Autowired
    TimePeriodRepository timePeriodRepository;

    @Autowired
    CottageRepository cottageRepository;

    @Autowired
    EarningPercentageRepository earningPercentageRepository;

    @Autowired
    SellerRepository sellerRepository;

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

    @Transactional
    public List<InhrShipDTO> searchShips (SearchDataDTO dto){
        List<Ship> ships = shipRepository.findAll().stream().filter(s -> !s.getDeleted()).collect(Collectors.toList());


        List<com.isa.services.Service> result = new ArrayList<>();

        for(Ship s : ships){
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

        ships.removeAll(result);
        return shipMapper(ships);
    }


    @Transactional
    public List<InhrCottageDTO> searchCottages (SearchDataDTO dto){
        List<Cottage> cottages =
                cottageRepository.findAll().stream().filter(s -> !s.getDeleted()).collect(Collectors.toList());


        List<com.isa.services.Service> result = new ArrayList<>();

        for(Cottage s : cottages){
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

        cottages.removeAll(result);
        return cottageMapper(cottages);
    }


    @Transactional
    public List<ServiceDTO> searchLessons (SearchDataDTO dto){
        List<FishingLessons> cottages =
                fishingLessonsRepository.findAll().stream().filter(s -> !s.getDeleted()).collect(Collectors.toList());


        List<com.isa.services.Service> result = new ArrayList<>();

        for(FishingLessons s : cottages){
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

        cottages.removeAll(result);
        return lessonMapper(cottages);
    }



    private List<InhrShipDTO> shipMapper(List<Ship> ships){
        List<InhrShipDTO> dtos = new ArrayList<>();


        for (Ship s : ships) {

            dtos.add(new InhrShipDTO(s.getId(), s.getName(), s.getGrade(), s.getPrice(), s.getAddress().getStreetName(),
                    s.getAddress().getNumber(), s.getAddress().getCity(), s.getAddress().getState(),
                    s.getNoGuests(), s.getType(), s.getLength(), s.getNoEngines(), s.getMaxSpeed(),
                    s.getEnginePower(), s.getImage()));
        }


        return dtos;
    }

    private List<InhrCottageDTO> cottageMapper(List<Cottage> ships){
        List<InhrCottageDTO> dtos = new ArrayList<>();


        for (Cottage s : ships) {

            dtos.add(new InhrCottageDTO(s.getId(), s.getName(), s.getGrade(), s.getPrice(), s.getAddress().getStreetName(),
                    s.getAddress().getNumber(), s.getAddress().getCity(), s.getAddress().getState(),
                    s.getNoGuests(), s.getNoRooms(), s.getNoBedsByRoom(), s.getImage()));
        }


        return dtos;
    }

    private List<ServiceDTO> lessonMapper(List<FishingLessons> ships){
        List<ServiceDTO> dtos = new ArrayList<>();


        for (FishingLessons s : ships) {

            dtos.add(new ServiceDTO(s.getId(), s.getName(), s.getGrade(), s.getPrice(), s.getAddress().getStreetName(),
                    s.getAddress().getNumber(), s.getAddress().getCity(), s.getAddress().getState(), s.getImage()));
        }


        return dtos;
    }

    @Transactional
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

    @Transactional
    public List<InhrCottageDTO> sortCottages(SortDTOCottage dto) {
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

    @Transactional
    public List<InhrShipDTO> sortShips(SortDTOShip dto) {
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

    @Transactional
    public List<InhrShipDTO> filter(FilterDTO dto) {

        if(dto.getEntity().equals("SHIP")){
            List<InhrShipDTO> removal = new ArrayList<>();
            for(InhrShipDTO d: dto.getDtos()){
                Ship s = shipRepository.getShipById(d.getId());
                if(!dto.getType().equalsIgnoreCase(s.getType())){
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


    @Transactional
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


//        serviceRepository.save(service);

        if(reservation ==null){
            float price = 0;
            float hours = ChronoUnit.HOURS.between(dto.getStart(), dto.getEnd());
            price+=service.getPrice() * hours;
            for(AdditionalInfo additionalInfo: dto.getAdditionalInfos()){
                price += additionalInfo.getPrice();
            }
            Optional<EarningPercentage> ep = earningPercentageRepository.findById(1L);
            Earnings earnings = new Earnings(LocalDate.now(), price*(ep.get().getPercentage()/100));
            Category goldClient = categoryRepository.findCategoryByNameAndType("GOLD", "CLIENT");
            Category silverClient = categoryRepository.findCategoryByNameAndType("SILVER", "CLIENT");
            Category bronzeClient = categoryRepository.findCategoryByNameAndType("BRONZE", "CLIENT");
            if(client.getPoints()>goldClient.getPoints()){
                price = price - price * goldClient.getDiscount();
            }
            else if(client.getPoints() > silverClient.getPoints()){
               price = price - price * silverClient.getDiscount();
            }
            else if(client.getPoints() > bronzeClient.getPoints()){
                price = price - price * bronzeClient.getDiscount();
            }
            Category goldSeller = categoryRepository.findCategoryByNameAndType("GOLD", "SELLER");
            Category silverSeller = categoryRepository.findCategoryByNameAndType("SILVER", "SELLER");
            Category bronzeSeller = categoryRepository.findCategoryByNameAndType("BRONZE", "SELLER");
            Seller seller = service.getSeller();
            float sellerMoney = 0;
            if(seller.getPoints()>goldSeller.getPoints()){
                sellerMoney = price - price * goldSeller.getDiscount()/100;
            }
            else if(seller.getPoints() > silverSeller.getPoints()){
                sellerMoney = price - price * silverSeller.getDiscount()/100;
            }
            else if(seller.getPoints() > bronzeSeller.getPoints()){
                sellerMoney = price - price * bronzeSeller.getDiscount()/100;
            }
            seller.setMoney(sellerMoney);

            earningsRepository.save(earnings);
            Optional<Points> points = pointsRepository.findById(1L);
            int sellerPoints = seller.getPoints();
            int clientPoints = client.getPoints();
            sellerPoints += points.get().getSellerPoints();
            clientPoints += points.get().getClientPoints();

            client.setPoints(clientPoints);
            seller.setPoints(sellerPoints);
            sellerRepository.save(seller);
            clientRepository.save(client);
            reservation = new Reservation(dto.getStart(), dto.getEnd(), dto.getNoPersons(),
                    dto.getAdditionalInfos(), price, service.getAddress(), service, client, false, true, false);

            reservationRepository.save(reservation);
            return  reservation;

        }



        EarningPercentage ep = earningPercentageRepository.getById(1L);
        Earnings earnings = new Earnings(LocalDate.now(), reservation.getDiscPrice()*(ep.getPercentage()/100));
        earningsRepository.save(earnings);
        float discPrice = reservation.getDiscPrice();
        Category goldClient = categoryRepository.findCategoryByNameAndType("GOLD", "CLIENT");
        Category silverClient = categoryRepository.findCategoryByNameAndType("SILVER", "CLIENT");
        Category bronzeClient = categoryRepository.findCategoryByNameAndType("BRONZE", "CLIENT");
        if(client.getPoints()>goldClient.getPoints()){
            discPrice = discPrice - discPrice * goldClient.getDiscount();
        }
        else if(client.getPoints() > silverClient.getPoints()){
            discPrice = discPrice - discPrice * silverClient.getDiscount();
        }
        else if(client.getPoints() > bronzeClient.getPoints()){
            discPrice = discPrice - discPrice * bronzeClient.getDiscount();
        }
        Category goldSeller = categoryRepository.findCategoryByNameAndType("GOLD", "SELLER");
        Category silverSeller = categoryRepository.findCategoryByNameAndType("SILVER", "SELLER");
        Category bronzeSeller = categoryRepository.findCategoryByNameAndType("BRONZE", "SELLER");
        Seller seller = service.getSeller();
        float sellerMoney = discPrice - 50*discPrice/100;
        if(seller.getPoints()>goldSeller.getPoints()){
            sellerMoney = discPrice - discPrice * goldSeller.getDiscount()/100;
        }
        else if(seller.getPoints() > silverSeller.getPoints()){
            sellerMoney = discPrice - discPrice * silverSeller.getDiscount()/100;
        }
        else if(seller.getPoints() > bronzeSeller.getPoints()){
            sellerMoney = discPrice - discPrice * bronzeSeller.getDiscount()/100;
        }
        seller.setMoney(sellerMoney);
        Optional<Points> points = pointsRepository.findById(1L);
        int sellerPoints = seller.getPoints();
        int clientPoints = client.getPoints();
        sellerPoints += points.get().getSellerPoints();
        clientPoints += points.get().getClientPoints();
        client.setPoints(clientPoints);
        seller.setPoints(sellerPoints);
        clientRepository.save(client);
        sellerRepository.save(seller);

        reservation.setDiscPrice(discPrice);
        reservation.setReserved(true);
        reservation.setCancelled(false);
        reservation.setClient(client);
        reservation.setDeleted(false);

//        reservation.get().setClient(client);

        reservationRepository.save(reservation);

        emailSender.sendEmail(client.getEmail(), ClientService.buildEmail("", "", "RES", ""), "RES");
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
        com.isa.services.Service service = serviceRepository.findById(reservation.getService().getId()).get();


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

//    @Transactional
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
        LocalDateTime now = LocalDateTime.now();
        for(Reservation discountReservation: discountReservations){
            if(!discountReservation.getCancelled() && !discountReservation.getDeleted() && discountReservation.getStartTime().isAfter(now)) {
                Float discPrice = discountReservation.getDiscPrice();
                if(discPrice == null){
                    discPrice = 0F;
                }
                GetReservationDTO dto = new GetReservationDTO(discountReservation.getId(),
                        discountReservation.getStartTime(), discountReservation.getEndTime(),
                        discountReservation.getMaxCapacity(), discountReservation.getPrice(),
                        discPrice,
                        discountReservation.getAddress().getCity(), discountReservation.getAdditionalInfos(),
                        discountReservation.getService().getName(), discountReservation.getService().getImage());
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

    @Transactional
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


    @Transactional
    public List<GetReservationDTO> getPastShipReservations(String email){
        List<Reservation> reservations =
                reservationRepository.findAll().stream().filter(r -> r.getEndTime().isBefore(LocalDateTime.now()) && r.getClient().getEmail().equals(email)).collect(Collectors.toList());
        List<GetReservationDTO> result = new ArrayList<>();

        for(Reservation r : reservations){
            Float discPrice = r.getDiscPrice();
            if(discPrice == null){
                discPrice = 0F;
            }
            if(r.getService() instanceof Ship){
                result.add(new GetReservationDTO(r.getId(),r.getStartTime(),r.getEndTime(),r.getMaxCapacity(),
                        r.getPrice(),discPrice,r.getAddress().getCity(), r.getAdditionalInfos(),
                        r.getService().getName(), r.getService().getImage()));
            }
        }

        return result;
    }

    @Transactional
    public List<GetReservationDTO> getPastLessonsReservations(String email){
        List<Reservation> reservations =
                reservationRepository.findAll().stream().filter(r -> r.getEndTime().isBefore(LocalDateTime.now()) && r.getClient().getEmail().equals(email)).collect(Collectors.toList());
        List<GetReservationDTO> result = new ArrayList<>();

        for(Reservation r : reservations){
            Float discPrice = r.getDiscPrice();
            if(discPrice == null){
                discPrice = 0F;
            }
            if(r.getService() instanceof FishingLessons){
                result.add(new GetReservationDTO(r.getId(),r.getStartTime(),r.getEndTime(),r.getMaxCapacity(),
                        r.getPrice(),discPrice,r.getAddress().getCity(), r.getAdditionalInfos(),
                        r.getService().getName(),r.getService().getImage()));
            }
        }

        return result;
    }

    @Transactional
    public List<GetReservationDTO> getPastCottageReservations(String email){
        List<Reservation> reservations =
                reservationRepository.findAll().stream().filter(r -> r.getEndTime().isBefore(LocalDateTime.now()) && r.getClient().getEmail().equals(email)).collect(Collectors.toList());
        List<GetReservationDTO> result = new ArrayList<>();

        for(Reservation r : reservations){
            if(r.getService() instanceof Cottage){
                Float discPrice = r.getDiscPrice();
                if(discPrice == null){
                    discPrice = 0F;
                }
                GetReservationDTO dto =new GetReservationDTO(r.getId(),r.getStartTime(),r.getEndTime(),r.getMaxCapacity(),
                r.getPrice(),discPrice,r.getAddress().getCity(), r.getAdditionalInfos(),
                r.getService().getName(), r.getService().getImage());
                result.add(dto);
            }
        }

        return result;
    }

    @Transactional
    public Reservation findOneById(Long id) {


        Reservation reservation = reservationRepository.getReservationById(id);

        return reservation;
    }


    public CPPDTO getMisc(String email){
        Client client = clientRepository.findByEmail(email);
        Integer points = client.getPoints();
        String category;
        Category goldClient = categoryRepository.findCategoryByNameAndType("GOLD", "CLIENT");
        Category silverClient = categoryRepository.findCategoryByNameAndType("SILVER", "CLIENT");
        Category bronzeClient = categoryRepository.findCategoryByNameAndType("BRONZE", "CLIENT");
        if(points > goldClient.getPoints()){
            category = "GOLD";
        }
        else if(points  > silverClient.getPoints()){
            category = "SILVER";
        }
        else if(points  > bronzeClient.getPoints()){
            category = "BRONZE";
        }
        else {
            category = "NONE";
        }
        return new CPPDTO(points, category, client.getPenalties());

    }

}
