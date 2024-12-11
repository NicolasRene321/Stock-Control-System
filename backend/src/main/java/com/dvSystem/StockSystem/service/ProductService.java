package com.dvSystem.StockSystem.service;

import com.dvSystem.StockSystem.dto.ProductDTO;
import com.dvSystem.StockSystem.model.*;
import com.dvSystem.StockSystem.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final DonorRepository donorRepository;
    private final StockService stockService;
    private final ManufacturerRepository manufacturerRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, DonorRepository donorRepository,
                          StockService stockService, ManufacturerRepository manufacturerRepository,
                          EmployeeRepository employeeRepository) {
        this.productRepository = productRepository;
        this.donorRepository = donorRepository;
        this.stockService = stockService;
        this.manufacturerRepository = manufacturerRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public ProductDTO register(ProductDTO productDTO){
        Product product = convertToEntity(productDTO);

        // Check if donor exists
        Long codDonor = productDTO.getDonorId();
        if (codDonor != null){
            Donor donor = donorRepository.findById(codDonor)
                    .orElseThrow(()-> new IllegalArgumentException("Donor not found!"));
            product.setDonor(donor);
        }

        // Check if manufacturer exists
        Long codManufacturer = productDTO.getManufacturerId();
        if (codManufacturer != null){
            Manufacturer manufacturer = manufacturerRepository.findById(codManufacturer)
                    .orElseThrow(()-> new IllegalArgumentException("Manufacturer not found!"));
            product.setManufacturer(manufacturer);
        }

        //Check if employee exists
        Long codEmployee = productDTO.getEmployeeId();
        if (codEmployee != null){
            Employee employee = employeeRepository.findById(codEmployee)
                    .orElseThrow(()-> new IllegalArgumentException("Employee not found!"));
            product.setEmployee(employee);
        }

        Product productSaved = productRepository.save(product);

        // Check if product exists is in stock using the name
        Optional<Stock> stockOptional = stockService.findByProductName(productSaved.getProductName());
        if (stockOptional.isPresent()){
            Stock stock = stockOptional.get();
            stock.setProductAmount(stock.getProductAmount() + productSaved.getProductAmount());
            stockService.save(stock); // The stock service needs a method to save the stock

            // Update the product with the stock id
            productSaved.setStock(stock);
        } else {
            // Create a new product in the stock
            Stock newStock = new Stock();
            newStock.setProductCod(productSaved.getCodProduct());
            newStock.setProductName(productSaved.getProductName());
            newStock.setProductAmount(productSaved.getProductAmount());
            Stock stockSaved = stockService.save(newStock);

            // Update the product with stock the id
            productSaved.setStock(stockSaved);
        }

        // Save again the product to update the stock ID
        productSaved = productRepository.save(productSaved);

        return convertToDTO(productSaved);
    }

    public List<ProductDTO> list(){
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product: products){
            ProductDTO productDTO = convertToDTO(product);
            productDTOS.add(productDTO);
        }

        return productDTOS;
    }

    public Product convertToEntity(ProductDTO productDTO){
        Product product = new Product();
        product.setProductDescription(productDTO.getProductDescription());
        product.setProductName(productDTO.getProductName());
        product.setProductAmount(productDTO.getProductAmount());
        product.setProductExpiration(productDTO.getProductExpiration());
        product.setProductPrice(productDTO.getProductPrice());

        return product;
    }

    public ProductDTO convertToDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductAmount(product.getProductAmount());
        productDTO.setProductExpiration(product.getProductExpiration());
        productDTO.setProductPrice(product.getProductPrice());

        // Check if donor exists
        if(product.getDonor() != null){
            productDTO.setDonorId(product.getDonor().getCodDonor());
        }

        // Check if stock exists
        if (product.getStock() != null){
            productDTO.setStockId(product.getStock().getCodStock());
        }

        //Check if manufacturer exists
        if (product.getManufacturer() != null){
            productDTO.setManufacturerId(product.getManufacturer().getCodManufacturer());
        }

        // Check if employee exists
        if (product.getEmployee() != null){
            productDTO.setEmployeeId(product.getEmployee().getCodEmployee());
        }

        return productDTO;
    }

}
