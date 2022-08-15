package com.isa.users.service;

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
    private AddressRepository addressRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    TimePeriodRepository timePeriodRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


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
            if(reservations != null) {
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
            for(com.isa.services.Service service : services){

                List<Reservation> reservations = reservationRepository.getReservationsByServiceId(service.getId());
                if(reservations != null) {
                    for (Reservation r : reservations) {
                        r.setReserved(false);
                        r.setCancelled(true);
                        r.setClient(null);
                        r.setDeleted(true);
                    }
                }
                service.setDeleted(true);

            }
            seller.setDeleted(true);
            return seller;
        }
        return null;
    }


    public List<UserDTO> getAllUsers(){
        List<User> users =
                userRepository.findAll().stream().filter(u -> !(u instanceof SystemAdmin) && !u.getDeleted()).collect(Collectors.toList());
        List<UserDTO> dtos = new ArrayList<>();
        for(User u : users){
            dtos.add(new UserDTO(u.getId(), u.getEmail(),u.getName(),u.getSurname()));
        }
        return dtos;
    }
}
