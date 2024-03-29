package com.isa.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    private Boolean firstTimeLogin;

    private Integer approved;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {})
    private Address address;

    @Column
    private String mobile;

    @Column
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private List<Role> roles;

    @Column
    private Boolean enabled;

    private Boolean deleted;

    public User(){}

    public User(Long id, String name,String surname, String email, String mobile, String password, List<Role> roles,
                Boolean enabled, Boolean deleted, Integer approved, Boolean firstTimeLogin) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled;
        this.approved = approved;
        this.firstTimeLogin = firstTimeLogin;
        this.deleted = deleted;
    }

    public User(String name, String surname, String email, Address address, String mobile, String password,
                List<Role> roles,
                Boolean enabled, Boolean deleted, Integer approved, Boolean firstTimeLogin) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.address = address;
        this.firstTimeLogin = firstTimeLogin;
        this.mobile = mobile;
        this.password = password;
        this.roles = roles;
        this.enabled = enabled;
        this.deleted = deleted;
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
////        Role role = getRole();
////        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
////
////        authorities.add(new SimpleGrantedAuthority(role.getName()));
////
////
////        return authorities;
//        return null;
//    }

    public String getPassword() {
        return password;
    }


    public String getUsername() {
        return email;
    }

//    @JsonIgnore
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @JsonIgnore
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @JsonIgnore
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }



    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> role) {
        this.roles = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }

    public Boolean getFirstTimeLogin() {
        return firstTimeLogin;
    }

    public void setFirstTimeLogin(Boolean firstTimeLogin) {
        this.firstTimeLogin = firstTimeLogin;
    }
}
