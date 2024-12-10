package com.dvSystem.StockSystem.repository;

import com.dvSystem.StockSystem.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
