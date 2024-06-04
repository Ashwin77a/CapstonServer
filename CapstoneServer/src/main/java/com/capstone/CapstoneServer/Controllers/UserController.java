package com.capstone.CapstoneServer.Controllers;

import com.capstone.CapstoneServer.dto.UserDto;
import com.capstone.CapstoneServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Integer> createUser(@RequestBody UserDto userDto) {
        try {
            Integer userId = userService.createUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userId);
        } catch (Exception e) {
            if (e.getMessage().equals("Email already exists")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(3);
            } else if (e.getMessage().equals("Username already exists")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(2);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        }
    }

    @GetMapping
    public ResponseEntity<Integer> getUser(@RequestParam String username, @RequestParam String password) {
        try {
            Integer userId = userService.getUserId(username, password);
            return ResponseEntity.ok(userId);
        } catch (Exception e) {
            if (e.getMessage().equals("Unauthorized")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(401);
            } else if (e.getMessage().equals("User not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(404);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
