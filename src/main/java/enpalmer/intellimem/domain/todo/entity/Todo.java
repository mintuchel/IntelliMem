package enpalmer.intellimem.domain.todo.entity;

import enpalmer.intellimem.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    private String task;

    private LocalDateTime scheduledAt;

    private boolean hasTime;

    private boolean completed;

    private boolean calendered;
}

