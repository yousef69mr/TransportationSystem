package com.example.demo.Users;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/client")
public class clientController {
    @GetMapping
    public List<Client> getClient(){

        BirthDay bd=new BirthDay();
    return Arrays.asList(new Client("gggg", "01002337333", "aa@ff.com", "gggg", bd));

    }


}
