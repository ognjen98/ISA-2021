package com.isa.requests;

import com.isa.users.Client;
import com.isa.users.Seller;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

public class SellerComplaint extends Complaint{

    @ManyToOne(fetch = FetchType.EAGER)
    private Seller seller;

    public SellerComplaint(){}

    public SellerComplaint(Client client, String text, Integer status, Seller seller) {
        super(client, text, status);
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
