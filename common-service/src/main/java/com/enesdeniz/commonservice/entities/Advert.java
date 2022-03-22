package com.enesdeniz.commonservice.entities;

import com.enesdeniz.commonservice.enums.AdvertStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "advert")
public class Advert extends BaseEntity{

    private int advertNo;
    private String title;
    @OneToOne
    @JoinColumn(name = "creator_user_id", referencedColumnName = "id")
    private User creatorUser;
    private BigDecimal price;
    private int period;
    private boolean isForward = false;
    private boolean isExamine = false;
    private Date createDate;
    private Date updateDate;
    @Enumerated(EnumType.STRING)
    private AdvertStatus advertStatus;

}
