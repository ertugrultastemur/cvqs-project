package com.cvqs.mistakeservice.service;

import com.cvqs.mistakeservice.dto.MistakeDto;
import com.cvqs.mistakeservice.mapper.ModelMapperService;
import com.cvqs.mistakeservice.model.Mistake;
import com.cvqs.mistakeservice.repository.MistakeRepository;
import com.cvqs.mistakeservice.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MistakeServiceTest {

   /* MistakeService mistakeService;

    private MistakeRepository mistakeRepository;

    private VehicleRepository vehicleRepository;

    private ModelMapperService modelMapperService;


    @BeforeEach
    void setUp() {
        mistakeRepository = Mockito.mock(MistakeRepository.class);
        vehicleRepository = Mockito.mock(VehicleRepository.class);
        modelMapperService = Mockito.mock(ModelMapperService.class);

        mistakeService = new MistakeService(mistakeRepository,vehicleRepository,modelMapperService);
    }

    @Test
    void findById() {
        int mistakeId = 1;

        Mockito.when(mistakeRepository.findById(mistakeId));

        MistakeDto result = mistakeService.findById(mistakeId);

        assertNotNull(result);
        assertEquals(mistakeId, result.getId().getMistakeId());


    }

    @Test
    void findByName() {
    }

    @Test
    void getAllMistakes() {
    }*/
}