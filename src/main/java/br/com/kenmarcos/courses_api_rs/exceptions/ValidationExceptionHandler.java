package br.com.kenmarcos.courses_api_rs.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.kenmarcos.courses_api_rs.dtos.ErrorMessageDTO;

@RestControllerAdvice
public class ValidationExceptionHandler {

  private MessageSource messageSource;

  public ValidationExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorMessageDTO>> handleMethodArgumetnNotValidException(
      MethodArgumentNotValidException ex) {
    List<ErrorMessageDTO> errorDto = new ArrayList<>();

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(err -> {
          String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
          ErrorMessageDTO error = new ErrorMessageDTO(message, err.getField());
          errorDto.add(error);
        });

    return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
  }
}
