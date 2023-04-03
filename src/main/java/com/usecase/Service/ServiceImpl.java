package com.usecase.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.usecase.exception.*;
import com.usecase.Repository.Rolerepo;
import com.usecase.Repository.StudentRepo;
import com.usecase.model.Student;
import com.usecase.payload.StudentDataTransfer;

import jakarta.persistence.Access;
@Service
public class ServiceImpl implements ServicesProvided {
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	private StudentRepo studentRepo;
	
	
	

	public ServiceImpl(StudentRepo studentRepo) {
		super();
		this.studentRepo = studentRepo;
	}

	@Override
	public StudentDataTransfer createStudent(StudentDataTransfer st) {
		
		Student student = this.dtoStudent(st);
		Student savedstudent = this.studentRepo.save(student);
		// TODO Auto-generated method stub
		return this.SttoDto(savedstudent);
	}

	@Override
	public StudentDataTransfer updateStudent(StudentDataTransfer stdo, long id) {
		
		Student student = this.studentRepo.findById(id).orElseThrow(()->new Exceptionss("Student" , "Studentid" , id));
		// TODO Auto-generated method stub
		student.setSname(stdo.getSname());
		student.setSclass(stdo.getSclass());
		student.setSsection(stdo.getSsection());
		student.setSrollno(stdo.getRollno());
		
		Student updateStudent = this.studentRepo.save(student);
		StudentDataTransfer stdo1 =  this.SttoDto(updateStudent);
		
		return stdo1;
	}

	@Override
	public StudentDataTransfer getStudentbyId(long id) {
		
		Student student = this.studentRepo.findById(id).orElseThrow(()->new Exceptionss("Student" , "Studentid" , id));
		// TODO Auto-generated method stub
		return this.SttoDto(student);
	}

	@Override
	public List<StudentDataTransfer> getAllStudents() {
		
		List<Student> l = this.studentRepo.findAll();
		List<StudentDataTransfer> stdo =  l.stream().map(student->this.SttoDto(student)).collect(Collectors.toList());
		// TODO Auto-generated method stub
		return stdo;
	}

	@Override
	public void deleteStudent(long id) {
		Student student = this.studentRepo.findById(id).orElseThrow(()->new Exceptionss("Student" , "Studentid" , id));
		this.studentRepo.delete(student);
		// TODO Auto-generated method stub

	}
	
	private Student dtoStudent(StudentDataTransfer studentDataTransfer)
	{
		Student st  = new Student();
		st.setId(studentDataTransfer.getId());
		st.setSname(studentDataTransfer.getSname());
		st.setSclass(studentDataTransfer.getSclass());
		st.setSsection(studentDataTransfer.getSsection());
		st.setSrollno(studentDataTransfer.getRollno());
		st.setSemail(studentDataTransfer.getSemail());
		st.setSpass(passwordEncoder.encode(studentDataTransfer.getSpass()));
		st.setScpass(passwordEncoder.encode(studentDataTransfer.getScpass()));
		return st;
		
	}
	
	public StudentDataTransfer SttoDto(Student student)
	{
		StudentDataTransfer stdo = new StudentDataTransfer();	
		stdo.setId(student.getId());
		stdo.setSname(student.getSname());
		stdo.setSclass(student.getSclass());
		stdo.setSsection(student.getSsection());
		stdo.setRollno(student.getSrollno());
		stdo.setSemail(student.getSemail());
		stdo.setSpass(passwordEncoder.encode(student.getSpass()));
		stdo.setScpass(passwordEncoder.encode(student.getScpass()));
		return stdo;
	
	}

	

	

}
