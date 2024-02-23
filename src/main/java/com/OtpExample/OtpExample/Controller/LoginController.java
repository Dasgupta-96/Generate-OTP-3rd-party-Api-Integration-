package com.OtpExample.OtpExample.Controller;

import com.OtpExample.OtpExample.Service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@RequestMapping("/api")
public class LoginController {

    @Autowired
    private EmailVerificationService emailVerificationService;
    @PostMapping("/send-otp-for-login")
    // http://localhost:8080/api/send-otp-for-login?email="mike@gmail.com"
    public Map<String,String> sendOtpForLogin(@RequestBody String email){

        return emailVerificationService.sendOtpForLogin(email);

    }
    @PostMapping("/verify-otp-for-login")

    public Map<String,String> sendOtpForLogin(@RequestParam String email, @RequestParam String otp){

        return emailVerificationService.verifyOtpForLogin(email,otp);

    }
}
