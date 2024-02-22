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

}
