package com.engsoft.marmita.service;

import com.engsoft.marmita.model.LoginRequest;
import com.engsoft.marmita.model.User;
import com.engsoft.marmita.service.jwt.JwtService;
import com.engsoft.marmita.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    public AuthenticationService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String authenticate(LoginRequest authentication) {
        User user = userService.findByUsername(authentication.getUsername());
        String authPass = Utils.hashPassword(authentication.getPassword());
        if(!user.isActive()){
            return null;
        }
        if(user.getPassword().equals(authPass)) {
            return jwtService.generateToken(user);
        }
        return null;
    }
}