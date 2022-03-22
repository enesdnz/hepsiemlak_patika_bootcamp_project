package com.enesdeniz.updateAdvertStatusservice.entities;

import com.enesdeniz.updateAdvertStatusservice.enums.AdvertStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "advert")
public class Advert{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
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
