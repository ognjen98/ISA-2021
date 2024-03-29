package com.isa.requests;

import com.isa.services.Service;
import com.isa.users.Client;

import javax.persistence.*;
import java.rmi.server.ServerCloneException;

@Entity
@Table(name = "complaints")
@Inheritance(strategy = InheritanceType.JOINED)
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private Integer status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    @Version
    private Integer version;

    public Complaint(){}

    public Complaint(Long id, Client client, String text, Integer status) {
        this.id = id;
        this.client = client;
        this.text = text;
        this.status = status;

    }

    public Complaint(Client client, String text, Integer status) {
        this.client = client;
        this.text = text;
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
