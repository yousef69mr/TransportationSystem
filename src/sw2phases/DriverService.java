package com.example.demo.Users;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.swing.text.html.StyleSheet;
import java.util.Arrays;
import java.util.List;
@Service
public class DriverService {
    public static List<Driver> getDriver(){
        String ts="Iam the driver";
        TransportationSystem System=new TransportationSystem(ts) ;
        return Arrays.asList(new Driver("gggg", "01002337333", "aa@ff.com", "gggg","20190567","999999999",System));

    }



}
