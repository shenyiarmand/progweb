package com.user.survey.dao;

import java.util.List;

import com.user.survey.model.Survey;

public interface SurveyDao 
{
	public Survey saveSurvey(Survey survey);

	public Survey getSurvey(Integer id);

	public List<Survey> getSurveys();
	
	public boolean isExist(Integer id);
}
