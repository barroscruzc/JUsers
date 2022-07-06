package com.example.project.springsec.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@SuppressWarnings("unused")
@Entity(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nome", unique=true)
	@Size(min = 2, max = 50, message = "O nome deve conter de 2 a 50 caracteres")
	private String nome;
	
	@Column(name="email", unique=true)
	private String email;
	
	@Column(name="senha")
	@Size(min = 3, max = 10, message="Senha deve conter de 3 a 10 caracteres")
	private String senha;
	
	@Column(name="role")
	@Size(min=4, max=5, message="Nível de acesso deve ser ROLE ou ADMIN")
	private String role;
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", role=" + role + "]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
