package vahenrique.ms.loja.catalogo.api.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import vahenrique.ms.loja.catalogo.api.model.ErroDto;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ErroDto> erros = ex.getBindingResult().getAllErrors().stream()
				.map(error -> new ErroDto(((FieldError) error).getField(),
						messageSource.getMessage(error, LocaleContextHolder.getLocale())))
				.collect(Collectors.toList());

		return handleExceptionInternal(ex, erros, headers, status, request);
	}
}
