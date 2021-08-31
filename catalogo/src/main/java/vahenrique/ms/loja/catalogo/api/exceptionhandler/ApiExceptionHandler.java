package vahenrique.ms.loja.catalogo.api.exceptionhandler;

import java.time.OffsetDateTime;
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
import vahenrique.ms.loja.catalogo.api.model.ErroCampoDto;
import vahenrique.ms.loja.catalogo.api.model.ErroDto;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ErroCampoDto> campos = ex.getBindingResult().getAllErrors().stream()
				.map(error -> new ErroCampoDto(((FieldError) error).getField(),
						messageSource.getMessage(error, LocaleContextHolder.getLocale())))
				.collect(Collectors.toList());

		ErroDto erro = new ErroDto(status.value(), OffsetDateTime.now(), "Um ou mais campos com valor inválido.",
				campos);

		return handleExceptionInternal(ex, erro, headers, status, request);
	}
}
