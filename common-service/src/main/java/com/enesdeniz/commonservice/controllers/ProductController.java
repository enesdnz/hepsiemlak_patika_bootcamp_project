package com.enesdeniz.commonservice.controllers;

import com.enesdeniz.commonservice.dto.request.PaymentRequest;
import com.enesdeniz.commonservice.dto.response.ProductResponse;
import com.enesdeniz.commonservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("{userId}")
    public ResponseEntity<ProductResponse> getProductByUserId(@PathVariable("userId") Long userId){

        ProductResponse response = productService.getProductByUserId(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/purchase")
    public ResponseEntity<?> buyProduct(@RequestBody PaymentRequest request){

        return new ResponseEntity<>(productService.buyProduct(request), HttpStatus.CREATED);
    }
}
