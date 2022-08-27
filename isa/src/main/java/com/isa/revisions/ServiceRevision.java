package com.isa.revisions;

import com.isa.services.Service;
import com.isa.users.Client;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.lang.annotation.Target;

@Entity
@Table(name = "service_revisions")
public class ServiceRevision extends Revision{

    @ManyToOne(fetch = FetchType.EAGER)
    private Service service;

    public ServiceRevision(){}

    public ServiceRevision(Service service) {
        this.service = service;
    }

    public ServiceRevision(Integer grade, String text, Integer status, Client client, Service service) {
        super(grade, text, status, client);
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
