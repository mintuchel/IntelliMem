package enpalmer.intellimem.global.exception.exception;

import enpalmer.intellimem.global.exception.errorcode.UserErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserException extends RuntimeException {
    private UserErrorCode errorCode;
}
