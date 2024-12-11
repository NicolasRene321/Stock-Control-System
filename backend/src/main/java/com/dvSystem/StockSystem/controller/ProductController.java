package com.dvSystem.StockSystem.controller;

import com.dvSystem.StockSystem.dto.ProductDTO;
import com.dvSystem.StockSystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<?> registerProduct(@RequestBody ProductDTO newProductDTO){
        try {
            ProductDTO productDTO = productService.register(newProductDTO);
            return ResponseEntity.ok(productDTO);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<?>> listProducts(){
        try {
            List<ProductDTO> productDTOS = productService.list();
            return ResponseEntity.ok(productDTOS);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
