package com.dvSystem.StockSystem.service;

import com.dvSystem.StockSystem.dto.ManufacturerDTO;
import com.dvSystem.StockSystem.model.Employee;
import com.dvSystem.StockSystem.model.Manufacturer;
import com.dvSystem.StockSystem.repository.EmployeeRepository;
import com.dvSystem.StockSystem.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {
    private ManufacturerRepository manufacturerRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public ManufacturerService(ManufacturerRepository manufacturerRepository, EmployeeRepository employeeRepository){
        this.manufacturerRepository = manufacturerRepository;
        this.employeeRepository = employeeRepository;
    }

    public ManufacturerDTO register(ManufacturerDTO manufacturerDTO){
        Manufacturer manufacturer = convertToEntity(manufacturerDTO);

        // Recover employee code
        Long codEmployee = manufacturerDTO.getEmployeeID();
        Employee employee = employeeRepository.findById(codEmployee)
                .orElseThrow(()-> new IllegalArgumentException("Employee not found"));

        // Associate the employee with manufacturer
        manufacturer.setEmployee(employee);
        Manufacturer manufacturerSaved = manufacturerRepository.save(manufacturer);

        // Update DTO with the employee code
        ManufacturerDTO manufacturerDTOSaved = convertToDTO(manufacturerSaved);
        manufacturerDTOSaved.setEmployeeID(codEmployee);

        return manufacturerDTOSaved;
    }

    public List<ManufacturerDTO> list(){
        List<Manufacturer> manufacturers = manufacturerRepository.findAll();
        List<ManufacturerDTO> manufacturerDTOS = new ArrayList<>();

        for (Manufacturer manufacturer: manufacturers){
            ManufacturerDTO manufacturerDTO = convertToDTO(manufacturer);
            manufacturerDTOS.add(manufacturerDTO);
        }

        return manufacturerDTOS;
    }

    public ManufacturerDTO findById(Long id) throws Exception {
        Optional<Manufacturer> manufacturerOptional = manufacturerRepository.findById(id);

        if (manufacturerOptional.isPresent()){
            return convertToDTO(manufacturerOptional.get());
        } else {
            throw new Exception("Manufacturer not found!");
        }
    }

    public ManufacturerDTO updateById(Long id, ManufacturerDTO manufacturerDTO) throws Exception {
        Optional<Manufacturer> manufacturerOptional = manufacturerRepository.findById(id);

        if (manufacturerOptional.isPresent()){
            Manufacturer manufacturer = manufacturerOptional.get();
            manufacturer.setNameManufacturer(manufacturerDTO.getNameManufacturer());

            // Recover and associate the employee with the manufacturer
            if (manufacturerDTO.getCodManufacturer() != null){
                Long codEmployee = manufacturerDTO.getEmployeeID();
                Employee employee = employeeRepository.findById(codEmployee)
                        .orElseThrow(()-> new IllegalArgumentException("Employee not found!"));

                manufacturer.setEmployee(employee);
            }

            Manufacturer manufacturerUpdated = manufacturerRepository.save(manufacturer);
            return convertToDTO(manufacturerUpdated);
        } else {
            throw new Exception("Manufacturer not found!");
        }
    }

    public void deleteById(Long id) throws Exception {
        Optional<Manufacturer> manufacturerOptional = manufacturerRepository.findById(id);
        if (manufacturerOptional.isPresent()){
            manufacturerRepository.deleteById(id);
        } else {
            throw new Exception("Manufacturer not found!");
        }
    }

    public Manufacturer convertToEntity(ManufacturerDTO manufacturerDTO){
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setNameManufacturer(manufacturerDTO.getNameManufacturer());
        return manufacturer;
    }

    public ManufacturerDTO convertToDTO(Manufacturer manufacturer){
        ManufacturerDTO manufacturerDTO = new ManufacturerDTO();
        manufacturerDTO.setCodManufacturer(manufacturer.getCodManufacturer());
        manufacturerDTO.setNameManufacturer(manufacturer.getNameManufacturer());

        // Verify if the employee is present in the database
        Employee employee = manufacturer.getEmployee();
        if (employee != null){
            manufacturerDTO.setEmployeeID(employee.getCodEmployee());
        }

        return manufacturerDTO;
    }

}
