package com.isa.users.service;

import com.isa.users.Client;
import com.isa.users.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulingService {

    @Autowired
    ClientRepository clientRepository;

    @Scheduled(cron="0 47 22 6 1/1 *")
    public void resetPenalties(){
        List<Client> clients = clientRepository.findAll();
        for(Client client : clients){
            client.setPenalties(0);
            clientRepository.save(client);
        }
    }
}
