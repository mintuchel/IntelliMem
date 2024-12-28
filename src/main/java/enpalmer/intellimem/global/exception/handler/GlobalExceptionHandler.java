package enpalmer.intellimem.global.exception.handler;

import enpalmer.intellimem.global.exception.exception.OpenAIException;
import enpalmer.intellimem.global.exception.exception.TodoException;
import enpalmer.intellimem.global.exception.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserException.class)
    protected ResponseEntity<String> handleUserException(UserException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(e.getErrorCode().getMessage());
    }

    @ExceptionHandler(OpenAIException.class)
    protected ResponseEntity<String> handleOpenAIException(OpenAIException e) {
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(e.getErrorCode().getMessage());
    }

    @ExceptionHandler(TodoException.class)
    protected ResponseEntity<String> handleTodoException(TodoException e){
        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(e.getErrorCode().getMessage());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleGlobalException(Exception e) {
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return ResponseEntity
                    .status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body("URL은 맞는데 HTTP Method가 틀린거임. HTTP Method 확인하고 다시 보내보셈");
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("이 오류 뜨면 나한테 톡 ㄱㄱ");
    }
}
