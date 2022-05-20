package com.isa.revisions;

import com.isa.users.Client;

import javax.persistence.*;

@Entity
@Table(name = "revisions")
@Inheritance(strategy = InheritanceType.JOINED)
public class Revision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer grade;

    private String text;

    private Integer status;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    public Revision(){}

    public Revision(Long id, Integer grade, String text, Integer status, Client client) {
        this.id = id;
        this.grade = grade;
        this.text = text;
        this.status = status;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
