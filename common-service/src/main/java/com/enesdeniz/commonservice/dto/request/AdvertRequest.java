package com.enesdeniz.commonservice.dto.request;

import com.enesdeniz.commonservice.enums.AdvertStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AdvertRequest {

    private String title;
    private Long creatorUserId;
    private BigDecimal price;
    private int period;
    private AdvertStatus advertStatus;

}
