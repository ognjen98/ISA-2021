package com.isa.requests;

import com.isa.services.Service;
import com.isa.users.Client;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class ServiceComplaint extends Complaint{

    @ManyToOne(fetch = FetchType.EAGER)
    private Service service;

    public ServiceComplaint(){}

    public ServiceComplaint(Client client, String text, Integer status, Service service) {
        super(client, text, status);
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
