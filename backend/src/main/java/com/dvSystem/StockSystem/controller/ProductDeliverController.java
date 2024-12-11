package com.dvSystem.StockSystem.controller;

import com.dvSystem.StockSystem.dto.ProductDeliverDTO;
import com.dvSystem.StockSystem.repository.ProductDeliverRepository;
import com.dvSystem.StockSystem.service.ProductDeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deliver")
@CrossOrigin("*")
public class ProductDeliverController {
    private final ProductDeliverService productDeliverService;

    public ProductDeliverController(ProductDeliverService productDeliverService) {
        this.productDeliverService = productDeliverService;
    }

    @Autowired


    @PostMapping
    private ResponseEntity<?> registerProductDeliver(@RequestBody ProductDeliverDTO productDeliverDTO){
        try {
            ProductDeliverDTO productDeliverDTO1 = productDeliverService.register(productDeliverDTO);
            return ResponseEntity.ok(productDeliverDTO1);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    private ResponseEntity<List<?>> listProductDeliveries(){
        try {
            List<ProductDeliverDTO> productDeliverDTOS = productDeliverService.list();
            return ResponseEntity.ok(productDeliverDTOS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findDeliverProduct(@PathVariable Long id){
        try {
            Optional<ProductDeliverDTO> productDeliverDTO = productDeliverService.findById(id);
            if (productDeliverDTO.isPresent()){
                return ResponseEntity.ok(productDeliverDTO.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
