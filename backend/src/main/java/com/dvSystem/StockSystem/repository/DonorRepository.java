package com.dvSystem.StockSystem.repository;

import com.dvSystem.StockSystem.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonorRepository extends JpaRepository<Donor, Long> {
}
