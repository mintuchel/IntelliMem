package enpalmer.intellimem.global.exception.exception;

import enpalmer.intellimem.global.exception.errorcode.OpenAIErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OpenAIException extends RuntimeException {
    private OpenAIErrorCode errorCode;
}
