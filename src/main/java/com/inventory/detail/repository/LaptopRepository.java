package com.inventory.detail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.detail.model.Laptop;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {

}
