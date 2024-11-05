package com.bentoo.taskman.utils;


import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Utils {
    public void copyNonNullProperties (Object source, Object destination){
        BeanWrapper wrapper = new BeanWrapperImpl(source);
        var descriptors = wrapper.getPropertyDescriptors();
        Set<String> nullsProperties = new HashSet<>();

        for(var descriptor : descriptors){
            String property = descriptor.getName();
            var hasValue = wrapper.getPropertyValue(property);
            if(hasValue==null){
                nullsProperties.add(property);
            }
        }

        String[] fields = nullsProperties.toArray(new String[] {});
        BeanUtils.copyProperties(source,destination,fields);
    }

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
