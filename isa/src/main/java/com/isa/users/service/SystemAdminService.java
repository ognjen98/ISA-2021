package com.isa.users.service;

import com.isa.loyalties.Category;
import com.isa.loyalties.Points;
import com.isa.loyalties.dto.CategoryDTO;
import com.isa.loyalties.repository.CategoryRepository;
import com.isa.loyalties.repository.PointsRepository;
import com.isa.users.Role;
import com.isa.users.Seller;
import com.isa.users.SystemAdmin;
import com.isa.users.dto.AdminDTO;
import com.isa.users.dto.UserDTO;
import com.isa.users.repository.RoleRepository;
import com.isa.users.repository.SellerRepository;
import com.isa.users.repository.SystemAdminRepository;
import com.isa.users.service.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.isa.users.service.ClientService.buildEmail;

@Service
public class SystemAdminService {

    @Autowired
    SystemAdminRepository systemAdminRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    PointsRepository pointsRepository;

    @Autowired
    EmailSender emailSender;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    SellerRepository sellerRepository;

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
                passwordEncoder.encode("123"), roles, true, false, true, 1);

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

    @Transactional
    public Category createCategory(Category dto){
        Category category = categoryRepository.findCategoryByNameAndType(dto.getName(), dto.getType());

        if(category == null) {
            category = new Category(dto.getName(), dto.getDiscount(), dto.getPoints(), dto.getType());
            categoryRepository.save(category);
        }
        else{
            category.setDiscount(dto.getDiscount());
            category.setName(dto.getName());
            category.setPoints(dto.getPoints());
            categoryRepository.save(category);
        }

        return category;
    }

    @Transactional
    public Points setPoints(Points dto){
        Optional<Points> points = pointsRepository.findById(1L);
        if(points.isEmpty()){
            points = Optional.of(new Points(dto.getId(), dto.getClientPoints(), dto.getSellerPoints()));

        }
        else {
            points.get().setClientPoints(dto.getClientPoints());
            points.get().setSellerPoints(dto.getSellerPoints());

        }

        pointsRepository.save(points.get());
        return points.get();

    }

    public List<UserDTO> getRegRequests(){
        List<Seller> sellers =
                sellerRepository.findAll().stream().filter(s -> s.getApproved() == 2).collect(Collectors.toList());
        List<UserDTO> dtos = new ArrayList<>();
        for(Seller seller : sellers){
            dtos.add(new UserDTO(seller.getId(), seller.getEmail(), seller.getName(), seller.getSurname()));
        }
        return dtos;
    }

    @Transactional
    public String acceptReg(Long id){
        Optional<Seller> seller = sellerRepository.findById(id);
        seller.get().setApproved(1);
        emailSender.sendEmail(seller.get().getEmail(), buildEmail("", "", "REQ_APPR", ""),
                "REQ_APPR");
        return "Account activated successfully";
    }

    @Transactional
    public String rejectReg(Long id, String message){
        Optional<Seller> seller = sellerRepository.findById(id);
        seller.get().setApproved(0);
        emailSender.sendEmail(seller.get().getEmail(), buildEmail("", "", "REQ_REJ", message), "REQ_REJ");
        return "Account activation rejected";
    }



}
