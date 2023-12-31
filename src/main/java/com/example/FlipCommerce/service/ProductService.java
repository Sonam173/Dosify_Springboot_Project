package com.example.FlipCommerce.service;

import com.example.FlipCommerce.Enum.Category;
import com.example.FlipCommerce.dto.requestDto.ProductRequestDto;
import com.example.FlipCommerce.dto.responseDto.ProductResponseDto;
import com.example.FlipCommerce.exception.SellerNotFoundException;
import com.example.FlipCommerce.model.Product;
import com.example.FlipCommerce.model.Seller;
import com.example.FlipCommerce.repository.ProductRepository;
import com.example.FlipCommerce.repository.SellerRepository;
import com.example.FlipCommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;
public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException
{
    //first all do check seller is exist or not
    Seller seller= sellerRepository.findByEmailId(productRequestDto.getSellerEmailId());
    if(seller==null)
    {
        throw new SellerNotFoundException("EmailId is not registered");
    }
    //dto to entity
    Product product= ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
    // set seller
    product.setSeller(seller);
    seller.getProducts().add(product);
    //save product
    Seller savedSeller=sellerRepository.save(seller);//save both product and seller
    Product savedProduct=savedSeller.getProducts().get(savedSeller.getProducts().size()-1);
    //prepare response dto
    return ProductTransformer.ProductToProductResponseDto(savedProduct);
}
public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category,int price)
{
    List<Product> products=productRepository.findByCategoryAndPrice(category,price);
    // prepare a list of dtos
    List<ProductResponseDto> productResponseDtos = new ArrayList<>();
    for(Product product: products){
        productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
    }
    return productResponseDtos;
}
}
