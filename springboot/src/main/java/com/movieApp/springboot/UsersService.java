package com.movieApp.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
	private UsersRepository usersRepository;
	
	@Autowired
	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	public UsersModel createUsers(UsersModel usersModel) {
		return usersRepository.save(usersModel);
	}

}
