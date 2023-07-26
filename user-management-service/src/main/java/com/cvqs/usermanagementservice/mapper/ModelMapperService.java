package com.cvqs.usermanagementservice.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service class for configuring and providing a ModelMapper instance.
 */
@Service
public class ModelMapperService {

    private final ModelMapper modelMapper;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Constructs a ModelMapperService with the specified ModelMapper instance.
     *
     * @param modelMapper the ModelMapper instance
     */
    public ModelMapperService(ModelMapper modelMapper){
        logger.info("ModelMapperService: ctor entered");
        this.modelMapper = modelMapper;
    }

    /**
     * Returns a ModelMapper instance configured for request mappings.
     *
     * @return a ModelMapper instance for request mappings
     */
    public ModelMapper forRequest() {
        logger.info("ModelMapperService: forRequest method entered");
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return this.modelMapper;
    }

    /**
     * Returns a ModelMapper instance configured for response mappings.
     *
     * @return a ModelMapper instance for response mappings
     */
    public ModelMapper forResponse() {
        logger.info("ModelMapperService: forResponse entered");
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return this.modelMapper;
    }
}
