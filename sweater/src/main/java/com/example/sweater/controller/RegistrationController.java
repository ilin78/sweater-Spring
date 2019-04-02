package com.example.sweater.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.sweater.domain.Role;
import com.example.sweater.domain.User;
import com.example.sweater.repos.UserRepo;

@Controller
public class RegistrationController {
	@Autowired
	private UserRepo userRepo;
	
	
	
	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}
	
	// При успешной реистации - отправляем пользователя на страницу login
	// Если пользователь userFromDb не равен null то сообщаем об этом на экране 
	@PostMapping("/registration")	
	public String addUser(User user, Map<String, Object> model) {
		User userFromDb = userRepo.findByUsername(user.getUsername());
		
		if (userFromDb != null) {
			model.put("message", "User exists!"); // (если такой usr уже есть мы сообщаем об этом на странице регистрации)
			return "registration"; // 
		}
		
		// обработаем если нет такого User
		user.setActive(true);
		user.setRoles(Collections.singleton(Role.USER));
		userRepo.save(user);
		return "redirect:/login";
	}
}
