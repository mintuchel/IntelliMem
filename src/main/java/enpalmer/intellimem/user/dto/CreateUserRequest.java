package enpalmer.intellimem.user.dto;

public record CreateUserRequest(
        String username,
        String password
) { }
