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

@Service
public class UserManagementService {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserManagementRepository userManagementRepository;

    private final ModelMapperService modelMapperService;

    public UserManagementService(UserManagementRepository userManagementRepository, ModelMapperService modelMapperService){
        logger.info("UserManagementService: entered constructor.");
        this.userManagementRepository = userManagementRepository;
        this.modelMapperService = modelMapperService;
    }

    public List<UserDto> getAllUsers(){
        logger.info("UserManagementService: Entered the getAll method.");
        return userManagementRepository.findAll().stream().map(UserDto::convert).collect(Collectors.toList());
    }

    public UserDto findById(Integer id){
        logger.info("UserManagementService: Entered the findById method.");
        return userManagementRepository.findById(id).map(UserDto::convert).orElseThrow(() -> new UserNotFoundException("User could not fount by id: " + id));
    }

    public UserDto findByUserName(String userName){
        logger.info("UserManagementService: Entered the findByUserName method.");
        return userManagementRepository.findByUsername(userName).map(UserDto::convert).orElseThrow(() -> new UserNotFoundException("User could not found by User Name: " +userName));
    }

    public UserDto add(CreateUserRequestDto createUserRequestDto){
        logger.info("UserManagementService: Entered the add method.");
        /*userManagementRepository.findByUsername(createUserRequestDto.getUsername()).ifPresent(user -> {
            throw new UserNotFoundException("User could not found by username: " + createUserRequestDto.getUsername());
        });*/
        User user = this.modelMapperService.forRequest().map(createUserRequestDto,User.class);

        logger.debug("UserManagementService: User successfully created.");
        return this.modelMapperService.forRequest().map(userManagementRepository.save(user),UserDto.class);
    }

    public void addRole(RoleDto roleDto){
        logger.info("UserManagementService: Entered the addRole method.");
        User user = userManagementRepository.findByUsername(roleDto.getUsername()).orElseThrow(()-> new UserNotFoundException("User could not found by username: " + roleDto.getUsername()));
        List<Role> roles = user.getRoles();
        if (roles.stream().anyMatch(role -> role.equals(roleDto.getRole()))){
            logger.error("UserManagementService: role could not adding" );
            throw new IllegalArgumentException("A user cannot have more than one role of the same role.");
        }
        roles.add(roleDto.getRole());
        user.setRoles(roles);
        userManagementRepository.save(user);
        logger.debug("UserManagementService: Role successfully added.");

    }

    public void addToken(CreateTokenRequestDto createTokenRequestDto){
        logger.info("UserManagementService: Entered the addToken method.");
        User user = userManagementRepository.findByUsername(createTokenRequestDto.getUsername()).orElseThrow(()-> new UserNotFoundException("User could not found by username: " + createTokenRequestDto.getUsername()));
        List<Token> tokens = user.getTokens();
        tokens.add(createTokenRequestDto.getToken());
        user.setTokens(tokens);
        userManagementRepository.save(user);
        logger.debug("UserManagementService: Token successfully added.");

    }

    public void deleteRole(RoleDto deleteRoleRequestDto){
        logger.info("UserManagementService: Entered the deleteRole method.");
        User user = userManagementRepository.findByUsername(deleteRoleRequestDto.getUsername()).orElseThrow(()-> new UserNotFoundException("User could not found by username: " + deleteRoleRequestDto.getUsername()));
        List<Role> roles = user.getRoles();
        if (roles.size()>1) {
            roles.remove(deleteRoleRequestDto.getRole());
            user.setRoles(roles);
            userManagementRepository.save(user);
            logger.debug("UserManagementService: User successfully created.");
        }
        else {
            logger.error("UserManagementService: User creating failed.");
            throw new IllegalArgumentException("Invalid number of roles. Number of roles must be at least 1.");        }
    }

    public void delete(int id){
        logger.info("UserManagementService: Entered the delete method.");
        User user = userManagementRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User could not found by id: " + id));
        user.setDeleted(true);
        userManagementRepository.save(user);
        logger.debug("UserManagementService: User successfully deleted.");
    }

}
