package com.user.survey.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.user.survey.model.Survey;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, Integer>
{
	public List<Survey> findByIsDeletedOrderByIdDesc(Boolean isDeleted);
}
