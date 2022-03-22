package com.enesdeniz.commonservice.service.advertService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.enesdeniz.commonservice.client.PaymentFeignClient;
import com.enesdeniz.commonservice.core.Result;
import com.enesdeniz.commonservice.dto.request.AdvertRequest;
import com.enesdeniz.commonservice.dto.response.AdvertResponse;
import com.enesdeniz.commonservice.dto.response.PaymentResponse;
import com.enesdeniz.commonservice.entities.Advert;
import com.enesdeniz.commonservice.entities.User;
import com.enesdeniz.commonservice.queue.QueueService;
import com.enesdeniz.commonservice.repository.AdvertRepository;
import com.enesdeniz.commonservice.repository.UserRepository;
import com.enesdeniz.commonservice.services.AdvertService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * this class tests the advert service for the common microservice
 */

@SpringBootTest
public class AdvertServiceImplTest extends AdvertTestSupport{

    @InjectMocks
    private AdvertService advertService;

    @Mock
    private AdvertRepository advertRepository;

    @Mock
    private PaymentFeignClient paymentFeignClient;

    @Mock
    private QueueService queueService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("When saving test advert, it should also register the user")
    void saveAdvertWithUserTest(){
        AdvertRequest request = prepareAdvertRequest();

        Optional<User> user = Optional.of(prepareUser());

        Mockito
            .when(userRepository.findById(request.getCreatorUserId()))
            .thenReturn(user);

        Mockito
            .when(advertRepository.save(any()))
            .thenReturn(prepareAdvert("baslik"));

        Mockito
            .when(paymentFeignClient.savePayment(any()))
            .thenReturn(new ResponseEntity<PaymentResponse>(new PaymentResponse(), HttpStatus.OK));

        assertDoesNotThrow(() -> {
            advertService.saveAdvert(request);
            verify(advertRepository).save(any());
            verify(queueService).updateAdvertStatus(any());
            verify(paymentFeignClient).savePayment(any());

        });

    }

    @Test
    @DisplayName("advert should be updated according to the data sent")
    void updateAdvertTest(){
        // Given
        Advert advert = prepareAdvert("testBaslik");

        when(advertRepository.findByAdvertNo(5274))
            .thenReturn(Optional.ofNullable(advert));

        // When
        AdvertRequest advertRequest = new AdvertRequest();
        advertRequest.setPrice(new BigDecimal(2000));
        advertRequest.setTitle("newTitle");

        advertService.updateAdvert(5274L, advertRequest);

        // Then
        assert advert != null;
        assertThat(advertRequest.getPrice())
            .isEqualTo(new BigDecimal(2000));

        assertThat(advertRequest.getTitle())
            .isEqualTo("newTitle");
    }

    @Test
    @DisplayName("advert should be deleted as a result of the test")
    void deleteAdvertTest(){

        Advert advert = prepareAdvert("testBaslik");

        advertRepository.delete(advert);

        verify(advertRepository, Mockito.times(1)).delete(advert);
    }

    @Test
    @DisplayName("When saving test advert, it should also register the user")
    void saveAdvertWithUserTest1() throws Exception {
        AdvertRequest request = prepareAdvertRequest();

        Optional<User> user = Optional.of(prepareUser());

        Mockito
            .when(userRepository.findById(request.getCreatorUserId()))
            .thenReturn(user);

        Mockito
            .when(advertRepository.save(any()))
            .thenReturn(prepareAdvert(""));

        Mockito
            .when(paymentFeignClient.savePayment(any()))
            .thenReturn(new ResponseEntity<PaymentResponse>(new PaymentResponse(), HttpStatus.OK));

        Result result = advertService.saveAdvert(request);

        assertTrue(result.isSuccess());
        verify(queueService).updateAdvertStatus(any());
        verify(paymentFeignClient).savePayment(any());

    }


    @Test()
    @DisplayName("when user not found throw exception. "
        + "method name can be: should_throw_exception_when_user_not_found")
    void saveAdvertWithoutUserTest() {

        assertThrows(Exception.class, () -> {
            advertService.saveAdvert(prepareAdvertRequest());
        }, "The advert could not be save");

    }

    @Test
    @DisplayName("All adverts should come as a result of the test")
    void getAllAdvertsTest() {

        Mockito.when(advertRepository.findAll()).thenReturn(prepareAdvertList());

        List<AdvertResponse> responseList = advertService.getAllAdverts();

        assertNotEquals(0, responseList.size());

        assertThat(responseList.size()).isNotZero();

        for (AdvertResponse response : responseList) {
            assertThat(response.getAdvertNo()).isEqualTo(0);

            assertEquals(new BigDecimal(1500), response.getPrice());

        }

    }

    @Test
    @DisplayName("It tests whether the advert corresponding to the given advert number is received")
    void getAdvertByAdvertIdTest() {

        Mockito
            .when(advertRepository.findByAdvertNo(0))
            .thenReturn(Optional.of(prepareAdvert("baslik")));

        AdvertResponse response = advertService.getAdvertByAdvertNo(0);
        assertNotNull(response);
        assertEquals("baslik", response.getTitle());
        assertNotNull(response.getCreatorUser());
        assertNotNull(response.getAdvertStatus());
        assertNotNull(response.getCreateDate());
        assertNotNull(response.getPrice());

    }
}
