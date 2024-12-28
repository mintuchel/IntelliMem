package enpalmer.intellimem.global.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OpenAIErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다");

    private HttpStatus status;
    private String message;
}
