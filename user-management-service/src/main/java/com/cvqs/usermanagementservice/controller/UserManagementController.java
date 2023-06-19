package com.cvqs.usermanagementservice.controller;

import com.cvqs.usermanagementservice.dto.CreateUserRequestDto;
import com.cvqs.usermanagementservice.dto.UserDto;
import com.cvqs.usermanagementservice.dto.UserIdDto;
import com.cvqs.usermanagementservice.service.UserManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserManagementController {

    private final UserManagementService userManagementService;

    public UserManagementController(UserManagementService userManagementService){
        this.userManagementService = userManagementService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(){
        return ResponseEntity.ok(userManagementService.getAllUsers());
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id){
        return ResponseEntity.ok(userManagementService.findById(id));
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<UserIdDto> getUserByUserName(@PathVariable String userName){
        return ResponseEntity.ok(userManagementService.findByUserName(userName));
    }

    @PostMapping("/user/{user}")
    public ResponseEntity<Void> add(@RequestBody CreateUserRequestDto user){
        userManagementService.add(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/{userName}")
    public ResponseEntity<Void> delete(@RequestBody UserIdDto userName){
        userManagementService.delete(userName);
        return ResponseEntity.ok().build();
    }
}
