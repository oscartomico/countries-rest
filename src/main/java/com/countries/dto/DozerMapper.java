package com.countries.dto;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class used to get DozerBeanMappers
 * @author Oscar Tomico
 *
 */
@Configuration
public class DozerMapper {
    @Bean
    public Mapper beanMapper() {
        return new DozerBeanMapper();
    }
}
