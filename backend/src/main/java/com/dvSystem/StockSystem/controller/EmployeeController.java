package com.dvSystem.StockSystem.controller;

import com.dvSystem.StockSystem.dto.EmployeeDTO;
import com.dvSystem.StockSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> registerEmployee(@RequestBody EmployeeDTO newEmployee){
        try {
            EmployeeDTO employee = employeeService.register(newEmployee);
            return ResponseEntity.ok(employee);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<?>> listEmployees(){
        try {
            List<EmployeeDTO> employeeDTOS = employeeService.list();
            return ResponseEntity.ok(employeeDTOS);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployee(@PathVariable Long id){
        try {
            EmployeeDTO employeeDTO = employeeService.findById(id);
            return ResponseEntity.ok(employeeDTO);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeUpdated){
        try {
            EmployeeDTO employeeDTO = employeeService.updateById(id, employeeUpdated);
            return ResponseEntity.ok(employeeDTO);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        try {
            employeeService.deleteById(id);
            return ResponseEntity.ok("Employee deleted successfully!");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
