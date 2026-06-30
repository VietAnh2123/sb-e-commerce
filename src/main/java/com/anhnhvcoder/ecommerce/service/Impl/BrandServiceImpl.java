package com.anhnhvcoder.ecommerce.service.Impl;

import com.anhnhvcoder.ecommerce.model.Brand;
import com.anhnhvcoder.ecommerce.repository.BrandRepository;
import com.anhnhvcoder.ecommerce.request.BrandRequest;
import com.anhnhvcoder.ecommerce.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public Brand createBrand(BrandRequest request) {

        if(brandRepository.existsByNameIgnoreCase(request.getName())){
            throw new IllegalArgumentException("Brand already exists");
        }

        Brand brand = new Brand();
        brand.setName(request.getName());
        brand.setDescription(request.getDescription());
        brand.setLogo(request.getLogo());

        return brandRepository.save(brand);
    }
}
