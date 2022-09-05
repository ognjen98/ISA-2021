package com.isa.users.service;

import com.isa.requests.DeleteRequest;
import com.isa.requests.dto.DeleteRequestDTO;
import com.isa.requests.repository.DeleteRequestRepository;
import com.isa.services.Reservation;
import com.isa.services.TimePeriod;
import com.isa.services.repository.ServiceRepository;
import com.isa.services.repository.TimePeriodRepository;
import com.isa.users.*;
import com.isa.users.dto.UpdateInfoDTO;
import com.isa.users.dto.UserDTO;
import com.isa.users.repository.AddressRepository;
import com.isa.users.repository.ClientRepository;
import com.isa.users.repository.ReservationRepository;
import com.isa.users.repository.UserRepository;
import com.isa.users.service.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    EmailSender emailSender;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    TimePeriodRepository timePeriodRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    DeleteRequestRepository deleteRequestRepository;


    @Transactional
    public User updateInfo(UpdateInfoDTO dto, String email){
        User u = userRepository.findByEmail(email);
        if(u.getDeleted()){
            return null;
        }
        u.setName(dto.getName());
        u.setSurname(dto.getSurname());
        u.setMobile(dto.getMobile());
        Address a = u.getAddress();
        a.setState(dto.getState());
        a.setCity(dto.getCity());
        a.setNumber(dto.getNumber());
        a.setStreetName(dto.getStreetName());
        u.setAddress(a);
        addressRepository.save(a);
        userRepository.save(u);
        return u;
    }

    @Transactional
    public UpdateInfoDTO  getInfo(String email){
        User u = userRepository.findByEmail(email);
        if(u.getDeleted()){
            return null;
        }
        UpdateInfoDTO dto = new UpdateInfoDTO(u.getName(),u.getSurname(),u.getAddress().getCity(),
                u.getAddress().getState(),u.getAddress().getStreetName(),u.getAddress().getNumber(),u.getMobile());

        return dto;

    }


    @Transactional
    public User deleteUser(Long id){
        Optional<User> user = userRepository.findById(id);

        List<Role> roles = user.get().getRoles();
        if(roles.get(0).getName().equals("CLIENT")){
            Client client = (Client) user.get();
            Set<Reservation> cancelled = client.getCancelledReservations();
//            user.get().setDeleted(true);
            List<Reservation> reservations = reservationRepository.getReservationsByClientId(id);
            if(reservations != null || reservations.size() != 0) {
                for (Reservation r : reservations) {
                    if (!cancelled.contains(r)) {
                        com.isa.services.Service s = r.getService();
                        TimePeriod t1 = new TimePeriod();
                        TimePeriod t2 = new TimePeriod();
                        for (TimePeriod tp : s.getPeriod()) {
                            if (r.getStartTime().isEqual(tp.getEnd())) {
                                t1 = tp;

                            } else if (r.getEndTime().isEqual(tp.getStart())) {
                                t2 = tp;
                            }


                        }
                        TimePeriod newPeriod = new TimePeriod(t1.getStart(), t2.getEnd());
                        timePeriodRepository.save(newPeriod);

                        s.getPeriod().remove(t1);
                        s.getPeriod().remove(t2);
                        s.getPeriod().add(newPeriod);

                        serviceRepository.save(s);

                    }
                    r.setReserved(false);
                    r.setCancelled(true);
                    r.setClient(null);
                    if(r.getDiscPrice() == null) {
                        r.setDeleted(true);
                    }
                }
            }

//            reservationRepository.deleteAll(reservations);
            client.setDeleted(true);
            return client;
        }
        else if(roles.get(0).getName().equals("COTTAGE_OWNER") || roles.get(0).getName().equals("SHIP_OWNER") || roles.get(0).getName().equals(
                "INSTRUCTOR")){
            Seller seller = (Seller) user.get();
            List<com.isa.services.Service> services = serviceRepository.getServicesBySellerId(seller.getId());
            if(services != null || services.size() != 0) {
                for (com.isa.services.Service service : services) {

                    List<Reservation> reservations = reservationRepository.getReservationsByServiceId(service.getId());
                    if (reservations == null || reservations.size() == 0) {
                        service.setDeleted(true);

                    } else {
                        return null;
                    }


                }
            }
            seller.setDeleted(true);
            return seller;
        }
        return null;
    }

    @Transactional
    public List<UserDTO> getAllUsers(){
        List<User> users =
                userRepository.findAll().stream().filter(u -> !(u instanceof SystemAdmin) && !u.getDeleted()).collect(Collectors.toList());
        List<UserDTO> dtos = new ArrayList<>();
        for(User u : users){
            dtos.add(new UserDTO(u.getId(), u.getEmail(),u.getName(),u.getSurname()));
        }
        return dtos;
    }


    @Transactional
    public String deleteUserAccept(Long id, String reason){
        Optional<User> user = userRepository.findById(id);

        List<Role> roles = user.get().getRoles();
        if(roles.get(0).getName().equals("CLIENT")){
            Client client = (Client) user.get();
            Set<Reservation> cancelled = client.getCancelledReservations();
//            user.get().setDeleted(true);
            List<Reservation> reservations = reservationRepository.getReservationsByClientId(id);
            if(reservations != null || reservations.size() != 0) {
                for (Reservation r : reservations) {
                    if (!cancelled.contains(r)) {
                        com.isa.services.Service s = r.getService();
                        TimePeriod t1 = new TimePeriod();
                        TimePeriod t2 = new TimePeriod();
                        for (TimePeriod tp : s.getPeriod()) {
                            if (r.getStartTime().isEqual(tp.getEnd())) {
                                t1 = tp;

                            } else if (r.getEndTime().isEqual(tp.getStart())) {
                                t2 = tp;
                            }


                        }
                        TimePeriod newPeriod = new TimePeriod(t1.getStart(), t2.getEnd());
                        timePeriodRepository.save(newPeriod);

                        s.getPeriod().remove(t1);
                        s.getPeriod().remove(t2);
                        s.getPeriod().add(newPeriod);

                        serviceRepository.save(s);

                    }
                    r.setReserved(false);
                    r.setCancelled(true);
                    r.setClient(null);
                    if(r.getDiscPrice() == null) {
                        r.setDeleted(true);
                    }
                }
            }

//            reservationRepository.deleteAll(reservations);
            client.setDeleted(true);

        }
        else if(roles.get(0).getName().equals("COTTAGE_OWNER") || roles.get(0).getName().equals("SHIP_OWNER") || roles.get(0).getName().equals(
                "INSTRUCTOR")){
            Seller seller = (Seller) user.get();
            List<com.isa.services.Service> services = serviceRepository.getServicesBySellerId(seller.getId());
            if(services != null || services.size() != 0) {
                for (com.isa.services.Service service : services) {

                    List<Reservation> reservations = reservationRepository.getReservationsByServiceId(service.getId());
                    if (reservations == null || reservations.size() == 0) {
                        service.setDeleted(true);

                    } else {
                        return null;
                    }


                }
            }
            seller.setDeleted(true);

        }
        else if(roles.get(0).getName().equals("SYSTEM_ADMIN")){
            SystemAdmin systemAdmin = (SystemAdmin) user.get();
            systemAdmin.setDeleted(true);
        }

        DeleteRequest deleteRequest = deleteRequestRepository.findByUser(user.get());
        deleteRequest.setStatus(1);
        emailSender.sendEmail(user.get().getEmail(), ClientService.buildEmail("", "", "DEL", reason), "DEL");

        return "Deleted successfully";
    }

    @Transactional
    public String deleteUserReject(Long id, String reason){
        Optional<User> user = userRepository.findById(id);
        DeleteRequest deleteRequest = deleteRequestRepository.findByUser(user.get());
        deleteRequest.setStatus(0);
        emailSender.sendEmail(user.get().getEmail(), ClientService.buildEmail("", "", "DEL", reason), "DEL");
        return "Request rejected";
    }


    @Transactional
    public String makeDelRequest(String reason, String email){
        User user = userRepository.findByEmail(email);
        DeleteRequest dr = deleteRequestRepository.findByUser(user);
        if(dr != null){
            return "You already submitted a request";
        }

        if(user == null){
            return "You must login first";
        }
        DeleteRequest deleteRequest = new DeleteRequest(reason, user, 2);
        deleteRequestRepository.save(deleteRequest);
        return "Request made";

    }

    @Transactional
    public List<DeleteRequestDTO> getDelRequests(){
        List<DeleteRequest> deleteRequests = deleteRequestRepository.findAll();
        List<DeleteRequestDTO> dtos = new ArrayList<>();
        for(DeleteRequest deleteRequest : deleteRequests){
            if(deleteRequest.getStatus() == 2) {
                dtos.add(new DeleteRequestDTO(deleteRequest.getMessage(), deleteRequest.getUser().getId(),
                        deleteRequest.getUser().getEmail()));
            }
        }

        return dtos;
    }

}
