package com.isa.subscriptions.service;

import com.isa.services.Reservation;
import com.isa.services.repository.ServiceRepository;
import com.isa.subscriptions.Subscription;
import com.isa.subscriptions.dto.ResActionDTO;
import com.isa.subscriptions.dto.SubscriptionDTO;
import com.isa.subscriptions.repository.SubscriptionRepository;
import com.isa.users.Client;
import com.isa.users.repository.ClientRepository;
import com.isa.users.repository.ReservationRepository;
import com.isa.users.service.email.EmailSender;
import com.isa.users.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.isa.users.service.ClientService.buildEmail;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    EmailSender emailSender;

    public String createSubscription(Long serviceId, String email){
        Client client = clientRepository.findByEmail(email);
        com.isa.services.Service service = serviceRepository.getServiceById(serviceId);
        Subscription s = subscriptionRepository.getSubscriptionByServiceAndClient(service, client);
        if(s != null){
            return "Subscription already exists";
        }

        Subscription subscription = new Subscription(service, client, false);
        subscriptionRepository.save(subscription);

        return "Subscription added";
    }

    public List<SubscriptionDTO> subbedServices(String email){
        Client client = clientRepository.findByEmail(email);
        List<Subscription> subscriptions = subscriptionRepository.getSubscriptionsByClient(client);
        List<SubscriptionDTO> dtos = new ArrayList<>();
        for(Subscription subscription : subscriptions){
            if(!subscription.getCancelled()) {
                dtos.add(new SubscriptionDTO(subscription.getId(), subscription.getService().getName(),
                        subscription.getService().getAddress().getCity()));
            }
        }

        return dtos;
    }

    @Transactional
    public String cancelSubscription(Long id){
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        subscription.get().setCancelled(true);
        subscriptionRepository.save(subscription.get());
        return "Subscription cancelled";
    }

    public String addResAction(ResActionDTO dto){
        com.isa.services.Service service = serviceRepository.getServiceById(dto.getServiceId());
        Reservation reservation = new Reservation(dto.getPrice(),dto.getDiscount(), service);

        reservationRepository.save(reservation);
        List<Subscription> subscriptions = subscriptionRepository.getSubscriptionsByService(service);
        for(Subscription subscription: subscriptions){
            if(!subscription.getCancelled()){
//                emailSender.sendEmail(subscription.getClient().getEmail(), buildEmail(service.getName() + "added new " +
//                                "action", "", "SUB"),
//                        "SUB");
            }
        }
        return "Discount reservation added";
    }
}
