package enpalmer.intellimem.domain.user.dto;

public record CreateUserRequest(
        String username,
        String password
) { }
