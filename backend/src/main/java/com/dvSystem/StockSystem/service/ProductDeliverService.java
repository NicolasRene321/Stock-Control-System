package com.dvSystem.StockSystem.service;

import com.dvSystem.StockSystem.dto.EmployeeDTO;
import com.dvSystem.StockSystem.dto.ProductDeliverDTO;
import com.dvSystem.StockSystem.dto.StockDTO;
import com.dvSystem.StockSystem.model.Employee;
import com.dvSystem.StockSystem.model.ProductDeliver;
import com.dvSystem.StockSystem.model.Stock;
import com.dvSystem.StockSystem.repository.EmployeeRepository;
import com.dvSystem.StockSystem.repository.ProductDeliverRepository;
import com.dvSystem.StockSystem.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDeliverService {
    private final ProductDeliverRepository productDeliverRepository;
    private final StockRepository stockRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ProductDeliverService(ProductDeliverRepository productDeliverRepository, StockRepository stockRepository, EmployeeRepository employeeRepository) {
        this.productDeliverRepository = productDeliverRepository;
        this.stockRepository = stockRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public ProductDeliverDTO register(ProductDeliverDTO productDeliverDTO){
        // Verification of the employee
        Employee employee = employeeRepository.findById(productDeliverDTO.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Code employee is mandatory!"));

        // Verification of the stock
        Stock stock = stockRepository.findById(productDeliverDTO.getStockId())
                .orElseThrow(()-> new IllegalArgumentException("Code stock is mandatory!"));

        // Check whether the amount to be delivered is available in stock
        if (stock.getProductAmount() < productDeliverDTO.getDeliverAmount()){
            throw new IllegalArgumentException("Desired amount is unavailable in stock");
        }

        // Update amount in stock
        stock.setProductAmount(stock.getProductAmount() - productDeliverDTO.getDeliverAmount());
        stockRepository.save(stock);

        // Register delivery
        ProductDeliver productDeliver = convertToEntity(productDeliverDTO, employee, stock);
        ProductDeliver deliverSaved = productDeliverRepository.save(productDeliver);

        return convertToDTO(deliverSaved);
    }

    public List<ProductDeliverDTO> list(){
        List<ProductDeliver> productDelivers = productDeliverRepository.findAll();
        List<ProductDeliverDTO> productDeliverDTOS = new ArrayList<>();

        for (ProductDeliver productDeliver: productDelivers){
            ProductDeliverDTO productDeliverDTO = convertToDTO(productDeliver);
            productDeliverDTOS.add(productDeliverDTO);
        }

        return productDeliverDTOS;
    }

    public Optional<ProductDeliverDTO> findById(Long id){
        Optional<ProductDeliver> productDeliverOptional = productDeliverRepository.findById(id);

        if (productDeliverOptional.isPresent()){
            return Optional.of(convertToDTO(productDeliverOptional.get()));
        } else {
            return Optional.empty();
        }
    }

    private ProductDeliver convertToEntity(ProductDeliverDTO productDeliverDTO, Employee employee,
                                           Stock stock){
        ProductDeliver productDeliver = new ProductDeliver();

        productDeliver.setProductDeliverName(productDeliver.getProductDeliverName());
        productDeliver.setDeliverResponsible(productDeliverDTO.getDeliverResponsible());
        productDeliver.setDeliverAmount(productDeliverDTO.getDeliverAmount());
        productDeliver.setDeliverDate(productDeliverDTO.getDeliverDate());
        productDeliver.setEmployee(employee);
        productDeliver.setStock(stock);

        return productDeliver;
    }

    private ProductDeliverDTO convertToDTO(ProductDeliver productDeliver){
        ProductDeliverDTO productDeliverDTO = new ProductDeliverDTO();

        productDeliverDTO.setCodDeliver(productDeliver.getCodDeliver());
        productDeliverDTO.setProductDeliverName(productDeliver.getProductDeliverName());
        productDeliverDTO.setDeliverResponsible(productDeliver.getDeliverResponsible());
        productDeliverDTO.setDeliverDate(productDeliver.getDeliverDate());
        productDeliverDTO.setDeliverAmount(productDeliver.getDeliverAmount());
        productDeliverDTO.setEmployeeId(productDeliver.getEmployee().getCodEmployee());
        productDeliverDTO.setStockId(productDeliver.getStock().getCodStock());

        return productDeliverDTO;
    }
}
