package com.isa.revisions.service;

import com.isa.revisions.Revision;
import com.isa.revisions.SellerRevision;
import com.isa.revisions.ServiceRevision;
import com.isa.revisions.dto.RevisionDTO;
import com.isa.revisions.repository.RevisionRepository;
import com.isa.revisions.repository.SellerRevisionRepository;
import com.isa.revisions.repository.ServiceRevisionRepository;
import com.isa.services.repository.ServiceRepository;
import com.isa.users.Client;
import com.isa.users.Seller;
import com.isa.users.repository.ClientRepository;
import com.isa.users.repository.SellerRepository;
import com.isa.users.service.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.isa.users.service.ClientService.buildEmail;

@Service
public class RevisionService {

    @Autowired
    RevisionRepository revisionRepository;

    @Autowired
    EmailSender emailSender;

    @Autowired
    ServiceRevisionRepository serviceRevisionRepository;

    @Autowired
    SellerRevisionRepository sellerRevisionRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Transactional
    public String saveRevision(RevisionDTO dto, String email){
        Client client = clientRepository.findByEmail(email);
        com.isa.services.Service service = serviceRepository.getServiceById(dto.getServiceId());
        ServiceRevision serRev = serviceRevisionRepository.getServiceRevisionByClientAndService(client, service);
        SellerRevision selRev = sellerRevisionRepository.getSellerRevisionByClientAndSeller(client, service.getSeller());



        if(dto.getType().equals("SELLER")){
            if(selRev != null){
                return "Already revised";
            }
            SellerRevision revision = new SellerRevision(dto.getGrade(), dto.getText(), 2, client, service.getSeller());
            revisionRepository.save(revision);
            return "Seller revision saved successfully";
        }
        else if(dto.getType().equals("SERVICE")) {
            if(serRev != null){
                return "Already revised";
            }
            ServiceRevision serviceRevision = new ServiceRevision(dto.getGrade(),dto.getText(), 2, client, service);
            revisionRepository.save(serviceRevision);
            return "Service revision saved successfully";
        }

        return "There was a problem";
    }


    @Transactional
    public List<Revision> getPendingRevisions(){
        List<Revision> revisions =
                revisionRepository.findAll().stream().filter(r -> r.getStatus() == 2).collect(Collectors.toList());

        return revisions;
    }

    @Transactional
    public String approve(Long id){
        Optional<Revision> revision = revisionRepository.findById(id);

        if(revision.get() instanceof  ServiceRevision){
            ServiceRevision serviceRevision = (ServiceRevision) revision.get();
            float grade = 0;
            int count = 0;
            List<ServiceRevision> serviceRevisionList =
                    serviceRevisionRepository.getServiceRevisionsByService(serviceRevision.getService());
            if(serviceRevisionList != null && serviceRevisionList.size() != 0) {
                for (ServiceRevision sr : serviceRevisionList) {
                    grade += sr.getGrade();
                    count++;

                }
                grade = grade / count;
                serviceRevision.getService().setGrade(grade);
            }
            else{
                serviceRevision.getService().setGrade(Float.valueOf(serviceRevision.getGrade()));
            }


            serviceRepository.save(serviceRevision.getService());
            serviceRevision.setStatus(1);
            emailSender.sendEmail(serviceRevision.getService().getSeller().getEmail(), buildEmail("", "", "REV", serviceRevision.getText()),
                    "REV");

        }
        else if(revision.get() instanceof SellerRevision){
            SellerRevision sellerRevision = (SellerRevision) revision.get();
            float grade = 0;
            int count = 0;
            List<SellerRevision> serviceRevisionList =
                    sellerRevisionRepository.getSellerRevisionsBySeller(sellerRevision.getSeller());
            if(serviceRevisionList != null && serviceRevisionList.size() != 0) {
                for (SellerRevision sr : serviceRevisionList) {
                    grade += sr.getGrade();
                    count++;

                }
                grade = grade / count;
                sellerRevision.getSeller().setGrade(grade);
            }
            else{
                sellerRevision.getSeller().setGrade(Float.valueOf(sellerRevision.getGrade()));
            }


            sellerRepository.save(sellerRevision.getSeller());
            sellerRevision.setStatus(1);
            emailSender.sendEmail(sellerRevision.getSeller().getEmail(), buildEmail("", "", "REV", sellerRevision.getText()),
                    "REV");
        }



        return "Revision approved";

    }

    @Transactional
    public String reject(Long id){
        Optional<Revision> revision = revisionRepository.findById(id);
        revision.get().setStatus(0);
        revisionRepository.save(revision.get());
        return "Revision not approved";


    }

    @Transactional
    public List<RevisionDTO> getSerRevisions(Long serviceId){
        List<ServiceRevision> revisions =
                serviceRevisionRepository.findAll().stream().filter(r -> r.getService().getId() == serviceId && r.getStatus() == 1).collect(Collectors.toList());
        List<RevisionDTO> dtos = new ArrayList<>();
        for(ServiceRevision serviceRevision : revisions){
            dtos.add(new RevisionDTO(serviceRevision.getId(), serviceRevision.getGrade(), serviceRevision.getText(),
                    "SERVICE",serviceId));

        }
        return dtos;
    }

    @Transactional
    public List<RevisionDTO> getSelRevisions(Long serviceId){
        Optional<com.isa.services.Service> service = serviceRepository.findById(serviceId);
        List<SellerRevision> revisions =
                sellerRevisionRepository.findAll().stream().filter(r -> r.getSeller().getId() == service.get().getSeller().getId() && r.getStatus() == 1).collect(Collectors.toList());
        List<RevisionDTO> dtos = new ArrayList<>();
        for(SellerRevision serviceRevision : revisions){
            dtos.add(new RevisionDTO(serviceRevision.getId(), serviceRevision.getGrade(), serviceRevision.getText(),
                    "SELLER", serviceId));

        }
        return dtos;
    }
}
