package com.capstone.CapstoneServer.service;

import com.capstone.CapstoneServer.dto.UserDto;
import com.capstone.CapstoneServer.entities.User;
import com.capstone.CapstoneServer.entities.RepoUser;
import com.capstone.CapstoneServer.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private RepoUser userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetUserId() throws Exception {
        // Mock UserRepository
        User mockUser = User.builder()
                            .userId(1)
                            .userName("testUser")
                            .email("test@example.com")
                            .password("password")
                            .build();

        when(userRepository.findByUserName("testUser")).thenReturn(mockUser);

        // Test getUserId method
        Integer userId = userService.getUserId("testUser", "password");
        assertNotNull(userId);
        assertEquals(1, userId);

        // Verify that the userRepository.findByUserName method was called with the correct arguments
        verify(userRepository, times(1)).findByUserName("testUser");
    }

    @Test
    void testGetAllUsers() {
        // Mock UserRepository
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder()
                        .userName("user1")
                        .email("user1@example.com")
                        .password("password1")
                        .build());
        mockUsers.add(User.builder()
                        .userName("user2")
                        .email("user2@example.com")
                        .password("password2")
                        .build());

        when(userRepository.findAll()).thenReturn(mockUsers);

        // Test getAllUsers method
        List<UserDto> userDtos = userService.getAllUsers();
        assertNotNull(userDtos);
        assertEquals(2, userDtos.size());

        // Verify that the userRepository.findAll method was called
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testCreateUser() throws Exception {
        // Mock UserRepository
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(userRepository.findByUserName(anyString())).thenReturn(null);

        // Test createUser method
        UserDto newUserDto = new UserDto("newUser", "newuser@example.com", "newpassword");
        Integer userId = userService.createUser(newUserDto);
        assertNotNull(userId);

        // Verify that userRepository.save method was called with the correct user object
        verify(userRepository, times(1)).save(any(User.class));
    }
}
