package com.enesdeniz.commonservice.repository;

import com.enesdeniz.commonservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> getProductByUserId (Long userId);

    Optional<Product> findProductByUserId (Long userId);
}
