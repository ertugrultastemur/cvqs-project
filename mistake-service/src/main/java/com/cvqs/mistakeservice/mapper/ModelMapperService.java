package com.cvqs.mistakeservice.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service class for configuring and providing ModelMapper instances.
 */
@Service
public class ModelMapperService {

    private final ModelMapper modelMapper;

    /**
     * Constructs a new ModelMapperService with the specified ModelMapper instance.
     *
     * @param modelMapper the ModelMapper instance
     */
    public ModelMapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /**
     * Configures and returns the ModelMapper instance for handling request mapping.
     *
     * @return the ModelMapper instance for request mapping
     */
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.STANDARD);
        return this.modelMapper;
    }

    /**
     * Configures and returns the ModelMapper instance for handling response mapping.
     *
     * @return the ModelMapper instance for response mapping
     */
    public ModelMapper forResponse() {
        this.modelMapper.getConfiguration()
                .setAmbiguityIgnored(true)
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        return this.modelMapper;
    }
}
