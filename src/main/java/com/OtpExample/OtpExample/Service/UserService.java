package com.OtpExample.OtpExample.Service;

import com.OtpExample.OtpExample.Entity.User;
import com.OtpExample.OtpExample.Repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRespository userRespository;
    public User registerUser(User user){

        return userRespository.save(user);
    }

    public User getUserByEmail(String email) {

        User user = userRespository.findByEmail(email);

        return user;
    }

    public void verifyEmail(User user) {

        user.setEmailVerified(true);
        userRespository.save(user);
    }

    public boolean isEmailVerified(String email) {

        User user = userRespository.findByEmail(email);
        return user!=null && user.isEmailVerified();
    }
}
