package com.enesdeniz.commonservice.repository;

import com.enesdeniz.commonservice.entities.Advert;
import com.enesdeniz.commonservice.enums.AdvertStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdvertRepository extends JpaRepository<Advert, Long> {

    Optional<Advert> findByAdvertNo(int advertNo);
    List<Advert> getAdvertByAdvertStatus(AdvertStatus advertStatus);
}
