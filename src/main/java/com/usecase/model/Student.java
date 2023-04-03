package com.usecase.model;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name="student")
@Entity
public class Student implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@Column(name = "name", nullable = false)
	private String sname;
	
	@Column(name = "class")
	private String sclass;
	@Column(name="section")
	private String ssection;
	@Column(name="rollno")
	private long srollno;
	@Column(name = "email", nullable = false)
	private String semail;
	@Column(name="password", nullable = false)
	private String spass;
	@Column(name="fpassword", nullable = false)
	private String scpass;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSclass() {
		return sclass;
	}
	public void setSclass(String sclass) {
		this.sclass = sclass;
	}
	public String getSsection() {
		return ssection;
	}
	public void setSsection(String ssection) {
		this.ssection = ssection;
	}
	public long getSrollno() {
		return srollno;
	}
	public void setSrollno(long srollno) {
		this.srollno = srollno;
	}
	public String getSemail() {
		return semail;
	}
	public void setSemail(String semail) {
		this.semail = semail;
	}
	public String getSpass() {
		return spass;
	}
	public void setSpass(String spass) {
		
		this.spass = spass;
	}
	public String getScpass() {
		return scpass;
	}
	public void setScpass(String scpass) {
		this.scpass = scpass;
	}
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name="user_role", joinColumns = @JoinColumn(name="user" , referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="role" , referencedColumnName = "id"))
	private Set<Role> roles=new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<SimpleGrantedAuthority> authorities =  this.roles.stream().map((role)-> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		// TODO Auto-generated method stub
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return spass;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return semail;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
