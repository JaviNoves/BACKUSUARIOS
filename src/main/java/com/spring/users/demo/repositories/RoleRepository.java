package com.spring.users.demo.repositories;


import com.spring.users.demo.models.entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
