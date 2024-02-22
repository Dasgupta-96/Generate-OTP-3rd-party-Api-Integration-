package com.OtpExample.OtpExample.Repository;

import com.OtpExample.OtpExample.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User,Integer> {

  User findByEmail(String email);
}
