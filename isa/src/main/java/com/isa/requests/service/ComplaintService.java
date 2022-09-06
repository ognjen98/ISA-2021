package com.isa.requests.service;

import com.isa.requests.Complaint;
import com.isa.requests.SellerComplaint;
import com.isa.requests.ServiceComplaint;
import com.isa.requests.dto.ComplaintDTO;
import com.isa.requests.repository.ComplaintRepository;
import com.isa.requests.repository.SellerComplaintRepository;
import com.isa.requests.repository.ServiceComplaintRepository;
import com.isa.revisions.Revision;
import com.isa.revisions.SellerRevision;
import com.isa.revisions.ServiceRevision;
import com.isa.revisions.dto.RevisionDTO;
import com.isa.services.repository.ServiceRepository;
import com.isa.users.Client;
import com.isa.users.repository.ClientRepository;
import com.isa.users.service.email.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.isa.users.service.ClientService.buildEmail;

@Service
public class ComplaintService {

    @Autowired
    ComplaintRepository complaintRepository;

    @Autowired
    ServiceComplaintRepository serviceComplaintRepository;

    @Autowired
    SellerComplaintRepository sellerComplaintRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    EmailSender emailSender;

    @Transactional
    public String saveComplaint(ComplaintDTO dto, String email) {
        Client client = clientRepository.findByEmail(email);
        com.isa.services.Service service = serviceRepository.getServiceById(dto.getServiceId());
        ServiceComplaint serRev = serviceComplaintRepository.getServiceComplaintByClientAndService(client, service);
        SellerComplaint selRev = sellerComplaintRepository.getSellerComplaintByClientAndSeller(client,service.getSeller());


        if (dto.getType().equals("SELLER")) {

            SellerComplaint revision = new SellerComplaint(client, dto.getText(), 2, service.getSeller());
            complaintRepository.save(revision);
            return "Seller complaint saved successfully";
        } else if (dto.getType().equals("SERVICE")) {

            ServiceComplaint serviceRevision = new ServiceComplaint(client, dto.getText(), 2, service);
            complaintRepository.save(serviceRevision);
            return "Service complaint saved successfully";
        }

        return "There was a problem";
    }

    @Transactional
    public List<Complaint> getPendingComplaints(){
        List<Complaint> revisions =
                complaintRepository.findAll().stream().filter(r -> r.getStatus() == 2).collect(Collectors.toList());

        return revisions;
    }

    @Transactional
    public String respond(Long id, String response){
        Optional<Complaint> complaint = complaintRepository.findById(id);
        if(complaint.get() instanceof ServiceComplaint) {
            ServiceComplaint serviceComplaint = (ServiceComplaint) complaint.get();
            serviceComplaint.setStatus(1);
            serviceComplaintRepository.save(serviceComplaint);
            emailSender.sendEmail(serviceComplaint.getService().getSeller().getEmail(), buildEmail("", "", "COM",
                    response), "COM");
            emailSender.sendEmail(serviceComplaint.getClient().getEmail(), buildEmail("", "", "COM",
                    response), "COM");
            return  "Complaint approved";
        }
        else if(complaint.get() instanceof SellerComplaint) {
            SellerComplaint sellerComplaint = (SellerComplaint) complaint.get();
            sellerComplaint.setStatus(1);
            sellerComplaintRepository.save(sellerComplaint);
            emailSender.sendEmail(sellerComplaint.getSeller().getEmail(), buildEmail("", "", "COM",
                    response), "COM");
            emailSender.sendEmail(sellerComplaint.getClient().getEmail(), buildEmail("", "", "COM",
                    response), "COM");
            return "Complaint approved";
        }

        return "Complaint not approved";


    }


}
