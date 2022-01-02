package com.example.demo.Users;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class AdminService {
    public static List<Administrator> getAdmin(){
        String A="Iam the admin";
        TransportationSystem System=new TransportationSystem(A);
        return Arrays.asList(new Administrator(System));

    }



}
