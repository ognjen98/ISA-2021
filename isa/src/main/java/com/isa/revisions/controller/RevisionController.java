package com.isa.revisions.controller;

import com.isa.revisions.Revision;
import com.isa.revisions.dto.RevisionDTO;
import com.isa.revisions.service.RevisionService;
import com.isa.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/revision")
public class RevisionController {

    @Autowired
    RevisionService revisionService;

    @Autowired
    TokenUtils tokenUtils;

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @PostMapping("/saveRevision")
    public ResponseEntity saveRevision(@RequestBody RevisionDTO dto, HttpServletRequest request){
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity(revisionService.saveRevision(dto, email), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping("/approve/{id}")
    public ResponseEntity approve(@PathVariable Long id){
        return new ResponseEntity(revisionService.approve(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping("/reject/{id}")
    public ResponseEntity reject(@PathVariable Long id){
        return new ResponseEntity(revisionService.reject(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    @GetMapping("/getRevisions")
    public ResponseEntity getRevisions(){
        return new ResponseEntity(revisionService.getPendingRevisions(), HttpStatus.OK);
    }

    @GetMapping("/getSerRevisions/{id}")
    public ResponseEntity<List<RevisionDTO>> getSerRevisions(@PathVariable Long id){
        return new ResponseEntity<>(revisionService.getSerRevisions(id), HttpStatus.OK);
    }

    @GetMapping("/getSelRevisions/{id}")
    public ResponseEntity<List<RevisionDTO>> getSelRevisions(@PathVariable Long id){
        return new ResponseEntity<>(revisionService.getSelRevisions(id), HttpStatus.OK);
    }
}
