package enpalmer.intellimem.domain.user;

import enpalmer.intellimem.domain.user.dto.CreateUserRequest;
import enpalmer.intellimem.domain.user.dto.UserInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Tag(name = "유저 API", description = "유저 회원가입, 조회")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    @Operation(summary = "특정 유저 정보 조회")
    public UserInfoResponse getUserById(@PathVariable int userId){
        return userService.getUserById(userId);
    }

    @PostMapping("")
    @Operation(summary = "특정 유저 회원가입")
    public int createNewUser(@RequestBody CreateUserRequest createUserRequest){
        return userService.createNewUser(createUserRequest);
    }
}