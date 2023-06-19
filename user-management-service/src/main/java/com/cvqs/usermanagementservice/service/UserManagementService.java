package com.cvqs.usermanagementservice.service;

import com.cvqs.usermanagementservice.dto.CreateUserRequestDto;
import com.cvqs.usermanagementservice.dto.UserDto;
import com.cvqs.usermanagementservice.dto.UserIdDto;
import com.cvqs.usermanagementservice.exception.UserNotFoundException;
import com.cvqs.usermanagementservice.model.User;
import com.cvqs.usermanagementservice.repository.UserManagementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserManagementService {

    private final UserManagementRepository userManagementRepository;

    public UserManagementService(UserManagementRepository userManagementRepository){
        this.userManagementRepository = userManagementRepository;
    }

    public List<UserDto> getAllUsers(){
        return userManagementRepository.findAll().stream().map(UserDto::convert).collect(Collectors.toList());
    }

    public UserDto findById(Integer id){
        return userManagementRepository.findById(id).map(UserDto::convert).orElseThrow(() -> new UserNotFoundException("User could not fount by id: " + id));
    }

    public UserIdDto findByUserName(String userName){
        return userManagementRepository.findByUserName(userName).map(user -> new UserIdDto(user.getUserId(), user.getUserName())).orElseThrow(() -> new UserNotFoundException("User could not found by User Name: " +userName));
    }

    public void add(CreateUserRequestDto createUserRequestDto){
        User user = userManagementRepository.findById(createUserRequestDto.getUserId()).orElseThrow(() -> new UserNotFoundException("User could not found: " + createUserRequestDto.getUserName()));
        userManagementRepository.save(user);
    }

    public void delete(UserIdDto userIdDto){
        User user = userManagementRepository.findByUserName(userIdDto.getUserName()).orElseThrow(() -> new UserNotFoundException("User could not found: " + userIdDto.getUserName()));
        user.setDeleted(true);
        userManagementRepository.save(user);
    }
}
