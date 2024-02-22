package com.OtpExample.OtpExample.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import static com.OtpExample.OtpExample.Service.EmailVerificationService.emailOtpMapping;
@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    private final UserService userService;

    public EmailService(JavaMailSender javaMailSender, UserService userService) {
        this.javaMailSender = javaMailSender;
        this.userService = userService;
    }

    public String generateOtp(){

        return String.format("%04d", new java.util.Random().nextInt(10000));
    }
    public void sendOtp(String email){

        String otp = generateOtp();
        emailOtpMapping.put(email,otp);

        sendEmail(email, "Otp for email verification", "Your OTP is "+otp);


    }
    private void sendEmail(String to, String subject, String text){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mania@gmail.com");
        message.setTo(to);
        message.setText(text);
        message.setSubject(subject);
        javaMailSender.send(message);
    }
}
