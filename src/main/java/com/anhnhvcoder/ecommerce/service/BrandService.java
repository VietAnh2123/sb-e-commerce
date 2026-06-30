package com.anhnhvcoder.ecommerce.service;

import com.anhnhvcoder.ecommerce.model.Brand;
import com.anhnhvcoder.ecommerce.request.BrandRequest;

public interface BrandService {

    Brand createBrand(BrandRequest request);
}
