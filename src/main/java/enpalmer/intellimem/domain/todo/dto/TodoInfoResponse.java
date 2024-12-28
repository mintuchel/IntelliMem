package enpalmer.intellimem.domain.todo.dto;

public record TodoInfoResponse(
        int id,
        String task,
        String time,
        boolean calendered,
        boolean completed
) { }
