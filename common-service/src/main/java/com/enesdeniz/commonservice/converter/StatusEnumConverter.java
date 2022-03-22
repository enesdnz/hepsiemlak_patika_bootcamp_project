package com.enesdeniz.commonservice.converter;

import com.enesdeniz.commonservice.enums.AdvertStatus;
import org.springframework.stereotype.Component;

import org.springframework.core.convert.converter.Converter;

@Component
public class StatusEnumConverter implements Converter<String, AdvertStatus> {

    @Override
    public AdvertStatus convert(String value) {
        return AdvertStatus.valueOf(value.toUpperCase());
    }
}
