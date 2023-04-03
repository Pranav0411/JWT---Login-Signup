package com.usecase.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usecase.model.Role;

public interface Rolerepo extends JpaRepository<Role, Long> {

}
