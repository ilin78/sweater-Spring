package com.example.sweater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.sweater.domain.User;
import com.example.sweater.repos.UserRepo;

@Service // даем понять spring что это компонент Controller
public class UserService implements UserDetailsService {

	@Autowired
	private  UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Возвращаем пользователя
		return userRepo.findByUsername(username); 	
	}
}