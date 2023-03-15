package com.kaffle.kaffle.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kaffle.kaffle.db.JamoEntity;

@Configuration
public class configuration {

    @Bean
    public JamoEntity jamo(){
        return new JamoEntity();
    }



}
