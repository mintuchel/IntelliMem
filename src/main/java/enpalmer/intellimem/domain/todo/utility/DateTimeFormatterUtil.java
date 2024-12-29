package enpalmer.intellimem.domain.todo.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeFormatterUtil {

    // 원하는 형식으로 변환하기 위한 DateTimeFormatter
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // 문자열을 LocalDateTime 객체로 변환
    public LocalDateTime stringToLocalDateTime(String dateTimeString) {

        if (dateTimeString == null || dateTimeString.isEmpty()) {
            String formattedMidnight = LocalDate.now(ZoneId.of("Asia/Seoul")).atStartOfDay().format(FORMATTER);
            return LocalDateTime.parse(formattedMidnight, FORMATTER);
        }

        return LocalDateTime.parse(dateTimeString, FORMATTER);
    }

    // LocalDateTime 객체를 문자열로 변환
    public String localDateTimeToString(LocalDateTime localDateTime) {
        if(localDateTime.getHour() == 0 && localDateTime.getMinute() == 0 && localDateTime.getSecond() == 0) {
            return "";
        }
        return localDateTime.format(FORMATTER);
    }
}
