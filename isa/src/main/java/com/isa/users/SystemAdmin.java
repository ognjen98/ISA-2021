package com.isa.users;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="sys_admins")
public class SystemAdmin extends User{

    private Boolean firstTimeLogin;
    public SystemAdmin(){}

    public SystemAdmin(Long id, String name, String surname, String email, String mobile, String password, List<Role> roles, Boolean enabled, Boolean deleted, Boolean firstTimeLogin) {
        super(id, name, surname, email, mobile, password, roles, enabled, deleted);
        this.firstTimeLogin = firstTimeLogin;
    }

    public SystemAdmin(String name, String surname, String email, Address address, String mobile, String password, List<Role> roles, Boolean enabled, Boolean deleted, Boolean firstTimeLogin) {
        super(name, surname, email, address, mobile, password, roles, enabled, deleted);
        this.firstTimeLogin = firstTimeLogin;
    }

    public Boolean getFirstTimeLogin() {
        return firstTimeLogin;
    }

    public void setFirstTimeLogin(Boolean firstTimeLogin) {
        this.firstTimeLogin = firstTimeLogin;
    }
}
