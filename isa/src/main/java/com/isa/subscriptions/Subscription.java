package com.isa.subscriptions;

import com.isa.services.Service;
import com.isa.users.Client;

import javax.persistence.*;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Service service;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    private Boolean cancelled;

    public Subscription(){}

    public Subscription(Long id, Service service, Client client, Boolean cancelled) {
        this.id = id;
        this.service = service;
        this.client = client;
        this.cancelled = cancelled;
    }

    public Subscription(Service service, Client client, Boolean cancelled) {
        this.service = service;
        this.client = client;
        this.cancelled = cancelled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }
}
