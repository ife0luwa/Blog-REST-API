package com.example.myblogtask.services.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.myblogtask.models.UserDetails;
import com.example.myblogtask.repositories.PostRepository;
import com.example.myblogtask.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testRegisterUser() {
        UserDetails userDetails = new UserDetails();
        userDetails.setActive("Active");
        userDetails.setEmail("jane.doe@example.org");
        userDetails.setFriends(new ArrayList<>());
        userDetails.setGender("Gender");
        userDetails.setId(123L);
        userDetails.setName("Name");
        userDetails.setPassword("iloveyou");
        when(this.userRepository.findByEmail((String) any())).thenReturn(userDetails);

        UserDetails userDetails1 = new UserDetails();
        userDetails1.setActive("Active");
        userDetails1.setEmail("jane.doe@example.org");
        userDetails1.setFriends(new ArrayList<>());
        userDetails1.setGender("Gender");
        userDetails1.setId(123L);
        userDetails1.setName("Name");
        userDetails1.setPassword("iloveyou");
        assertFalse(this.userServiceImpl.registerUser(userDetails1));
        verify(this.userRepository).findByEmail((String) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testLoginUser() {
        UserDetails userDetails = new UserDetails();
        userDetails.setActive("Active");
        userDetails.setEmail("jane.doe@example.org");
        userDetails.setFriends(new ArrayList<>());
        userDetails.setGender("Gender");
        userDetails.setId(123L);
        userDetails.setName("Name");
        userDetails.setPassword("iloveyou");
        when(this.userRepository.findByEmailAndPassword((String) any(), (String) any())).thenReturn(userDetails);
        assertSame(userDetails, this.userServiceImpl.loginUser("jane.doe@example.org", "iloveyou"));
        verify(this.userRepository).findByEmailAndPassword((String) any(), (String) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testGetUserById() {
        UserDetails userDetails = new UserDetails();
        userDetails.setActive("Active");
        userDetails.setEmail("jane.doe@example.org");
        userDetails.setFriends(new ArrayList<>());
        userDetails.setGender("Gender");
        userDetails.setId(123L);
        userDetails.setName("Name");
        userDetails.setPassword("iloveyou");
        Optional<UserDetails> ofResult = Optional.of(userDetails);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(userDetails, this.userServiceImpl.getUserById(123L));
        verify(this.userRepository).findById((Long) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testGetAllUsers() {
        ArrayList<UserDetails> userDetailsList = new ArrayList<>();
        when(this.userRepository.findAll()).thenReturn(userDetailsList);
        List<UserDetails> actualAllUsers = this.userServiceImpl.getAllUsers();
        assertSame(userDetailsList, actualAllUsers);
        assertTrue(actualAllUsers.isEmpty());
        verify(this.userRepository).findAll();
    }

    @Test
    void testDeactivateUser() {
        doNothing().when(this.userRepository).deleteById((Long) any());
        this.userServiceImpl.deactivateUser(123L);
        verify(this.userRepository).deleteById((Long) any());
        assertTrue(this.userServiceImpl.getAllUsers().isEmpty());
    }

    @Test
    void testDeactivateUser2() {
        // TODO: This test is incomplete.
        //   Reason: R004 No meaningful assertions found.
        //   Diffblue Cover was unable to create an assertion.
        //   Make sure that fields modified by deactivateUser(Long)
        //   have package-private, protected, or public getters.
        //   See https://diff.blue/R004 to resolve this issue.

        doNothing().when(this.userRepository).deleteById((Long) any());
        this.userServiceImpl.deactivateUser(null);
    }
}

