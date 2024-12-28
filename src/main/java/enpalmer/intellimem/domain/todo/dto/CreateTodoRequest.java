package enpalmer.intellimem.domain.todo.dto;

public record CreateTodoRequest(
        int userId,
        String task,
        String time
) { }
