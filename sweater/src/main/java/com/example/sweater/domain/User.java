package com.example.sweater.domain;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "usr") // создаем новую таблицу для USER - авторизация
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	private String password;
	private boolean active;
	
	
	
	
	/*	
	 * Role - создаем поле для формирования Enum
	 * fetch - как данные значения будут подгружаться относительно основной сущности
	 * EAGER - при запуске будет загружать все его данные(удобен когда мало пользователей) ускоряет работу и 
	 * потребляет больше ресурсов ("Жадный" способ)
	 * LAZY -  лучше использовать козда много пользователей,выполяется при конкретном запросе("Ленивый" способ)
	 * */
	
	
	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) 
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id")) // соединение user_role с user_id
	@Enumerated(EnumType.STRING)													  // Enum храним в виде строки
 	private Set<Role> roles;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}






