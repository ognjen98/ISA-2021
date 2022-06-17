package com.isa.users.service;

import com.isa.users.Address;
import com.isa.users.User;
import com.isa.users.dto.UpdateInfoDTO;
import com.isa.users.repository.AddressRepository;
import com.isa.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User updateInfo(UpdateInfoDTO dto, String email){
        User u = userRepository.findByEmail(email);
        u.setName(dto.getName());
        u.setSurname(dto.getSurname());
        u.setMobile(dto.getMobile());
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        Address a = u.getAddress();
        a.setState(dto.getState());
        a.setCity(dto.getCity());
        a.setNumber(dto.getNumber());
        a.setStreetName(dto.getStreetName());
        u.setAddress(a);
        addressRepository.save(a);
        userRepository.save(u);
        return u;
    }


}
