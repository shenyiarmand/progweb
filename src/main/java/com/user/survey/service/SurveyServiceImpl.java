package com.user.survey.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.survey.dao.SurveyDao;
import com.user.survey.exception.ResourceNotFoundException;
import com.user.survey.model.Survey;

@Service
public class SurveyServiceImpl implements SurveyService
{
	@Autowired
	private SurveyDao surveyDao;
	
	@Override
	public Survey saveSurvey(Survey survey, Integer userId)
	{
		survey.setAddedBy(userId);
		
		survey.setDeleted(Boolean.FALSE);
		survey.setDateAdded(new Date());
		survey.setDateModified(new Date());
		
		return surveyDao.saveSurvey(survey);
	}

	@Override
	public Survey getSurvey(Integer id)
	{
		return surveyDao.getSurvey(id);
	}

	@Override
	public List<Survey> getSurveys()
	{
		return surveyDao.getSurveys();
	}

	@Override
	public Survey deleteSurvey(Integer id)
	{
		Survey survey = surveyDao.getSurvey(id);
		
		survey.setDeleted(Boolean.TRUE);
		survey.setDateModified(new Date());
		
		return surveyDao.saveSurvey(survey);
	}

	@Override
	public Survey updateSurvey(Integer id, Survey survey)
	{
		Survey oldSurvey = surveyDao.getSurvey(id);
		
		oldSurvey.setTitle(survey.getTitle());
		oldSurvey.setDescription(survey.getDescription());
		
		oldSurvey.setCity(survey.getCity());
		oldSurvey.setState(survey.getState());
		oldSurvey.setCountry(survey.getCountry());
		oldSurvey.setZip(survey.getZip());
		
		oldSurvey.setSurveyDate(survey.getSurveyDate());
		oldSurvey.setDateModified(new Date());
		
		return surveyDao.saveSurvey(oldSurvey);
	}

}
