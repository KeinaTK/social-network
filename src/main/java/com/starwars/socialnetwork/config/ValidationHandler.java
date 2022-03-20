package com.starwars.socialnetwork.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.starwars.socialnetwork.dto.FormErrorDTO;
import com.starwars.socialnetwork.dto.NegociacaoErrorDTO;
import com.starwars.socialnetwork.exception.NegociacaoInvalidaException;
import com.starwars.socialnetwork.exception.NoContentException;

@RestControllerAdvice
public class ValidationHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FormErrorDTO> handle(MethodArgumentNotValidException exception) {
		List<FormErrorDTO> listaErros = new ArrayList<>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			listaErros.add(new FormErrorDTO(e.getField(), mensagem));
		});
		
		return listaErros;
		
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoContentException.class)
	public ResponseEntity<?> handle() {
		return ResponseEntity.notFound().build();
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NegociacaoInvalidaException.class)
	public ResponseEntity<?> handle(NegociacaoInvalidaException ex) {
		return ResponseEntity.badRequest().body(new NegociacaoErrorDTO(ex.getMessage()));
	}
}
