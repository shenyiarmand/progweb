package com.user.survey.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.survey.constant.KeyConstant;
import com.user.survey.model.ServiceResponse;
import com.user.survey.model.Survey;
import com.user.survey.service.PrincipalService;
import com.user.survey.service.SurveyService;

@RestController
@RequestMapping(value = "/survey", produces = "application/hal+json")
public class SurveyController
{
	@Autowired
	private SurveyService surveyService;
	
	@Autowired
	private PrincipalService principalService;
	
//-----------------------------------------------------------------------------------SAVE SURVEY INFORMATION------------------------------
	
	@PostMapping()
	public ResponseEntity<Object> saveSurvey(@RequestBody Survey survey, Principal principl)
	{	
		Survey saveSurvey = surveyService.saveSurvey(survey, principalService.getUserId(principl.getName()));
		ServiceResponse<Survey> response = new ServiceResponse<Survey>(KeyConstant.STATUS_SUCCESS, saveSurvey);
		return new ResponseEntity<Object>(response, HttpStatus.CREATED);
	}
	
//-----------------------------------------------------------------------------------GET SURVEY INFORMATION------------------------------
	
	@GetMapping("/{id}")
	public Survey getSurvey(@PathVariable("id") Integer id)
	{	
		return surveyService.getSurvey(id);
	}
	
//-----------------------------------------------------------------------------------GET ALL SURVEYS INFORMATIONS-------------------------

	@GetMapping()
	public ResponseEntity<Object> getSurveys()
	{	
		ServiceResponse<List<Survey>> response = new ServiceResponse<List<Survey>>(KeyConstant.STATUS_SUCCESS, surveyService.getSurveys());
		return new ResponseEntity<Object>(response, HttpStatus.OK);		
	}
	
//-----------------------------------------------------------------------------------UPDATE SURVEY INFORMATION------------------------------

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateSurvey(@PathVariable("id") Integer id, @RequestBody Survey survey)
	{	
		Survey saveSurvey = surveyService.updateSurvey(id, survey);
		ServiceResponse<Survey> response = new ServiceResponse<Survey>(KeyConstant.STATUS_SUCCESS, saveSurvey);
		return new ResponseEntity<Object>(response, HttpStatus.NO_CONTENT);
	}
	
//-----------------------------------------------------------------------------------DELETE SURVEY INFORMATION------------------------------

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteSurvey(@PathVariable("id") Integer id)
	{		
		Survey survey = surveyService.deleteSurvey(id);
		ServiceResponse<String> response = new ServiceResponse<String>(KeyConstant.STATUS_SUCCESS, survey.getTitle());
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}	
	
//-----------------------------------------------------------------------------------END---------------------------------------------------
	
}
