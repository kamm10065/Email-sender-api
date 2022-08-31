package com.email.controller;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;
    @RequestMapping("/welcome")
    public String welcome(){
        return "hello this is my email api";
    }

    //api to send email
    @RequestMapping(value = "/sendemail",method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request)
    {

        System.out.println(request);
         boolean res = this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getto());
        System.out.println("result "+res);
         if (res)
        {
            return ResponseEntity.ok(new EmailResponse("Email is sent successfully..."));
        }else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not sent..."));
        }
    }
}
