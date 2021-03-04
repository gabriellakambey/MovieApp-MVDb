package com.movieApp.springboot;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
	
	@Autowired
	private UsersService usersService;
	
	
	@PostMapping("/register")
	public GetUserModel createUsers(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
		GetUserModel getUserModelist = new GetUserModel();
		UsersModel usersModel = usersService.createUsers(name, email, password);
		if (Objects.nonNull(usersModel)) {
			getUserModelist.setStatus("Success");
			getUserModelist.setUserModels(usersModel);
			return getUserModelist;
		} else {
			getUserModelist.setStatus("Failed");
			return getUserModelist;
		}
		//		return usersService.createUsers(usersModel);
	}
	
	@GetMapping("/user/{email}")
	public UsersModel getUsersByEmail(@PathVariable String email) {
		return usersService.getUsersByEmail(email);
	}
	
	@GetMapping("/login")
	public GetUserModel login(@RequestParam String email, @RequestParam String password) {
		GetUserModel getUserModelist = new GetUserModel();
		if (usersService.login(email, password)) {
			getUserModelist.setStatus("Success");
			getUserModelist.setUserModels(getUsersByEmail(email));
			return getUserModelist;
		} else {
			getUserModelist.setStatus("Failed");
			return getUserModelist;
		}
		
//		return "Login Success "+email;
	
	}
	
	@GetMapping("/user/all")
	public Iterable<UsersModel> getAllUser(){
		return usersService.getAllUser();
	}
	
}
