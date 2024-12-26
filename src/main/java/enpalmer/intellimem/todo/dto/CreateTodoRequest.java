package enpalmer.intellimem.todo.dto;

import java.time.LocalDateTime;

public record CreateTodoRequest(
        int userId,
        String task,
        LocalDateTime time,
        boolean calendered
) { }
