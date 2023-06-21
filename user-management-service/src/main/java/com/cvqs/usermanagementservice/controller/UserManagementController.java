package com.cvqs.usermanagementservice.controller;

import com.cvqs.usermanagementservice.dto.CreateUserRequestDto;
import com.cvqs.usermanagementservice.dto.UserDto;
import com.cvqs.usermanagementservice.dto.UserIdDto;
import com.cvqs.usermanagementservice.service.UserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
public class UserManagementController {

    private final UserManagementService userManagementService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserManagementController(UserManagementService userManagementService){
        logger.info("UserManagementController: ctor entered.");
        this.userManagementService = userManagementService;
    }

    @GetMapping("/user/getAll")
    public ResponseEntity<List<UserDto>> getAll(){
        logger.info("UserManagerController: getAll endpoint entered.");
        return ResponseEntity.ok(userManagementService.getAllUsers());
    }

    @GetMapping("/user/getById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id){
        logger.info("UserManagerController: getUserById endpoint entered" + id);
        return ResponseEntity.ok(userManagementService.findById(id));
    }

    @GetMapping("/user/getByUserName/{userName}")
    public ResponseEntity<UserIdDto> getUserByUserName(@PathVariable String userName){
        logger.info("UserManagerController: getUserByUserName endpoint entered: " + userName);
        return ResponseEntity.ok(userManagementService.findByUserName(userName));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> add(@RequestBody CreateUserRequestDto user){
        logger.info("UserManagerController: add endpoint entered.");
        userManagementService.add(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/user/delete/{userName}")
    public ResponseEntity<Void> delete(@RequestBody UserIdDto userName){
        logger.info("UserManagerController: delete endpoint entered.");
        userManagementService.delete(userName);
        return ResponseEntity.ok().build();
    }


}
