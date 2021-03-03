package com.movieApp.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password) {
		return "Login Success "+email;
	
	}
	
	@PostMapping("/register")
	public UsersModel createUsers(@RequestBody UsersModel usersModel) {
		return usersService.createUsers(usersModel);
	}
	
}
