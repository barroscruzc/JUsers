package com.example.project.springsec.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@SuppressWarnings("unused")
@Entity(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nome", nullable = false, length=50, unique=true)
	@Size(min = 2, max = 50, message = "O nome deve conter de 2 a 50 caracteres")
	private String nome;
	
	@Column(name="email", unique=true, nullable = false, length=50)
	private String email;
	
	@Column(name="senha", nullable = false, length=10)
	@Size(min = 3, max = 10, message="Senha deve conter de 3 a 10 caracteres")
	private String senha;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuario_role", 
				joinColumns = @JoinColumn(name = "usuario_id"), 
				inverseJoinColumns = @JoinColumn(name="role_id"))
	private List<Role> roles;

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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
