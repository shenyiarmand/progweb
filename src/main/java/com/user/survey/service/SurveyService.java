package com.user.survey.service;

import java.util.List;

import com.user.survey.model.Survey;

public interface SurveyService 
{
	public Survey saveSurvey(Survey survey, Integer userId);

	public Survey getSurvey(Integer id);

	public List<Survey> getSurveys();

	public Survey deleteSurvey(Integer id);

	public Survey updateSurvey(Integer id, Survey survey);
}
