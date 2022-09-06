package com.isa.requests;

import com.isa.users.Client;

import javax.persistence.*;

@Entity
@Table(name = "penalty_requests")
public class PenaltyRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private Integer status;

    private Long clientId;

    private Long sellerId;

    public PenaltyRequest(){}

    public PenaltyRequest(Long id, String text, Integer status, Long clientId, Long sellerId) {
        this.id = id;
        this.text = text;
        this.status = status;
        this.clientId = clientId;
        this.sellerId = sellerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
