package com.isa.requests;

import com.isa.requests.dto.ComplaintDTO;
import com.isa.requests.service.ComplaintService;
import com.isa.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @Autowired
    ComplaintService complaintService;

    @Autowired
    TokenUtils tokenUtils;

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping("/makeComplaint")
    public ResponseEntity complaint(@RequestBody ComplaintDTO dto, HttpServletRequest request){
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity(complaintService.saveComplaint(dto, email), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping("/getComplaints")
    public ResponseEntity<List<Complaint>> getComplaints(){
        return new ResponseEntity(complaintService.getPendingComplaints(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping("/response/{id}")
    public ResponseEntity response(@PathVariable Long id, @RequestParam String response){
        return new ResponseEntity(complaintService.respond(id, response), HttpStatus.OK);
    }
}
