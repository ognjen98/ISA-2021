package com.isa.users;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="instructors")
public class Instructor extends Seller{

    public Instructor(){}
}
