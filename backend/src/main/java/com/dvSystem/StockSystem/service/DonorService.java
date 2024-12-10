package com.dvSystem.StockSystem.service;

import com.dvSystem.StockSystem.dto.DonorDTO;
import com.dvSystem.StockSystem.model.Donor;
import com.dvSystem.StockSystem.model.Employee;
import com.dvSystem.StockSystem.repository.DonorRepository;
import com.dvSystem.StockSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DonorService {
    private final DonorRepository donorRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public DonorService(DonorRepository donorRepository, EmployeeRepository employeeRepository){
        this.donorRepository = donorRepository;
        this.employeeRepository = employeeRepository;
    }

    public DonorDTO register(DonorDTO donorDTO) throws Exception {
        Donor donor = convertToEntity(donorDTO);

        // Check if employee exists
        Long codEmployee = donorDTO.getEmployeeId();
        Employee employee = employeeRepository.findById(codEmployee)
                .orElseThrow(()-> new IllegalArgumentException("Employee not found!"));

        // Associate the employee with donor
        donor.setEmployee(employee);
        Donor donorSaved = donorRepository.save(donor);

        DonorDTO donorDTOSaved = convertToDTO(donorSaved);
        donorDTOSaved.setEmployeeId(codEmployee);
        return donorDTOSaved;
    }

    public List<DonorDTO> list(){
        List<Donor> donors = donorRepository.findAll();
        List<DonorDTO> donorDTOS = new ArrayList<>();

        for (Donor donor: donors){
            DonorDTO donorDTO = convertToDTO(donor);
            donorDTOS.add(donorDTO);
        }

        return donorDTOS;
    }

    public DonorDTO findById(Long id) throws Exception {
        Optional<Donor> donorOptional = donorRepository.findById(id);

        if (donorOptional.isPresent()){
            return convertToDTO(donorOptional.get());
        } else {
            throw new Exception("Donor not found!");
        }
    }

    public DonorDTO updateById(Long id, DonorDTO donorDTO) throws Exception {
        Optional<Donor> donorOptional = donorRepository.findById(id);

        if (donorOptional.isPresent()){
            Donor donor = donorOptional.get();
            donor.setDonorName(donorDTO.getDonorName());
            donor.setDonorSituation(donorDTO.getDonorSituation());
            donor.setDonorCpf(donorDTO.getDonorCpf());
            donor.setDonorAddress(donorDTO.getDonorAddress());
            donor.setDonorCnpj(donorDTO.getDonorCnpj());
            donor.setDonorPhone(donorDTO.getDonorPhone());
            donor.setDonorEmail(donorDTO.getDonorEmail());

            Employee employee = employeeRepository.findById(donorDTO.getEmployeeId())
                    .orElseThrow(()-> new IllegalArgumentException("Employee not found!"));

            donor.setEmployee(employee);

            Donor donorUpdated = donorRepository.save(donor);
            return convertToDTO(donorUpdated);
        } else {
            throw new Exception("Donor not found!");
        }
    }

    public void deleteById(Long id) throws Exception {
        Optional<Donor> donorOptional = donorRepository.findById(id);
        if (donorOptional.isPresent()){
            donorRepository.deleteById(id);
        } else {
            throw new Exception("Donor not found!");
        }
    }

    public Donor convertToEntity(DonorDTO donorDTO) throws Exception {
        Donor donor = new Donor();
        donor.setDonorName(donorDTO.getDonorName());
        donor.setDonorAddress(donorDTO.getDonorAddress());
        donor.setDonorCnpj(donorDTO.getDonorCnpj());
        donor.setDonorCpf(donorDTO.getDonorCpf());
        donor.setDonorEmail(donorDTO.getDonorEmail());
        donor.setDonorSituation(donorDTO.getDonorSituation());
        donor.setDonorPhone(donorDTO.getDonorPhone());

        return donor;
    }

    public DonorDTO convertToDTO(Donor donor){
        DonorDTO donorDTO = new DonorDTO();
        donorDTO.setCodDonor(donor.getCodDonor());
        donorDTO.setDonorName(donor.getDonorName());
        donorDTO.setDonorAddress(donor.getDonorAddress());
        donorDTO.setDonorCpf(donor.getDonorCpf());
        donorDTO.setDonorEmail(donor.getDonorEmail());
        donorDTO.setDonorSituation(donor.getDonorSituation());
        donorDTO.setDonorPhone(donor.getDonorPhone());
        donorDTO.setDonorCnpj(donor.getDonorCnpj());

        // Check if employee exists
        Employee employee = donor.getEmployee();
        if (employee != null){
            donorDTO.setEmployeeId(employee.getCodEmployee());
        }

        return donorDTO;
    }
}
