package com.enesdeniz.commonservice.service.productService;


import com.enesdeniz.commonservice.dto.request.UserRequest;
import com.enesdeniz.commonservice.dto.response.ProductResponse;
import com.enesdeniz.commonservice.entities.Product;
import com.enesdeniz.commonservice.entities.User;
import com.enesdeniz.commonservice.enums.UserType;
import com.enesdeniz.commonservice.repository.ProductRepository;
import com.enesdeniz.commonservice.repository.UserRepository;
import com.enesdeniz.commonservice.services.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * this class tests the product service for the common microservice
 */

@SpringBootTest
public class ProductServiceImplTest extends ProductTestSupport {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("As a result of the test, the product result should be returned according to the user id")
    void getProductByUserIdTest(){

        User user = User.builder()
            .userType(UserType.INDIVIDUAL)
            .name("Enes Deniz")
            .email("enodeniz190@gmail.com")
            .password("12345")
            .build();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> {

            ProductResponse response = productService.getProductByUserId(1L);

            assertNotNull(response);

        });

    }

    @Test
    @DisplayName("a new product should be purchased as a result of the test")
    void createProductTest(){

        Product product = prepareProduct();

        productRepository.save(product);

        verify(productRepository, Mockito.times(1)).save(product);

    }

    @Test
    @DisplayName("a new product should be purchased as a result of the test")
    void updateRemainingPieceTest(){

        // Given
        Product product = prepareProduct();

        User user = prepareUser();

        when(productRepository.findProductByUserId(user.getId()))
            .thenReturn(Optional.ofNullable(product));

        // When
        assert product != null;
        product.setRemainingPiece(20);

        productRepository.save(product);

        verify(productRepository, Mockito.times(1)).save(product);

        // Then
        assertThat(product.getRemainingPiece())
            .isEqualTo(20);

    }


}
