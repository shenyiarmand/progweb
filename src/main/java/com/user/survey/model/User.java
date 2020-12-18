package com.user.survey.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name="USER")
@Data @NoArgsConstructor @AllArgsConstructor
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="PASSWORD",length=300, nullable=false)
	private String password;
	
	@Column(name="FIRST_NAME",length=50)
	private String firstName;
		
	@Column(name="LAST_NAME",length=50)
	private String lastName;
		
	@Column(name="EMAIL_ID",length=50, unique=true, nullable=false)
	private String emailId;
	
	@Column(name="MOBILE",length=50)
	private String mobile;	
	
	@JsonIgnore
	@Column(name="IS_DELETED", columnDefinition="tinyint(1) default 1")
	private boolean isDeleted = true;
	
	@JsonIgnore
	@Column(name="DATE_ADDED")
	private Date dateAdded;
	
	@JsonIgnore
	@Column(name="DATE_MODIFIED")
	private Date dateModified;	
}
