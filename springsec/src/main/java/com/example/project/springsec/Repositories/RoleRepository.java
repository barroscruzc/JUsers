package com.example.project.springsec.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.springsec.Models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRole(String role);
}
