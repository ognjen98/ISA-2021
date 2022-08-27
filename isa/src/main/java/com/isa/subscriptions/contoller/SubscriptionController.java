package com.isa.subscriptions.contoller;

import com.isa.security.TokenUtils;
import com.isa.subscriptions.dto.ResActionDTO;
import com.isa.subscriptions.dto.SubscriptionDTO;
import com.isa.subscriptions.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    TokenUtils tokenUtils;

    @GetMapping("/addSubscription/{serviceId}")
    public ResponseEntity addSub(@PathVariable Long serviceId, HttpServletRequest request){
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity(subscriptionService.createSubscription(serviceId, email), HttpStatus.OK);
    }

    @GetMapping("/getSubbedServices")
    public ResponseEntity<List<SubscriptionDTO>> getSubbedServices(HttpServletRequest request){
        String email = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        return new ResponseEntity(subscriptionService.subbedServices(email), HttpStatus.OK);
    }

    @GetMapping("/cancelSub/{id}")
    public ResponseEntity cancelSub(@PathVariable Long id){
        return new ResponseEntity(subscriptionService.cancelSubscription(id), HttpStatus.OK);
    }

    @PostMapping("/addResAction")
    public ResponseEntity addResAction(@RequestBody ResActionDTO dto){
        return new ResponseEntity(subscriptionService.addResAction(dto), HttpStatus.OK);
    }
}
