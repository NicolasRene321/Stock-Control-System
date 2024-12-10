package com.dvSystem.StockSystem.repository;

import com.dvSystem.StockSystem.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByProductName(String productName);
}
