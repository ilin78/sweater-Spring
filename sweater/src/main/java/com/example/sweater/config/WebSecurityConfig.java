package com.example.sweater.config;
    
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration							//при старте класс конфигурирует WebSecurityConfigurerAdapter
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/registration").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
            .and()
                .logout()
                .permitAll();
    }

    // Здесь был метод UserDetailsService
    // Мы теперь хотим использовать пользователей из базы данных
    // для этого переопределяем  новый метод
      
        
    @Override  
    protected void configure(AuthenticationManagerBuilder auth) throws  Exception {
    	auth.jdbcAuthentication()
    	.dataSource(dataSource)		// dataSource - необходим чтобы менеджер мог входить в базу данных и искать пользователей и их roly
    	.passwordEncoder(NoOpPasswordEncoder.getInstance())	// passwordEncoder(NoOpPasswordEncoder.getInstance()) - нужен чтобы нам шифровал пароли
    	.usersByUsernameQuery("select username, password, active from usr where username=?")
    	// .authoritiesByUsernameQuery - usr и присоединенной user_role выбираем поля u.username, ur.roles 
    	.authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?");
    		
    }


}









