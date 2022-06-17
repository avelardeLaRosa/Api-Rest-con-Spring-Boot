package com.autodidacta.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.autodidacta.seguridad.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		//las peticiones get sera permitido para cualquiera
		.authorizeRequests().antMatchers(HttpMethod.GET,"/vlexApi/**")
		.permitAll()
		//cualquiera otra peticion
		.anyRequest()
		//debe ser autenticada
		.authenticated()
		.and()
		//sera una autenticacion basica
		.httpBasic();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		super.configure(auth);
	}
	
	
	
	/*
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		// Se construira un usuario
		UserDetails alexander = User.builder() //construir
						.username("alexander")//usuario
						.password(passwordEncoder().encode("password")) //psw encodificado
						.roles("USER").build(); //rol de usuario otorgado
		
		UserDetails admin = User.builder() //construir
				.username("admin")//usuario
				.password(passwordEncoder().encode("admin")) //psw encodificado
				.roles("ADMIN").build(); //rol de usuario otorgado
		
		//SE GUARDARA ESTOS USUARIOS EN MEMORIA
		
		return new InMemoryUserDetailsManager(alexander,admin);
	}*/
	
	
	
	
}
