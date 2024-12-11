package com.dvSystem.StockSystem.service;

import com.dvSystem.StockSystem.dto.StockDTO;
import com.dvSystem.StockSystem.model.Stock;
import com.dvSystem.StockSystem.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockDTO> list(){
        List<Stock> stocks = stockRepository.findAll();
        List<StockDTO> stockDTOS = new ArrayList<>();

        for (Stock stock: stocks){
            StockDTO stockDTO = convertToDTO(stock);
            stockDTOS.add(stockDTO);
        }

        return stockDTOS;
    }

    public Optional<StockDTO> findStockDTOByProductName(String productName){
        Optional<Stock> stockOptional = stockRepository.findByProductName(productName);
        if (stockOptional.isPresent()){
            StockDTO stockDTO = convertToDTO(stockOptional.get());
            return Optional.of(stockDTO);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Stock> findByProductName(String productName){
        return stockRepository.findByProductName(productName);
    }

    public Stock save(Stock stock){
        return stockRepository.save(stock);
    }

    private StockDTO convertToDTO(Stock stock){
        StockDTO stockDTO = new StockDTO();
        stockDTO.setCodStock(stock.getCodStock());
        stockDTO.setProductName(stock.getProductName());
        stockDTO.setProductAmount(stock.getProductAmount());
        stockDTO.setProductCod(stock.getProductCod());

        return stockDTO;
    }
}
