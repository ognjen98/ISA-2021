package com.isa.requests;

import com.isa.users.User;

import javax.persistence.*;

@Entity
@Table(name = "delete_requests")
public class DeleteRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    private Integer status;

    public DeleteRequest(){}

    public DeleteRequest(Long id, String message, User user, Integer status) {
        this.id = id;
        this.message = message;
        this.user = user;
        this.status = status;
    }

    public DeleteRequest(String message, User user, Integer status) {
        this.message = message;
        this.user = user;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
