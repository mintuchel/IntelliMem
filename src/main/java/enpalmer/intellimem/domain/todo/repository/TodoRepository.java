package enpalmer.intellimem.domain.todo.repository;

import enpalmer.intellimem.domain.todo.entity.Todo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    @Query(value = "SELECT * FROM todo WHERE user_id = :user_id", nativeQuery = true)
    List<Todo> getTodoByUserId(@Param("user_id") String user_id);

    // 업데이트된 행 수를 반환
    // 성공이면 1 실패하면 0 반환
    @Modifying
    @Transactional
    @Query(value = "UPDATE todo SET completed = !completed WHERE id = :todo_id", nativeQuery = true)
    void changeTodoStatus(@Param("todo_id") long todo_id);
}
