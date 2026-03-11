package com.inventory.detail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.detail.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
