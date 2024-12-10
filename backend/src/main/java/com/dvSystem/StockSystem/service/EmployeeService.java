package com.dvSystem.StockSystem.service;

import com.dvSystem.StockSystem.dto.EmployeeDTO;
import com.dvSystem.StockSystem.model.Employee;
import com.dvSystem.StockSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO register(EmployeeDTO employeeDTO){
        Employee employee = convertToEntity(employeeDTO);
        Employee employeeSaved = employeeRepository.save(employee);
        return convertToDTO(employeeSaved);
    }

    public List<EmployeeDTO> list(){
       List<Employee> employees = employeeRepository.findAll();
       List<EmployeeDTO> employeeDTOS = new ArrayList<>();

       for (Employee employee: employees){
           EmployeeDTO employeeDTO = convertToDTO(employee);
           employeeDTOS.add(employeeDTO);
       }

       return employeeDTOS;
    }
    
    public EmployeeDTO findById(Long id) throws Exception {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()){
            return convertToDTO(employeeOptional.get());
        } else {
            throw new Exception("Employee not found!");
        }
    }

    public EmployeeDTO updateById(Long id, EmployeeDTO employeeDTO) throws Exception {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            employee.setEmployeeName(employeeDTO.getEmployeeName());
            employee.setEmployeeCpf(employeeDTO.getEmployeeCpf());
            employee.setEmployeePhone(employeeDTO.getEmployeePhone());
            employee.setEmployeePosition(employeeDTO.getEmployeePosition());
            employee.setEmployeeSituation(employeeDTO.getEmployeeSituation());

            Employee employeeSaved = employeeRepository.save(employee);
            return convertToDTO(employeeSaved);
        } else {
            throw new Exception("Employee not found!");
        }
    }

    public void deleteById(Long id) throws Exception {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()){
            employeeRepository.deleteById(id);
        } else {
            throw new Exception("Employee not found!");
        }
    }

    private Employee convertToEntity(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        employee.setEmployeeName(employeeDTO.getEmployeeName());
        employee.setEmployeeCpf(employeeDTO.getEmployeeCpf());
        employee.setEmployeePhone(employeeDTO.getEmployeePhone());
        employee.setEmployeePosition(employeeDTO.getEmployeePosition());
        employee.setEmployeeSituation(employeeDTO.getEmployeeSituation());
        return employee;
    }

    private EmployeeDTO convertToDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setCodEmployee(employee.getCodEmployee());
        employeeDTO.setEmployeeName(employee.getEmployeeName());
        employeeDTO.setEmployeePhone(employee.getEmployeePhone());
        employeeDTO.setEmployeeCpf(employee.getEmployeeCpf());
        employeeDTO.setEmployeeSituation(employee.getEmployeeSituation());
        employeeDTO.setEmployeePosition(employee.getEmployeePosition());
        return employeeDTO;
    }
}
