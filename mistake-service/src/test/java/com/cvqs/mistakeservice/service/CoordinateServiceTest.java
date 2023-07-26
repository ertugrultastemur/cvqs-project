package com.cvqs.mistakeservice.service;

import com.cvqs.mistakeservice.dto.CoordinateDto;
import com.cvqs.mistakeservice.dto.MistakeDto;
import com.cvqs.mistakeservice.dto.MistakeIdDto;
import com.cvqs.mistakeservice.exception.CoordinateNotFoundException;
import com.cvqs.mistakeservice.mapper.ModelMapperService;
import com.cvqs.mistakeservice.model.Coordinate;
import com.cvqs.mistakeservice.model.Mistake;
import com.cvqs.mistakeservice.repository.CoordinateRepository;
import com.cvqs.mistakeservice.repository.MistakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateServiceTest {

   /* private CoordinateService coordinateService;

    private CoordinateRepository coordinateRepository;
    private MistakeRepository mistakeRepository;
    private ModelMapperService modelMapperService;

    @BeforeEach
    void setUp() {
        coordinateRepository = Mockito.mock(CoordinateRepository.class);
        mistakeRepository = Mockito.mock(MistakeRepository.class);
        modelMapperService = Mockito.mock(ModelMapperService.class);

        coordinateService = new CoordinateService(coordinateRepository,mistakeRepository,modelMapperService);
    }


    @Test
    public void whenGetAllCalledWithValidRequest_itShouldReturnValidListOfCoordinateDto(){
        Coordinate coordinate1 = new Coordinate(1, 150, 150,false);
        Coordinate coordinate2 = new Coordinate(2, 170, 170,false);
        MistakeIdDto mistakeIdDto = new MistakeIdDto(1,"hata");
        List<Coordinate> coordinateList = new ArrayList<>();
        coordinateList.add(coordinate1);
        coordinateList.add(coordinate2);

        List<CoordinateDto> coordinateDtoList = new ArrayList<>();
        coordinateDtoList.add(new CoordinateDto(1, 150, 150, mistakeIdDto.getMistakeId()));
        coordinateDtoList.add(new CoordinateDto(2, 170, 170, mistakeIdDto.getMistakeId()));

        Mockito.when(coordinateRepository.findAll().stream().map(CoordinateDto::convert).collect(Collectors.toList())).thenReturn(coordinateDtoList);
        List<CoordinateDto> result = coordinateService.getAll();
        assertEquals(coordinateDtoList, result);

    }

    @Test
    public void whenFindByIdCalledWithValidRequest_itShouldReturnCoordinateDto() {
        Coordinate coordinate = new Coordinate(1, 150, 150, false);

        CoordinateDto expectedCoordinateDto = new CoordinateDto(1, 150, 150, 1);

        Mockito.when(coordinateRepository.findById(1)).thenReturn(Optional.of(coordinate));

        CoordinateDto actualCoordinateDto = coordinateService.findById(1);

        assertEquals(expectedCoordinateDto, actualCoordinateDto);

        Mockito.verify(coordinateRepository).findById(1);
    }

    @Test
    public void testFindById_InvalidId_ThrowsCoordinateNotFoundException() {
        Mockito.when(coordinateRepository.findById(1)).thenReturn(Optional.empty());
        Mockito.verify(coordinateRepository).findById(1);
        assertThrows(CoordinateNotFoundException.class, () -> coordinateService.findById(1));
    }*/
}


