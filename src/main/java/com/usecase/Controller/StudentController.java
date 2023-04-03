package com.usecase.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usecase.Service.ServicesProvided;
import com.usecase.payload.StudentDataTransfer;

@RestController
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	private ServicesProvided servicesProvided;
	
	
	@PostMapping("/create")
	public ResponseEntity<StudentDataTransfer> createStudent(@RequestBody StudentDataTransfer stdo)
	{
		StudentDataTransfer studentDataTransfer =  this.servicesProvided.createStudent(stdo);
		return new ResponseEntity<>(studentDataTransfer,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasrole('ADMIN','STUDENT')")
	@PutMapping("/{id}")
	public ResponseEntity<StudentDataTransfer> updateStudent(@RequestBody StudentDataTransfer studentDataTransfer, @PathVariable long id)
	{
		StudentDataTransfer studentDataTransfer2 =  this.servicesProvided.updateStudent(studentDataTransfer, id);
		return ResponseEntity.ok(studentDataTransfer2);
	}
	
	@PreAuthorize("hasrole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable long id)
	{
		this.servicesProvided.deleteStudent(id);
		return new ResponseEntity<String>("Student Deletion Success", HttpStatus.OK);
	}
	
	
	@GetMapping("/getall")
	public ResponseEntity<List<StudentDataTransfer>> getAllStudents()
	{
		return ResponseEntity.ok(this.servicesProvided.getAllStudents());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StudentDataTransfer> getStudentbyId(@PathVariable long id)
	{
		return ResponseEntity.ok(this.servicesProvided.getStudentbyId(id));
	}

}
