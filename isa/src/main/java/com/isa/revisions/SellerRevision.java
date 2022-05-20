package com.isa.revisions;

import com.isa.users.Seller;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "seller_revisions")
public class SellerRevision extends Revision{

    @ManyToOne(fetch = FetchType.EAGER)
    private Seller seller;

    public SellerRevision(){}

    public SellerRevision(Seller seller) {
        this.seller = seller;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
