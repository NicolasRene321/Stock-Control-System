package com.dvSystem.StockSystem.controller;

import com.dvSystem.StockSystem.dto.StockDTO;
import com.dvSystem.StockSystem.model.Stock;
import com.dvSystem.StockSystem.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stock")
@CrossOrigin("*")
public class StockController {
    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<List<?>> listStocks(){
        try {
            List<StockDTO> stockDTOS = stockService.list();
            return ResponseEntity.ok(stockDTOS);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{productName}")
    private ResponseEntity<?> findStockByProductName(@PathVariable String productName){
        try {
            Optional<StockDTO> stockOptional = stockService.findStockDTOByProductName(productName);
            if (stockOptional.isPresent()){
                return ResponseEntity.ok(stockOptional.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
