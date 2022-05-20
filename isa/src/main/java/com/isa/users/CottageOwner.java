package com.isa.users;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cottage_owners")
public class CottageOwner extends Seller{

    public CottageOwner(){}
}
