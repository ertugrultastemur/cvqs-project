package com.cvqs.mistakeservice.service;

import com.cvqs.mistakeservice.dto.MistakeDto;
import com.cvqs.mistakeservice.repository.MistakeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MistakeService {

    private final MistakeRepository mistakeRepository;


    public MistakeService(MistakeRepository mistakeRepository){
        this.mistakeRepository = mistakeRepository;
    }

}
