package Project.CoffeVendingMachine.exceptionHandling;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(SystemNotReadyException.class)
    public ResponseEntity<Object> handleSystemNotReadyException(SystemNotReadyException ex, WebRequest request)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleMessageNotReadableException(HttpMessageNotReadableException ex)
    {
        return new ResponseEntity<>("Please provide correct quantity", HttpStatus.BAD_REQUEST);
    }
}
