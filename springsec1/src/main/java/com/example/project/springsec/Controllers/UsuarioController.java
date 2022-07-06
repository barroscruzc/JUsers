package com.example.project.springsec.Controllers;


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

import com.example.project.springsec.Models.Usuario;
import com.example.project.springsec.Repositories.UsuarioRepository;

@SuppressWarnings("unused")
@Controller
@RequestMapping(path = "/springsec")
public class UsuarioController {

	@Autowired // Comunica com UsuarioRepository
	private UsuarioRepository usuarioRepository;

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

	@PostMapping(path = "/add")
	public String add(@RequestParam String nome, @RequestParam String email, @RequestParam String senha,
			@RequestParam String role) {
		// encapsulamento dos dados
		Usuario u = new Usuario();
		u.setNome(nome);
		u.setEmail(email);
		u.setRole(role);
		u.setSenha(senha);

		// salva o novo usuario no banco de dados
		usuarioRepository.save(u);

		return "redirect:/springsec/all";
	}

	// CRUD UPDATE - Editar usuário
	@GetMapping(path="/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("usuario", usuarioRepository.findById(id).get());
		return "updateForm";
	}
	
	@PostMapping(path = "/update")
	public String update(@RequestParam Integer id, @RequestParam String nome, @RequestParam String email,
			@RequestParam String senha, @RequestParam String role) {
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
		if (!role.isEmpty()) {
			user.setRole(role);
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
