package com.movieApp.springboot;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UsersModel, Integer>{
	UsersModel getUsersModelByEmail(String email);
}
