package com.example.project.springsec.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.project.springsec.Models.Role;
import com.example.project.springsec.Repositories.RoleRepository;

/*Esse componente implementa a interface CommandLineRunner
 Quando a aplicação rodar, ele verifica se os roles já existem 
 no banco de dados. Em caso negativo, os roles serão salvos na database*/

@Component
public class DataLoader implements CommandLineRunner {
	
	@Autowired
	private RoleRepository roleRepository;

	
	@Override
	public void run(String... args) throws Exception {

		String[] roles = {"ADMIN", "USER"};

		for (String roleString: roles) {
			Role role = roleRepository.findByRole(roleString);
			if (role == null) {
				role = new Role(roleString);
				roleRepository.save(role);
			}
		}

	}

}
