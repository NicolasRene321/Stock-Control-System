package com.dvSystem.StockSystem.repository;

import com.dvSystem.StockSystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
