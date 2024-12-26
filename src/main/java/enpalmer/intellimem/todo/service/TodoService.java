package enpalmer.intellimem.todo.service;

import enpalmer.intellimem.todo.dto.TodoInfoResponse;
import enpalmer.intellimem.todo.dto.UpdateTodoStatusRequest;
import enpalmer.intellimem.todo.dto.CreateTodoRequest;
import enpalmer.intellimem.todo.entity.Todo;
import enpalmer.intellimem.todo.repository.TodoRepository;
import enpalmer.intellimem.user.UserRepository;
import enpalmer.intellimem.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    // private final OpenAIService openAIService;

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<TodoInfoResponse> getTodoListByUserId(String userId){
        return todoRepository.getTodoByUserId(userId).stream()
                .map(todo -> new TodoInfoResponse(todo.getId(), todo.getTask(), todo.getTime(), todo.isCompleted()))
                .toList();
    }

    @Transactional
    public int createNewTodo(CreateTodoRequest createTodoRequest){
        User user = userRepository.findById(createTodoRequest.userId()).orElseThrow();

        Todo todo = Todo.builder()
                .user(user)
                .time(createTodoRequest.time())
                .task(createTodoRequest.task())
                .completed(false)
                .calendered(createTodoRequest.calendered())
                .build();

        todoRepository.save(todo);

        return 1;
    }

    @Transactional
    public int updateTodoStatus(UpdateTodoStatusRequest updateTodoStatusRequest){
        Todo todo = todoRepository.findById(updateTodoStatusRequest.todoId()).orElseThrow();

        todoRepository.changeTodoStatus(todo.getId());

        return 1;
    }
}
