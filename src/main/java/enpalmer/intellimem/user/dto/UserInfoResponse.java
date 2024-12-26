package enpalmer.intellimem.user.dto;

public record UserInfoResponse(
        int id,
        String username,
        String password
) { }
