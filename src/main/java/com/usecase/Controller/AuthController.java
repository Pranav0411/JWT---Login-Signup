package com.usecase.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usecase.exception.ApiExcception;
import com.usecase.model.Student;
import com.usecase.payload.JwtAuthRequest;
import com.usecase.payload.JwtAuthResponse;
import com.usecase.payload.StudentDataTransfer;
import com.usecase.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/student")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception
	{
		this.authenticate(request.getEmail(),request.getPassword());
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
		
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setUser(this.modelMapper.map((Student)userDetails, StudentDataTransfer.class));
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}

	private void authenticate(String email, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, password);
		
		try {
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			
		} catch (BadCredentialsException e) {
			
			System.out.println("Credentials are not correct");
			throw new Exception("Invalid details");
			// TODO: handle exception
		}
		
		
		
			
		
		// TODO Auto-generated method stub
		
	}

}
