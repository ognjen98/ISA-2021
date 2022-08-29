package com.isa.revisions.controller;

import com.isa.revisions.Revision;
import com.isa.revisions.dto.RevisionDTO;
import com.isa.revisions.service.RevisionService;
import com.isa.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/revision")
public class RevisionController {

    @Autowired
    RevisionService revisionService;

    @Autowired
    TokenUtils tokenUtils;

    @PostMapping("/saveRevision")
    public ResponseEntity saveRevision(@RequestBody RevisionDTO dto, HttpServletRequest request){
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity(revisionService.saveRevision(dto, email), HttpStatus.OK);
    }

    @GetMapping("/approve/{id}")
    public ResponseEntity approve(@PathVariable Long id){
        return new ResponseEntity(revisionService.approve(id), HttpStatus.OK);
    }

    @GetMapping("/reject/{id}")
    public ResponseEntity reject(@PathVariable Long id){
        return new ResponseEntity(revisionService.reject(id), HttpStatus.OK);
    }

    @GetMapping("/getRevisions")
    public ResponseEntity getRevisions(){
        return new ResponseEntity(revisionService.getPendingRevisions(), HttpStatus.OK);
    }
}
