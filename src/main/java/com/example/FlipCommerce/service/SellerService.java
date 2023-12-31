package com.example.FlipCommerce.service;

import com.example.FlipCommerce.dto.requestDto.SellerRequestDto;
import com.example.FlipCommerce.dto.responseDto.SellerResponseDto;
import com.example.FlipCommerce.model.Seller;
import com.example.FlipCommerce.repository.SellerRepository;
import com.example.FlipCommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto)
    {
        //dto- entity
        Seller seller= SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);
        // save seller
        Seller savedSeller=sellerRepository.save(seller);
        //prepare response Dto
       return SellerTransformer.SellerToSellerResponseDto(savedSeller);
    }
}
