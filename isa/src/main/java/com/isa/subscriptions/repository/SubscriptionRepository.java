package com.isa.subscriptions.repository;

import com.isa.services.Service;
import com.isa.subscriptions.Subscription;
import com.isa.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> getSubscriptionsByClient(Client client);
    List<Subscription> getSubscriptionsByService(Service service);
    Subscription getSubscriptionByServiceAndClient(Service service, Client client);
}
