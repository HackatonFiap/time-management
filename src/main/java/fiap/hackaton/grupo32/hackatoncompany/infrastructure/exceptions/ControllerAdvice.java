package fiap.hackaton.grupo32.hackatoncompany.infrastructure.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MessageExceptionHandler> handlerCpfAlreadyExists(BadRequestException error){
        MessageExceptionHandler messageExceptionHandler = new MessageExceptionHandler(
                error.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                new Date()
        );
        return new ResponseEntity<>(messageExceptionHandler, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MessageExceptionHandler> handlerCpfAlreadyExists(BadCredentialsException error){
        MessageExceptionHandler messageExceptionHandler = new MessageExceptionHandler(
                error.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                new Date()
        );
        return new ResponseEntity<>(messageExceptionHandler, HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MessageExceptionHandler> handlerCpfAlreadyExists(AccessDeniedException error){
        MessageExceptionHandler messageExceptionHandler = new MessageExceptionHandler(
                error.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                new Date()
        );
        return new ResponseEntity<>(messageExceptionHandler, HttpStatus.FORBIDDEN);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageExceptionHandler> handlerMethodArgumentNotValidException(MethodArgumentNotValidException error){

        List<String> errors = error.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        MessageExceptionHandler messageExceptionHandler = new MessageExceptionHandler(
                errors,
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                new Date()
        );
        return new ResponseEntity<>(messageExceptionHandler, HttpStatus.BAD_REQUEST);
    }
}
