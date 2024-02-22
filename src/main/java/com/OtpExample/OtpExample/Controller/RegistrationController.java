package com.OtpExample.OtpExample.Controller;

import com.OtpExample.OtpExample.Entity.User;
import com.OtpExample.OtpExample.Service.EmailService;
import com.OtpExample.OtpExample.Service.EmailVerificationService;
import com.OtpExample.OtpExample.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
private EmailVerificationService emailVerificationService;
    @PostMapping("/register")
    // http://localhost:8080/api/register
    public Map<String ,String> registerUser(@RequestBody User user){

        User reg = userService.registerUser(user);

        emailService.sendOtp(user.getEmail());

        Map<String ,String> response = new HashMap<>();
        response.put("status","success");
        response.put("message","User registered successfully. Check your email for verification.");
        return response;

    }

    public Map<String,String> verifyOtp(@RequestParam String email, @RequestParam String otp){

        return emailVerificationService.verifyOTP(email,otp);
    }




}
