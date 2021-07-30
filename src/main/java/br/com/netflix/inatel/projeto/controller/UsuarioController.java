package br.com.netflix.inatel.projeto.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.netflix.inatel.projeto.controller.dto.UsuarioDto;
import br.com.netflix.inatel.projeto.controller.form.UsuarioForm;
import br.com.netflix.inatel.projeto.controller.form.UsuarioFormUpdate;
import br.com.netflix.inatel.projeto.model.Usuario;
import br.com.netflix.inatel.projeto.repository.UsuarioRepository;
import java.net.URI;
import java.util.Optional;



@RestController
@RequestMapping("/perfil")
public class UsuarioController {
	
	@Autowired
    private UsuarioRepository usuarioRepository;

	@PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> create(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
    	Usuario user = form.converter();
    	usuarioRepository.save(user);
    	
    	URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
    	return ResponseEntity.created(uri).body(new UsuarioDto(user));
    }
	
	@PutMapping()
	@Transactional
	public ResponseEntity<UsuarioDto> update(Authentication authentication, @RequestBody @Valid UsuarioFormUpdate form) {
		Usuario authenticatedUser = (Usuario) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();

		Optional<Usuario> optionalUser = usuarioRepository.findById(authenticatedUserId);

		if (optionalUser.isPresent()) {
			Usuario updatedUser = form.update(optionalUser.get());
			return ResponseEntity.ok(new UsuarioDto(updatedUser));
		}

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping()
	@Transactional
	public ResponseEntity<?> delete(Authentication authentication) {
		Usuario authenticatedUser = (Usuario) authentication.getPrincipal();
		Long authenticatedUserId = authenticatedUser.getId();
		System.out.println(authenticatedUserId);

		Optional<Usuario> optionalUser = usuarioRepository.findById(authenticatedUserId);

		if (optionalUser.isPresent()) {
			usuarioRepository.deleteById(authenticatedUserId);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> listar(@PathVariable("id") Long id) {
		Optional<Usuario> optionalUser = usuarioRepository.findById(id);
		if (optionalUser.isPresent()) {
			return ResponseEntity.ok(new UsuarioDto(optionalUser.get()));
		}
		return ResponseEntity.notFound().build();
	}
}
