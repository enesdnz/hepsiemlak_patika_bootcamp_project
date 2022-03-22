package com.enesdeniz.updateAdvertStatusservice.repository;

import com.enesdeniz.updateAdvertStatusservice.entities.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertRepository extends JpaRepository<Advert, Long> {
}
