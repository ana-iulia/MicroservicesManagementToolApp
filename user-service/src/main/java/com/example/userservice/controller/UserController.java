package com.example.userservice.controller;

import com.example.userservice.domain.LoginRequestDTO;
import com.example.userservice.domain.NotificationEventDTO;
import com.example.userservice.domain.UserDTO;
import com.example.userservice.domain.UserRegisterDTO;
import com.example.userservice.service.IUserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/management")
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAll() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/user")
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserRegisterDTO userDTO) {
        return ResponseEntity.ok().body(userService.saveRegularUser(userDTO));
    }

    @CrossOrigin
    @PostMapping("/user/login")
    public ResponseEntity<Integer> login(@RequestBody @Valid LoginRequestDTO loginRequest) throws Exception {
        return ResponseEntity.ok().body(userService.login(loginRequest));
    }

    @GetMapping("/user/projects/{userEmail}")
    public ResponseEntity<List<String>> getAllProjectNames(@PathVariable("userEmail") String userEmail) {
        return new ResponseEntity<>(userService.getAllAssignments(userEmail), HttpStatus.OK);
    }

    @GetMapping("/user/unread/notifications/{userEmail}")
    public ResponseEntity<List<NotificationEventDTO>> getAllUserNotifications(@PathVariable("userEmail") String userEmail) {
        return new ResponseEntity<>(userService.getUserNotifications(userEmail), HttpStatus.OK);
    }

    @PutMapping("/user/read/notifications/{userEmail}")
    public ResponseEntity<Boolean> readAllUserNotifications(@PathVariable("userEmail") String userEmail) {
        return new ResponseEntity<>(userService.readUserNotifications(userEmail), HttpStatus.OK);
    }



}