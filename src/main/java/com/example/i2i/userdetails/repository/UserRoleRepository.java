package com.example.i2i.userdetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.i2i.userdetails.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

}
