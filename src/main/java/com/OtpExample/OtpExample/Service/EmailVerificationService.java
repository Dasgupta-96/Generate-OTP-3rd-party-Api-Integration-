package com.OtpExample.OtpExample.Service;

import com.OtpExample.OtpExample.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationService {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    static final Map<String, String> emailOtpMapping = new HashMap<>();

    public Map<String, String> verifyOTP(String email, String otp) {

        String storeOTP = emailOtpMapping.get(email);
        Map<String, String> response = new HashMap<>();
        if (storeOTP != null && storeOTP.equals(otp)) {

            User user = userService.getUserByEmail(email);

            if (user != null) {

                userService.verifyEmail(user);
                response.put("status", "sucess");
                response.put("message", "Email verified sucessfully");
            } else {

                response.put("status", "error");
                response.put("message", "User not found");
            }

        } else {

            response.put("status", "error");
            response.put("message", "Invalid otp");

        }
        return response;
    }

    public Map<String, String> sendOtpForLogin(String email) {

        if (userService.isEmailVerified(email)) {

            String otp = emailService.generateOtp();
            emailOtpMapping.put(email, otp);

            // send Otp to user's email

            emailService.sendOtp(email);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "OTP sent successfully");
            return response;
        } else {
            Map<String, String> response = new HashMap<>();

            response.put("status", "error");
            response.put("message", "Email is not verified");

            return response;
        }

    }

    public Map<String, String> verifyOtpForLogin(String email, String otp) {

        String storedOtp = emailOtpMapping.get(email);

        Map<String, String> response = new HashMap<>();

        if (storedOtp != null && storedOtp.equals(otp)) {

            response.put("status", "success");
            response.put("message", "OTP verified successfully");
        } else {

            response.put("status", "error");
            response.put("message", "invalid otp");
        }
        return response;
    }
}
