package com.anhnhvcoder.ecommerce.controller;

import com.anhnhvcoder.ecommerce.model.Brand;
import com.anhnhvcoder.ecommerce.request.BrandRequest;
import com.anhnhvcoder.ecommerce.response.ApiResponse;
import com.anhnhvcoder.ecommerce.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<ApiResponse> createBrand(@RequestBody BrandRequest request) {
        Brand createdBrand = brandService.createBrand(request);
        return ResponseEntity.created(null).body(new ApiResponse(HttpStatus.CREATED.value(), createdBrand));
    }

}
