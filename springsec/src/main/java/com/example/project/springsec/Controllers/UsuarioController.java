package com.example.project.springsec.Controllers;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.project.springsec.Models.Role;
import com.example.project.springsec.Models.Usuario;
import com.example.project.springsec.Repositories.RoleRepository;
import com.example.project.springsec.Repositories.UsuarioRepository;

@SuppressWarnings("unused")
@Controller
@RequestMapping(path = "/springsec")
public class UsuarioController {

	//Com a anotação Autowired, comunica automaticamente com os repositories
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	@Autowired 
	private RoleRepository roleRepository;

	@GetMapping(path = "/home")
	public String menu() {
		return "home";
	}

	// Crud READ - buscar usuários no banco de dados (query 'select * from
	// usuarios')
	
	@RequestMapping(path = "/all")
	public String allUsers(Model model) {
		model.addAttribute("usuario", usuarioRepository.findAll());
		return "all";
	} 
	
	//Busca por nome do usuário - Apenas leitura
	@GetMapping(path = "/search")
	public String search() {
		return "search";
	}
	
	@PostMapping("/search")
	public String searchUser(@RequestParam("searchUser") String searchUser, Model model) {
		model.addAttribute("usuario", usuarioRepository.findByNomeContains(searchUser));
		return "search";
	}
	
	//Busca por nome do usuário - Com opcoes Deletar ou Editar
	@GetMapping(path = "/upDel")
	public String searchUpDel() {
		return "upDel";
	}
	
	@PostMapping("/upDel")
	public String UpDel(@RequestParam("searchUser") String searchUser, Model model) {
		model.addAttribute("usuario", usuarioRepository.findByNomeContains(searchUser));
		return "upDel";
	}
	

	// CRUD CREATE - criar usuário
	@GetMapping(path = "/newUser")
	public String register(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "newUser";
	}

	@PostMapping(path = "/save")
	public String add(Usuario usuario, Model model, RedirectAttributes attributes) {
		////verifica se já consta usuário cadastrado com os dados preenchidos no formulário de cadastro
		
		//Verificação por e-mail
		Usuario uEmail = usuarioRepository.findByEmail(usuario.getEmail());
		if (uEmail != null) {
			//mensagem de erro
			model.addAttribute("emailExiste", "E-mail já cadastrado!");
			//recarrega a página, exibindo a mensagem de erro
			return "/newUser";
		}

		//Verificação por nome
		Usuario uNome = usuarioRepository.findByNome(usuario.getNome());
		if (uNome != null) {
			//mensagem de erro
			model.addAttribute("nomeExiste", "Nome de usuário já cadastrado!");
			//recarrega a página, exibindo a mensagem de erro
			return "/newUser";
		} else {
			
		//busca os roles atribuídos ao usuário
			Role role = roleRepository.findByRole("USER");
			List<Role> roles = new ArrayList<Role>();
			roles.add(role);
			usuario.setRoles(roles); //associa o role USER ao usuário
			
			
		// salva o novo usuario no banco de dados
		usuarioRepository.save(usuario);
		return "redirect:/springsec/all";
		}
	}

	// CRUD UPDATE - Editar usuário
	@GetMapping(path="/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("usuario", usuarioRepository.findById(id).get());
		return "updateForm";
	}
	
	@PostMapping(path = "/update")
	public String update(@RequestParam Integer id, @RequestParam String nome, @RequestParam String email,
			@RequestParam String senha) {
		Usuario user = usuarioRepository.findById(id).get();
		if (!nome.isEmpty()) {
			user.setNome(nome);
		}
		if (!email.isEmpty()) {
			user.setEmail(email);
		}
		if (!senha.isEmpty()) {
			user.setSenha(senha);
		}

		usuarioRepository.save(user);
		return "redirect:/springsec/all";
	}

	// CRUD DELETE - Excluir usuário
	@RequestMapping(path = "/delete/{id}")
	public String delete(@PathVariable int id) {
		usuarioRepository.deleteById(id);
		return "redirect:/springsec/all";
	}
}
