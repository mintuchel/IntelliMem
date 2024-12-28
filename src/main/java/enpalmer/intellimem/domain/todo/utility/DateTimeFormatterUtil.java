package enpalmer.intellimem.domain.todo.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeFormatterUtil {

    // 원하는 형식으로 변환하기 위한 DateTimeFormatter
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // 문자열을 LocalDateTime 객체로 변환
    public LocalDateTime stringToLocalDateTime(String dateTimeString) {
        if(dateTimeString == null || dateTimeString.isEmpty()) {
            return LocalDateTime.MIN;
        }
        return LocalDateTime.parse(dateTimeString, FORMATTER);
    }

    // LocalDateTime 객체를 문자열로 변환
    public String localDateTimeToString(LocalDateTime localDateTime) {
        if(localDateTime == LocalDateTime.MIN) {
            return "";
        }
        return localDateTime.format(FORMATTER);
    }
}
