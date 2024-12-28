package enpalmer.intellimem.domain.user.service;

import enpalmer.intellimem.domain.user.dto.CreateUserRequest;
import enpalmer.intellimem.domain.user.dto.UserInfoResponse;
import enpalmer.intellimem.domain.user.entity.User;
import enpalmer.intellimem.domain.user.repository.UserRepository;
import enpalmer.intellimem.global.exception.errorcode.UserErrorCode;
import enpalmer.intellimem.global.exception.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserInfoResponse getUserById(int userId){
        return userRepository.findById(userId)
                .map(user -> new UserInfoResponse(user.getId(), user.getUsername(), user.getPassword()))
                .orElseThrow(()-> new UserException(UserErrorCode.NOT_FOUND));
    }

    @Transactional
    public int createNewUser(CreateUserRequest createUserRequest) {
        User user = User.builder()
                .username(createUserRequest.username())
                .password(createUserRequest.password())
                .build();

        return userRepository.save(user).getId();
    }
}
