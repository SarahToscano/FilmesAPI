package br.com.netflix.inatel.projeto.controller.form;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.netflix.inatel.projeto.model.Usuario;

public class UsuarioForm {
	
	@NotNull @NotEmpty 
    private String nome;

    @NotNull @NotEmpty
    private String email;

    @NotNull @NotEmpty @Length(min=4)
    private String senha;
    
    @NotNull @NotEmpty
    private String biografia;
   
    public String getNome() {
        return nome;
    }
    
    public String getBiografia() {
		return biografia;
	}

	public String getEmail() {
        return email;
    }
    
    public String getSenha() {
        return senha;
    }

    public Usuario converter() {
    	String encryptedPassword = new BCryptPasswordEncoder().encode(senha);
    	return new Usuario(nome, email, encryptedPassword, biografia);
    }

}
