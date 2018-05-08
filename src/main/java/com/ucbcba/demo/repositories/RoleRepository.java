package com.ucbcba.demo.repositories;

import com.ucbcba.demo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
