package com.dvSystem.StockSystem.controller;

import com.dvSystem.StockSystem.dto.DonorDTO;
import com.dvSystem.StockSystem.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donors")
@CrossOrigin("*")
public class DonorController {
    private final DonorService donorService;

    @Autowired
    public DonorController(DonorService donorService){
        this.donorService = donorService;
    }

    @PostMapping
    public ResponseEntity<?> registerDonor(@RequestBody DonorDTO newDonor){
        try {
            DonorDTO donorDTO = donorService.register(newDonor);
            return ResponseEntity.ok(donorDTO);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<?>> listDonors(){
        try {
            List<DonorDTO> donorDTOS = donorService.list();
            return ResponseEntity.ok(donorDTOS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findDonor(@PathVariable Long id){
        try {
            DonorDTO donorDTO = donorService.findById(id);
            return ResponseEntity.ok(donorDTO);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDonor(@PathVariable Long id, @RequestBody DonorDTO donorUpdated){
        try {
            DonorDTO donorDTO = donorService.updateById(id, donorUpdated);
            return ResponseEntity.ok(donorDTO);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDonor(@PathVariable Long id){
        try{
            donorService.deleteById(id);
            return ResponseEntity.ok("Donor deleted successfully!");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
