package com.jaisgroup.dnd.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class AbstractConverter {

    private ModelMapper modelMapper;

    public ModelMapper getModelMapper() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                    .setMatchingStrategy(MatchingStrategies.STANDARD);
        }
        return modelMapper;
    }
}
