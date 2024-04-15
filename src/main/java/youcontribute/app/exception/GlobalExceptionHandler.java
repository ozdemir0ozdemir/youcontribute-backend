package youcontribute.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import youcontribute.app.model.ErrorResponse;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RepositoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> repositoryAlreadyExistsException(RepositoryAlreadyExistsException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                                ex.getMessage(),
                                ex.getClass().getSimpleName(),
                                "409 - CONFLICT",
                                ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
                        )
                );
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundException(EntityNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                                ex.getMessage(),
                                ex.getClass().getSimpleName(),
                                "404 - NOT_FOUND",
                                ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
                        )
                );
    }
}
