package com.example.sweater.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sweater.repos.UserRepo;

@Controller
@RequestMapping ("/user")
public class UserController {
	@Autowired
	private UserRepo userRepo;
	
	@GetMapping
	public String userList() {
		return "userList";
	}
}