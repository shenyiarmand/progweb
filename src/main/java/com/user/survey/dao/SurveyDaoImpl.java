package com.user.survey.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.survey.exception.ResourceNotFoundException;
import com.user.survey.model.Survey;
import com.user.survey.repository.SurveyRepository;

@Service
public class SurveyDaoImpl implements SurveyDao
{
	@Autowired
	private SurveyRepository surveyRepository;
	
	@Override
	public Survey saveSurvey(Survey survey)
	{
		return surveyRepository.save(survey);
	}

	@Override
	public Survey getSurvey(Integer id) 
	{
		return surveyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Survey", "id", id));
	}

	@Override
	public List<Survey> getSurveys()
	{
		return surveyRepository.findByIsDeletedOrderByIdDesc(Boolean.FALSE);
	}

	@Override
	public boolean isExist(Integer id)
	{
		return surveyRepository.existsById(id);
	}
}
