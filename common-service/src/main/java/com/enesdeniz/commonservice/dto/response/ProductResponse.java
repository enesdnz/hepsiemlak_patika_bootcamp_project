package com.enesdeniz.commonservice.dto.response;

import com.enesdeniz.commonservice.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private User user;
    private Date purchaseDate;
    private Date endDate;
    private int remainingPiece;


}
