package com.cvqs.usermanagementservice.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;



@Service
public class ModelMapperService {

    private final ModelMapper modelMapper;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ModelMapperService(ModelMapper modelMapper){
        logger.info("ModelMapperService: ctor entered");
        this.modelMapper = modelMapper;
    }

    public ModelMapper forRequest() {
        logger.info("ModelMapperService: forRequest method entered");
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return this.modelMapper;
    }

    public ModelMapper forResponse() {
        logger.info("ModelMapperService: forResponse entered");
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return this.modelMapper;
    }
}
