package com.cvqs.mistakeservice.service;

import com.cvqs.mistakeservice.dto.*;
import com.cvqs.mistakeservice.exception.ImageIOException;
import com.cvqs.mistakeservice.exception.MistakeNotFoundException;
import com.cvqs.mistakeservice.exception.VehicleNotFoundException;
import com.cvqs.mistakeservice.model.Coordinate;
import com.cvqs.mistakeservice.model.Mistake;
import com.cvqs.mistakeservice.mapper.ModelMapperService;
import com.cvqs.mistakeservice.model.Vehicle;
import com.cvqs.mistakeservice.repository.MistakeRepository;
import com.cvqs.mistakeservice.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.awt.*;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MistakeService {

    Logger logger = LoggerFactory.getLogger(MistakeService.class);
    private final MistakeRepository mistakeRepository;

    private final VehicleRepository vehicleRepository;

    private final ModelMapperService modelMapperService;


    public MistakeService(MistakeRepository mistakeRepository, VehicleRepository vehicleRepository, ModelMapperService modelMapperService){
        logger.info("MistakeService: ctor entered");
        this.mistakeRepository = mistakeRepository;
        this.vehicleRepository=vehicleRepository;
        this.modelMapperService = modelMapperService;
    }

    public void addMistake(MultipartFile multipartFile, CreateMistakeRequestDto createMistakeRequestDto) throws Exception {
        logger.info("MistakeService: addMistake method entered");
        Mistake mistake = this.modelMapperService.forRequest().map(createMistakeRequestDto, Mistake.class);
        byte[] imageBytes = multipartFile.getBytes();
        Blob imageBlob = new SerialBlob(imageBytes);
        mistake.setImage(imageBlob);
        mistake.setId(0);
        mistake.vehicle = vehicleRepository.findById(createMistakeRequestDto.getVehicleId()).orElseThrow(()-> new VehicleNotFoundException("Vehicle could not found by id: " + createMistakeRequestDto.getVehicleId()));
        mistakeRepository.save(mistake);
        logger.info("MistakeService: mistake saved success");
    }

    public MistakeDto findById(int id){
        logger.info("MistakeService: findById method entered");
        return mistakeRepository.findById(id).map(MistakeDto::convert).orElseThrow(() -> new MistakeNotFoundException("Mistake could not found by id: " + id));
    }

    public MistakeDto findByName(int id){
        logger.info("MistakeService: findByName method entered");
        return mistakeRepository.findById(id).map(MistakeDto::convert).orElseThrow(() -> new MistakeNotFoundException("Mistake could not found by id: " + id));
    }

    public List<MistakeDto> getAllMistakes(){
        logger.info("MistakeService: getAllMistakes method entered");
        return mistakeRepository.findAll().stream().map(MistakeDto::convert).collect(Collectors.toList());
    }

    public void update(UpdateImageDto imageDto) throws SQLException {
        logger.info("MistakeService: update method entered");
        Mistake mistake = mistakeRepository.findById(imageDto.getId()).orElseThrow(()->new MistakeNotFoundException("Mistake could not found by id: " + imageDto.getId()));
        Blob imageBlob = new SerialBlob(imageDto.getImage());
        mistake.setImage(imageBlob);
        mistakeRepository.save(mistake);
        logger.info("MistakeService: image updated success");
    }

    public void delete(int id){
        logger.info("MistakeService: delete method entered");
        Mistake mistake = mistakeRepository.findById(id).orElseThrow(()-> new MistakeNotFoundException("Mistake could not found by id: " + id));
        mistake.setDeleted(true);
        mistakeRepository.save(mistake);
        logger.info("MistakeService: mistake deleted success");
    }

    private static String convertBlobToBase64(Blob blob) {

        try {
            byte[] data = blob.getBytes(1, (int) blob.length());
            return Base64.getEncoder().encodeToString(data);
        } catch (SQLException e) {

            return e.getMessage();
        }

    }

}
