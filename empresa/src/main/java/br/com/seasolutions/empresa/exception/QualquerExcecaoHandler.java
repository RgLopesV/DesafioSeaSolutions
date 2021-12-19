package br.com.seasolutions.empresa.exception;

import br.com.seasolutions.empresa.dto.ErrorsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static br.com.seasolutions.empresa.config.MensagemConstantesConfig.SETOR_PT;

@RestControllerAdvice
public class QualquerExcecaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({EntityNotFoundException.class})
    public ErrorsResponseDto entityNotFoundHandler(EntityNotFoundException exception){
        return  ErrorsResponseDto.Converte(HttpStatus.BAD_REQUEST.name(), exception.getLocalizedMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorsResponseDto> EmptyFieldHandler(MethodArgumentNotValidException exception){
        List<ErrorsResponseDto> errorsResponseDto = new ArrayList<>();

        List<FieldError> fielderrors = exception.getBindingResult().getFieldErrors();
        fielderrors.forEach(e->{
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorsResponseDto erro = new ErrorsResponseDto(e.getField(), mensagem);
            errorsResponseDto.add(erro);
        });
        return  errorsResponseDto;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorsResponseDto noSuchElementHandler(NoSuchElementException exception){
        return  ErrorsResponseDto.Converte(SETOR_PT, exception.getLocalizedMessage());

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ErrorsResponseDto emptyResultDataAccessHandler (EmptyResultDataAccessException exception){
        return  ErrorsResponseDto.Converte(HttpStatus.BAD_REQUEST.name(), exception.getLocalizedMessage());
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorsResponseDto dataInterityViolationHandler(DataIntegrityViolationException exception){
        return  ErrorsResponseDto.Converte(HttpStatus.BAD_REQUEST.name(), exception.getLocalizedMessage());
    }
}
