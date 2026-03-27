package com.inventory.detail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.detail.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long>{

}
