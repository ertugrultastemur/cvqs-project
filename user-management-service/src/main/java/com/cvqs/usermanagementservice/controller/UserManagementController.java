package com.cvqs.usermanagementservice.controller;

import com.cvqs.usermanagementservice.dto.CreateTokenRequestDto;
import com.cvqs.usermanagementservice.dto.CreateUserRequestDto;
import com.cvqs.usermanagementservice.dto.RoleDto;
import com.cvqs.usermanagementservice.dto.UserDto;
import com.cvqs.usermanagementservice.service.UserManagementService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for user management endpoints.
 */
@Validated
@RestController
@RequestMapping("/v1/user")
public class UserManagementController {

    private final UserManagementService userManagementService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserManagementController(UserManagementService userManagementService) {
        logger.info("UserManagementController: ctor entered.");
        this.userManagementService = userManagementService;
    }

    /**
     * Retrieves all users.
     *
     * @return The list of all users.
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAll() {
        logger.info("UserManagerController: getAll endpoint entered.");
        return ResponseEntity.ok(userManagementService.getAllUsers());
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        logger.info("UserManagerController: getUserById endpoint entered" + id);
        return ResponseEntity.ok(userManagementService.findById(id));
    }

    /**
     * Retrieves a user by username.
     *
     * @param userName The username of the user to retrieve.
     * @return The user with the specified username.
     */
    @GetMapping("/getByUsername/{userName}")
    public ResponseEntity<UserDto> getUserByUserName(@PathVariable String userName) {
        logger.info("UserManagerController: getUserByUserName endpoint entered: " + userName);
        return ResponseEntity.ok(userManagementService.findByUserName(userName));
    }

    /**
     * Adds a new user.
     *
     * @param user The user to add.
     * @return The added user.
     */
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> add(@RequestBody CreateUserRequestDto user) {
        logger.info("UserManagerController: add endpoint entered.");
        return ResponseEntity.ok(userManagementService.add(user));
    }

    /**
     * Adds a role to a user.
     *
     * @param roleDto The role to add.
     * @return The response entity.
     */
    @PostMapping("/addRole")
    public ResponseEntity<Void> addRole(@RequestBody RoleDto roleDto) {
        logger.info("UserManagerController: addRole endpoint entered.");
        userManagementService.addRole(roleDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Adds a token to a user.
     *
     * @param createTokenRequestDto The token to add.
     * @return The response entity.
     */
    @PostMapping("/addToken")
    public ResponseEntity<Void> addToken(@RequestBody CreateTokenRequestDto createTokenRequestDto) {
        logger.info("UserManagerController: addToken endpoint entered.");
        userManagementService.addToken(createTokenRequestDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes a role from a user.
     *
     * @param deleteRoleRequestDto The role to delete.
     * @return The response entity.
     */
    @DeleteMapping("/deleteRole")
    public ResponseEntity<Void> deleteRole(@RequestBody RoleDto deleteRoleRequestDto) {
        logger.info("UserManagerController: deleteRole endpoint entered.");
        userManagementService.deleteRole(deleteRoleRequestDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to delete.
     * @return The response entity.
     */
    @PutMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        logger.info("UserManagerController: delete endpoint entered.");
        userManagementService.delete(id);
        return ResponseEntity.ok().build();
    }

}
