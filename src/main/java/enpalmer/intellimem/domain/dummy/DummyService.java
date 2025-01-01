package enpalmer.intellimem.domain.dummy;

import enpalmer.intellimem.domain.todo.entity.Todo;
import enpalmer.intellimem.domain.todo.utility.DateTimeFormatterUtil;
import enpalmer.intellimem.domain.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class DummyService {
    @PersistenceContext
    private final EntityManager em;

    private final DateTimeFormatterUtil dateTimeFormatterUtil;

    User user;

    @Transactional
    public void initHost() {
        user = User.builder()
                .username("messi")
                .password("10")
                .build();

        em.persist(user);
    }

    @Transactional
    public void initTodo(){
        Todo todo1 = Todo.builder()
                .user(user)
                .task("have dinner with pep")
                .calendered(false)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2024-12-31 18:00"))
                .build();

        Todo todo2 = Todo.builder()
                .user(user)
                .task("match with man united")
                .calendered(true)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2024-12-31 14:00"))
                .build();

        Todo todo3 = Todo.builder()
                .user(user)
                .task("go to grocery store")
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2024-12-31 10:00"))
                .build();

        Todo todo4 = Todo.builder()
                .user(user)
                .task("take red-green pills")
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime(""))
                .build();

        Todo todo5 = Todo.builder()
                .user(user)
                .task("new year party")
                .calendered(false)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2025-01-01 18:00"))
                .build();

        Todo todo6 = Todo.builder()
                .user(user)
                .task("match with juventus")
                .calendered(true)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2025-01-01 14:00"))
                .build();

        Todo todo7 = Todo.builder()
                .user(user)
                .calendered(false)
                .completed(true)
                .task("call sergio for iniesta")
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2025-01-01 09:30"))
                .build();

        Todo todo8 = Todo.builder()
                .user(user)
                .calendered(false)
                .completed(false)
                .task("take red-green pills")
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime(""))
                .build();

        Todo todo9 = Todo.builder()
                .user(user)
                .task("morning run")
                .calendered(false)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2025-01-02 06:30"))
                .build();

        Todo todo10 = Todo.builder()
                .user(user)
                .task("team meeting")
                .calendered(true)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2025-01-02 10:00"))
                .build();

        Todo todo11 = Todo.builder()
                .user(user)
                .task("lunch with Alex")
                .calendered(true)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2025-01-02 12:30"))
                .build();

        Todo todo12 = Todo.builder()
                .user(user)
                .task("evening yoga")
                .calendered(false)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2025-01-02 19:00"))
                .build();

        Todo todo13 = Todo.builder()
                .user(user)
                .task("write final exam report")
                .calendered(false)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2025-01-03 09:00"))
                .build();

        Todo todo14 = Todo.builder()
                .user(user)
                .task("call mom for housing")
                .calendered(true)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2025-01-03 11:00"))
                .build();

        Todo todo15 = Todo.builder()
                .user(user)
                .task("buy groceries")
                .calendered(true)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2025-01-03 15:00"))
                .build();

        Todo todo16 = Todo.builder()
                .user(user)
                .task("take 2 pink pills")
                .calendered(true)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2025-01-03 07:30"))
                .build();

        Todo todo17 = Todo.builder()
                .user(user)
                .task("visit doctor at denver street")
                .calendered(false)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2025-01-04 10:20"))
                .build();

        Todo todo18 = Todo.builder()
                .user(user)
                .task("meet friends at mary's")
                .calendered(true)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2025-01-04 14:00"))
                .build();

        Todo todo19 = Todo.builder()
                .user(user)
                .task("pick up ben")
                .calendered(true)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2025-01-04 16:40"))
                .build();

        Todo todo20 = Todo.builder()
                .user(user)
                .task("dinner with Sam")
                .calendered(true)
                .completed(false)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime("2025-01-04 19:30"))
                .build();

        em.persist(todo1);
        em.persist(todo2);
        em.persist(todo3);
        em.persist(todo4);
        em.persist(todo5);
        em.persist(todo6);
        em.persist(todo7);
        em.persist(todo8);
        em.persist(todo9);
        em.persist(todo10);
        em.persist(todo11);
        em.persist(todo12);
        em.persist(todo13);
        em.persist(todo14);
        em.persist(todo15);
        em.persist(todo16);
        em.persist(todo17);
        em.persist(todo18);
        em.persist(todo19);
        em.persist(todo20);
    }
}
