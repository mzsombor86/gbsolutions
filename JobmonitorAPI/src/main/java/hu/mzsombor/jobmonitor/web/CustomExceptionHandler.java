package hu.mzsombor.jobmonitor.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NonUniqueResultException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import hu.mzsombor.jobmonitor.dto.MyErrorDto;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDto> handleMethodArgumentValidationError(MethodArgumentNotValidException e,
			WebRequest req) {
		MyErrorDto myError = new MyErrorDto(400, "Bad Request", e.getBindingResult().getFieldErrors().stream()
				.map(fe -> fe.getDefaultMessage()).collect(Collectors.toList()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myError);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<MyErrorDto> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException e, WebRequest req) {
		MyErrorDto myError = new MyErrorDto(400, "Bad Request", List.of(e.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myError);
	}
	
	@ExceptionHandler(NonUniqueResultException.class)
	public ResponseEntity<MyErrorDto> handleNonUniqueResultException(
			NonUniqueResultException e, WebRequest req) {
		MyErrorDto myError = new MyErrorDto(400, "Bad Request", List.of(e.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(myError);
	}
	
}
