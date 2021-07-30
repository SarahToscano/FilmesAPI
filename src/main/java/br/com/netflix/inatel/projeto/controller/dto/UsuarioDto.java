package br.com.netflix.inatel.projeto.controller.dto;

import java.util.List;
import java.util.stream.Collectors;


import br.com.netflix.inatel.projeto.model.Usuario;

public class UsuarioDto {

	private Long id;
	private String nome;
	private String email;
	private String biografia;
	
	public UsuarioDto(Usuario user) {
		this.id = user.getId();
		this.nome = user.getNome();
		this.email = user.getEmail();
		this.biografia = user.getBiografia();
	}
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
	

	public String getBiografia() {
		return biografia;
	}

	public static List<UsuarioDto> toUserDto(List<Usuario> friends) {
		return friends.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}
}
