package com.noweldecor.sapins.controller;

import com.noweldecor.sapins.entity.Client;
import com.noweldecor.sapins.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/post")
    public Client postClient(@RequestParam String nom, @RequestParam String prenom) {
        Client client = Client.builder()
                .nom(nom)
                .prenom(prenom)
                .sponsor("Paul Claverie")
                .pointsFidelite(0)
                .build();
        return clientRepository.save(client);
    }

    @GetMapping("/get")
    public Client getClient(@RequestParam Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @GetMapping("/getAll")
    public List<Client> getAllClients() {
        return (List<Client>) clientRepository.findAll();
    }
}
