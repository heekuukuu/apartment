package com.example.apartment.service;


import com.example.apartment.model.LoginType;
import com.example.apartment.model.User;
import com.example.apartment.model.UserRole;
import com.example.apartment.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encode;

    @Transactional
    public void signUp(User user) {
        String rawPassword = user.getPassword();
        String encodePassword = encode.encode(rawPassword);
        user.setPassword(encodePassword);
        user.setLoginType(LoginType.GENERAL);
        user.setRole(UserRole.USER);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUser(String username) {
        User user = userRepository.findByUsername(username).orElseGet(User::new);
        return user;
    }


    @Transactional
    public void updateUserInfo(User user) {
        long id = user.getId();
        User currUser = userRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException(("failed to load User Info : cannot find User id")));


        //Validates
        if (currUser.getLoginType() == LoginType.GENERAL) {
            String rawPassword = user.getPassword();
            String encodePassword = encode.encode(rawPassword);
            currUser.setPassword(encodePassword);
            currUser.setEmail(user.getEmail());
        }

    }
}