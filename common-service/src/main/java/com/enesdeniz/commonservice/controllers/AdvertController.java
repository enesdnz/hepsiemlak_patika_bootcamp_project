package com.enesdeniz.commonservice.controllers;

import com.enesdeniz.commonservice.dto.response.AdvertResponse;
import com.enesdeniz.commonservice.enums.AdvertStatus;
import com.enesdeniz.commonservice.exception.EntityIsNullException;
import com.enesdeniz.commonservice.services.AdvertService;
import com.enesdeniz.commonservice.core.Result;
import com.enesdeniz.commonservice.dto.request.AdvertRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("advert")
public class AdvertController {

    @Autowired
    private AdvertService advertService;

    @GetMapping
    public ResponseEntity<List<AdvertResponse>> getAllAdverts() {
        return new ResponseEntity<>(advertService.getAllAdverts(), HttpStatus.OK);
    }

    @GetMapping("{advertNo}")
    public ResponseEntity<AdvertResponse> getAdvertByAdvertNo(@PathVariable(name="advertNo") int advertNo){
        AdvertResponse response = advertService.getAdvertByAdvertNo(advertNo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/status/{advertStatus}")
    public ResponseEntity<List<AdvertResponse>> getAdvertByAdvertStatus(@PathVariable(name="advertStatus") String advertStatus){

        //We took the parameter as a string against the fact that they write the parameter in lowercase in the url
        return new ResponseEntity<>(advertService.getAdvertByAdvertStatus(AdvertStatus.valueOf(advertStatus.toUpperCase(Locale.ROOT))), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Result> createAdvert(@RequestBody AdvertRequest request) throws EntityIsNullException {
        return new ResponseEntity<>(advertService.saveAdvert(request), HttpStatus.CREATED);
    }

    @PutMapping(path = "{advertNo}")
    public ResponseEntity<?> updateAdvert(@PathVariable(name="advertNo") Long advertNo, @RequestBody AdvertRequest request) {
        //If the sent request is null, we return a bad request
        if (request == null) {
            return new ResponseEntity<>("Your request is null!", HttpStatus.BAD_REQUEST);
        }
        else{
            Result response = advertService.updateAdvert(advertNo , request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    }

    @DeleteMapping("{advertNo}")
    public ResponseEntity<?> deleteAdvert(@PathVariable(name="advertNo") Long advertNo) {

        Result response = advertService.deleteAdvert(advertNo);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
