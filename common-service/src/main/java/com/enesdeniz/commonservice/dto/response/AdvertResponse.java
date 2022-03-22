package com.enesdeniz.commonservice.dto.response;

import com.enesdeniz.commonservice.entities.User;
import com.enesdeniz.commonservice.enums.AdvertStatus;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class AdvertResponse {

    private int advertNo;
    private String title;
    private User creatorUser;
    private BigDecimal price;
    private int period;
    private Date createDate;
    private Date updateDate;
    private AdvertStatus advertStatus;

}
