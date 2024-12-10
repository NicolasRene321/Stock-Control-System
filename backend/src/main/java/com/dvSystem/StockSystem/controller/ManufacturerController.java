package com.dvSystem.StockSystem.controller;

import com.dvSystem.StockSystem.dto.ManufacturerDTO;
import com.dvSystem.StockSystem.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manufacturer")
@CrossOrigin("*")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService){
        this.manufacturerService = manufacturerService;
    }

    @PostMapping
    public ResponseEntity<?> registerManufacturer(@RequestBody ManufacturerDTO newManufacturer){
        try {
            ManufacturerDTO manufacturer = manufacturerService.register(newManufacturer);
            return ResponseEntity.ok(manufacturer);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<?>> listManufacturers(){
        try {
            List<ManufacturerDTO> manufacturerDTOS = manufacturerService.list();
            return ResponseEntity.ok(manufacturerDTOS);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findManufacturer(@PathVariable Long id){
        try {
            ManufacturerDTO manufacturerDTO = manufacturerService.findById(id);
            return ResponseEntity.ok(manufacturerDTO);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateManufacturer(@PathVariable Long id, @RequestBody ManufacturerDTO manufacturerDTO){
        try {
            ManufacturerDTO manufacturerUpdated = manufacturerService.updateById(id, manufacturerDTO);
            return ResponseEntity.ok(manufacturerUpdated);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteManufacturer(@PathVariable Long id){
        try {
            manufacturerService.deleteById(id);
            return ResponseEntity.ok("Manufacturer deleted successfully!");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
