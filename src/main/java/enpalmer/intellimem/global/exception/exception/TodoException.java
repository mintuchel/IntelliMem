package enpalmer.intellimem.global.exception.exception;

import enpalmer.intellimem.global.exception.errorcode.TodoErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodoException extends RuntimeException{
    private TodoErrorCode errorCode;
}
