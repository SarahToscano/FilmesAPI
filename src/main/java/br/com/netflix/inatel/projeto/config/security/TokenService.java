package br.com.netflix.inatel.projeto.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import br.com.netflix.inatel.projeto.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class TokenService {

	@Value ("${inatel.projeto.expiration}")
	private String expiration;
	
	@Value ("${inatel.projeto.expiration}")
	private String secret;
	
	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExp = new Date(hoje.getTime()+ Long.parseLong(expiration));
				
		return Jwts.builder().
			setIssuer("Inatel-Projeto")
			.setSubject(logado.getId().toString())
			.setIssuedAt(hoje)
			.setExpiration(dataExp)
			.signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secret)
			.compact();
	}

	public  boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}	
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
