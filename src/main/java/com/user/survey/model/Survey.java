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
@Table( name="SURVEY")
@Data @NoArgsConstructor @AllArgsConstructor
public class Survey 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="TITLE",length=50)
	private String title;
	
	@Column(name="DESCRIPTION",length=50)
	private String description;
	
	@Column(name="CITY",length=50)
	private String city;
	
	@Column(name="STATE",length=50)
	private String state;
	
	@Column(name="COUNTRY",length=50)
	private String country;
	
	@Column(name="ZIP",length=50)
	private String zip;	
	
	@Column(name="SURVEY_DATE",length=50)
	private String surveyDate;
	
	@JsonIgnore
	@Column(name="IS_DELETED")
	private boolean isDeleted;
	
	@JsonIgnore
	@Column(name="DATE_ADDED")
	private Date dateAdded;
	
	@Column(name="ADDED_BY")
	private Integer addedBy;
	
	@JsonIgnore
	@Column(name="DATE_MODIFIED")
	private Date dateModified;	
}
