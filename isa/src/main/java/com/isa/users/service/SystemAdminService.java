package com.isa.users.service;

import com.isa.users.Role;
import com.isa.users.SystemAdmin;
import com.isa.users.dto.AdminDTO;
import com.isa.users.repository.RoleRepository;
import com.isa.users.repository.SystemAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemAdminService {

    @Autowired
    SystemAdminRepository systemAdminRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    public Boolean checkFirstTimeLogin(String email){
        SystemAdmin systemAdmin = systemAdminRepository.findByEmail(email);
        return systemAdmin.getFirstTimeLogin();
    }

    public SystemAdmin createAdmin(AdminDTO dto){
        boolean userExists = systemAdminRepository.existsSystemAdminByEmail(dto.getEmail());
        if(userExists){
            return null;
        }
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("SYSTEM_ADMIN"));
        SystemAdmin systemAdmin = new SystemAdmin(dto.getName(), dto.getSurname(), dto.getEmail(), null, null,
                passwordEncoder.encode("123"), roles, true, false, true);

        systemAdminRepository.save(systemAdmin);
        return systemAdmin;
    }

    @Transactional
    public String changePass(String pass, String email){
        SystemAdmin systemAdmin = systemAdminRepository.findByEmail(email);
        systemAdmin.setPassword(passwordEncoder.encode(pass));
        systemAdmin.setFirstTimeLogin(false);
        return "Password changed";
    }

}
