package com.cvqs.usermanagementservice.service;

import com.cvqs.usermanagementservice.dto.CreateTokenRequestDto;
import com.cvqs.usermanagementservice.dto.CreateUserRequestDto;
import com.cvqs.usermanagementservice.dto.RoleDto;
import com.cvqs.usermanagementservice.dto.UserDto;
import com.cvqs.usermanagementservice.exception.UserNotFoundException;
import com.cvqs.usermanagementservice.mapper.ModelMapperService;
import com.cvqs.usermanagementservice.model.Role;
import com.cvqs.usermanagementservice.model.User;
import com.cvqs.usermanagementservice.model.token.Token;
import com.cvqs.usermanagementservice.repository.UserManagementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

/**
 * The UserManagementService class provides methods to manage user-related operations.
 */
@Service
public class UserManagementService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserManagementRepository userManagementRepository;
    private final ModelMapperService modelMapperService;

    public UserManagementService(UserManagementRepository userManagementRepository, ModelMapperService modelMapperService) {
        logger.info("UserManagementService: Entered constructor.");
        this.userManagementRepository = userManagementRepository;
        this.modelMapperService = modelMapperService;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a list of UserDto objects representing all users
     */
    public List<UserDto> getAllUsers() {
        logger.info("UserManagementService: Entered the getAllUsers method.");
        return userManagementRepository.findAll().stream().map(UserDto::convert).collect(Collectors.toList());
    }

    /**
     * Finds a user by their ID.
     *
     * @param id the ID of the user
     * @return the UserDto object representing the found user
     * @throws UserNotFoundException if the user is not found
     */
    public UserDto findById(Integer id) {
        logger.info("UserManagementService: Entered the findById method.");
        return userManagementRepository.findById(id).map(UserDto::convert).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
    }

    /**
     * Finds a user by their username.
     *
     * @param userName the username of the user
     * @return the UserDto object representing the found user
     * @throws UserNotFoundException if the user is not found
     */
    public UserDto findByUserName(String userName) {
        logger.info("UserManagementService: Entered the findByUserName method.");
        return userManagementRepository.findByUsername(userName).map(UserDto::convert).orElseThrow(() -> new UserNotFoundException("User not found with username: " + userName));
    }

    /**
     * Adds a new user.
     *
     * @param createUserRequestDto the CreateUserRequestDto object containing the user information
     * @return the UserDto object representing the added user
     */
    public UserDto add(CreateUserRequestDto createUserRequestDto) {
        logger.info("UserManagementService: Entered the add method.");
        User user = this.modelMapperService.forRequest().map(createUserRequestDto, User.class);
        logger.debug("UserManagementService: User successfully created.");
        return this.modelMapperService.forRequest().map(userManagementRepository.save(user), UserDto.class);
    }

    /**
     * Adds a role to a user.
     *
     * @param roleDto the RoleDto object containing the role and username information
     * @throws UserNotFoundException if the user is not found
     * @throws IllegalArgumentException if the user already has the same role
     */
    public void addRole(RoleDto roleDto) {
        logger.info("UserManagementService: Entered the addRole method.");
        User user = userManagementRepository.findByUsername(roleDto.getUsername()).orElseThrow(() -> new UserNotFoundException("User not found with username: " + roleDto.getUsername()));
        List<Role> roles = user.getRoles();
        if (roles.stream().anyMatch(role -> role.equals(roleDto.getRole()))) {
            logger.error("UserManagementService: Role could not be added.");
            throw new IllegalArgumentException("A user cannot have more than one role of the same type.");
        }
        roles.add(roleDto.getRole());
        user.setRoles(roles);
        userManagementRepository.save(user);
        logger.debug("UserManagementService: Role successfully added.");
    }

    /**
     * Adds a token to a user.
     *
     * @param createTokenRequestDto the CreateTokenRequestDto object containing the token and username information
     * @throws UserNotFoundException if the user is not found
     */
    public void addToken(CreateTokenRequestDto createTokenRequestDto) {
        logger.info("UserManagementService: Entered the addToken method.");
        User user = userManagementRepository.findByUsername(createTokenRequestDto.getUsername()).orElseThrow(() -> new UserNotFoundException("User not found with username: " + createTokenRequestDto.getUsername()));
        List<Token> tokens = user.getTokens();
        tokens.add(createTokenRequestDto.getToken());
        user.setTokens(tokens);
        userManagementRepository.save(user);
        logger.debug("UserManagementService: Token successfully added.");
    }

    /**
     * Deletes a role from a user.
     *
     * @param deleteRoleRequestDto the RoleDto object containing the role and username information
     * @throws UserNotFoundException if the user is not found
     * @throws IllegalArgumentException if the user has only one role
     */
    public void deleteRole(RoleDto deleteRoleRequestDto) {
        logger.info("UserManagementService: Entered the deleteRole method.");
        User user = userManagementRepository.findByUsername(deleteRoleRequestDto.getUsername()).orElseThrow(() -> new UserNotFoundException("User not found with username: " + deleteRoleRequestDto.getUsername()));
        List<Role> roles = user.getRoles();
        if (roles.size() > 1) {
            roles.remove(deleteRoleRequestDto.getRole());
            user.setRoles(roles);
            userManagementRepository.save(user);
            logger.debug("UserManagementService: Role successfully deleted.");
        } else {
            logger.error("UserManagementService: Role deletion failed.");
            throw new IllegalArgumentException("Invalid number of roles. The user must have at least one role.");
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete
     * @throws UserNotFoundException if the user is not found
     */
    public void delete(int id) {
        logger.info("UserManagementService: Entered the delete method.");
        User user = userManagementRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        user.setDeleted(true);
        userManagementRepository.save(user);
        logger.debug("UserManagementService: User successfully deleted.");
    }
}
