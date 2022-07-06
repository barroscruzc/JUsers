package com.example.project.springsec.Security;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.project.springsec.Models.Usuario;
import com.example.project.springsec.Repositories.UsuarioRepository;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	public Long id;

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("all", "upDel", "newUser", "add", "update", "delete", "newUser").hasAuthority("ROLE_ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/springsec/login").permitAll()
			.and()
		.logout()
			.permitAll();
		return http.build();
	}

	@SuppressWarnings("deprecation")
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		
		List<Usuario> usuarios = usuarioRepository.findAll();
		Usuario usuario;
		List<UserDetails> userDetailsList = new ArrayList<>();
		for (int i=0; i<usuarios.size(); i++) {
			usuario = usuarios.get(i);
				System.out.println(usuario.getEmail());
				userDetailsList.add(User
						.withDefaultPasswordEncoder()
						.username(usuario.getEmail().toString())
						.password(usuario.getSenha().toString())
						.roles(usuario.getRole().toString()).build());
		}
		return new InMemoryUserDetailsManager(userDetailsList);
	}

}