package br.com.netflix.inatel.projeto.config.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;


@RestControllerAdvice 
public class ErroDeValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroFormAvaliacaoDTO> handle(MethodArgumentNotValidException exception) {
		
		List <ErroFormAvaliacaoDTO> dto = new ArrayList<>();
		List <FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(e->{
			String mensagemErro = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroFormAvaliacaoDTO erro = new ErroFormAvaliacaoDTO(e.getField(), mensagemErro);
			                                               //Campo que deu erro, mensagem
			dto.add(erro);
		});
		
		return dto;
	}

}
