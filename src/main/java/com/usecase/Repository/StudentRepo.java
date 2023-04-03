package com.usecase.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usecase.model.Student;

public interface StudentRepo extends JpaRepository<Student, Long>{
	
	Optional<Student> findBySemail(String semail);

}
