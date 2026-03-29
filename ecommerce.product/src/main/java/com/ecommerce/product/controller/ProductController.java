package com.ecommerce.product.controller;

import com.ecommerce.product.dto.request.ProductRequest;
import com.ecommerce.product.dto.response.ProductResponse;
import com.ecommerce.product.service.ProductService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Validated @RequestBody ProductRequest request){
        return ResponseEntity.ok(productService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id){
       return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAll(
            @RequestParam int page,@RequestParam int size,
            @RequestParam(defaultValue = "id") String sortBy){
       return ResponseEntity.ok(productService.getAll(page,size,sortBy));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponse>> search(
            @RequestParam String keyword,@RequestParam int page,@RequestParam int size){
      return ResponseEntity.ok(productService.search(keyword, page, size));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<ProductResponse>> filter(
            @RequestParam(required = false) String category, @RequestParam(required = false)BigDecimal min,
            @RequestParam(required = false) BigDecimal max,@RequestParam int page,@RequestParam int size){
        return ResponseEntity.ok(productService.filter(category,min,max,page,size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
         productService.deleteById(id);
         return ResponseEntity.ok("Deleted !");
    }

}
