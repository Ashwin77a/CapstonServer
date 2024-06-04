package com.capstone.CapstoneServer.services;

import com.capstone.CapstoneServer.dto.UserDto;
import com.capstone.CapstoneServer.entities.User;
import com.capstone.CapstoneServer.entities.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RepoUser userRepository;

    // used to get user id
    public Integer getUserId(String username, String password) throws Exception {
        User user = userRepository.findByUserName(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user.getUserId();
            } else {
                throw new Exception("Unauthorized");
            }
        } else {
            throw new Exception("User not found");
        }
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserDto(user.getUserName(), user.getEmail(), user.getPassword())).collect(Collectors.toList());
    }

    public Integer createUser(UserDto user) throws Exception {
        log.info("create user----------------------called");

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new Exception("Email already exists");
        }
        if (userRepository.findByUserName(user.getUserName()) != null) {
            throw new Exception("Username already exists");
        }
        User userNew = User.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        userRepository.save(userNew);
        return userNew.getUserId();
    }
}
