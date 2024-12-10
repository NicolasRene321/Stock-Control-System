package com.dvSystem.StockSystem.repository;

import com.dvSystem.StockSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
