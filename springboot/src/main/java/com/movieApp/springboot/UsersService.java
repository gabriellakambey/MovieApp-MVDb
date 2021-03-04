package com.movieApp.springboot;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
	private UsersRepository usersRepository;
	
	@Autowired
	public UsersService(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	public UsersModel createUsers(String name, String email, String password) {
		UsersModel userModel = new UsersModel();
		
		if (Objects.nonNull(usersRepository.getUsersModelByEmail(email))) {
			return null;
		} else {
			userModel.setUserName(name);
			userModel.setUserEmail(email);
			userModel.setUserPassword(password);
			
			return usersRepository.save(userModel);
		}
		
	}
	
	public Optional<UsersModel> getUser(int id){
		return usersRepository.findById(id);
	}
	
	public UsersModel getUsersByEmail(String email) {
		return usersRepository.getUsersModelByEmail(email);
	}
	
	public Boolean login(String email, String password) {
		UsersModel usersModel = usersRepository.getUsersModelByEmail(email);
		if (Objects.nonNull(usersModel)) {
			if(usersModel.getUserPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public Iterable<UsersModel> getAllUser(){
		return usersRepository.findAll();

//		return usersRepository.save(usersModel);
	}

}
