package com.isa.users;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sys_admins")
public class SystemAdmin extends User{

    public SystemAdmin(){}
}
