package com.isa.users;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="ship_owners")
public class ShipOwner extends Seller{

    public ShipOwner(){}
}
