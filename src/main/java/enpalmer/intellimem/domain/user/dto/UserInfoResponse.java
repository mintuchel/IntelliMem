package enpalmer.intellimem.domain.user.dto;

public record UserInfoResponse(
        int id,
        String username,
        String password
) { }
