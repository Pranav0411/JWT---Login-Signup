package com.usecase.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.usecase.Repository.StudentRepo;
import com.usecase.exception.Exceptionss;
import com.usecase.model.Student;


@Component
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private StudentRepo studentRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Student student = this.studentRepo.findBySemail(username).orElseThrow(()-> new Exceptionss("Student","email"+username,0));
		return student;
	}
	
	
	

}
