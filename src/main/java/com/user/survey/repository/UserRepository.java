package com.user.survey.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.user.survey.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>
{
	public User findByEmailIdAndIsDeleted(String email, Boolean isDeleted);
}
