package com.ecommerce.product.service;

import com.ecommerce.product.dto.request.ProductRequest;
import com.ecommerce.product.dto.response.ProductResponse;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    public ProductResponse create(ProductRequest request){
        Product product = productMapper.toEntity(request);
        product.setCreatedAt(LocalDateTime.now());
       return productMapper.toResponse(productRepository.save(product));
    }

    public ProductResponse getById(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
        return productMapper.toResponse(product);
    }

    public Page<ProductResponse> getAll(int page,int size,String sortBy){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        return productRepository.findAll(pageRequest).map(productMapper::toResponse);
    }

   public   Page<ProductResponse> search(String keyword,int page,int size){
       PageRequest pageRequest = PageRequest.of(page, size);
       return productRepository.findByNameContainingIgnoreCase(keyword,pageRequest).map(productMapper::toResponse);
   }

   public Page<ProductResponse> filter(String category, BigDecimal min,BigDecimal max,int page,int size){
       PageRequest pageRequest = PageRequest.of(page, size);
       if(category != null){
           return productRepository.findByCategory(category,pageRequest).map(productMapper::toResponse);
       }
       if(min != null && max != null){
           return productRepository.findByPriceBetween(min,max,pageRequest).map(productMapper::toResponse);
       }
       return productRepository.findAll(pageRequest).map(productMapper::toResponse);
   }
   public void deleteById(Long id){
        productRepository.deleteById(id);
   }
}
