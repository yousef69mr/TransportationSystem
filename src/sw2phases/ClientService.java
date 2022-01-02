package com.example.demo.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/client")
public class clientController {
    private final ClientService clientService;

    @Autowired
    public clientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping
    public List<Client> getClient(){
    return ClientService.getClient();

    }


}
