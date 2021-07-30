package br.com.netflix.inatel.projeto.controller.form;

import br.com.netflix.inatel.projeto.model.Usuario;

public class UsuarioFormUpdate {
	

	private String nome;

	private String biografia;

	public String getNome() {
		return nome;
	}

	public String getBiografia() {
		return biografia;
	}

	public Usuario update(Usuario user) {
		if (nome != null)
			if (nome.length() != 0)
				user.setNome(nome);

		if (biografia != null)
			if (biografia.length() != 0)
				user.setBiografia(biografia);

		return user;
	}
}

