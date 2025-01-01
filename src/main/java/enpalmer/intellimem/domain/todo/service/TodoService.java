package enpalmer.intellimem.domain.todo.service;

import enpalmer.intellimem.domain.todo.dto.TodoInfoResponse;
import enpalmer.intellimem.domain.todo.dto.UpdateTodoStatusRequest;
import enpalmer.intellimem.domain.todo.dto.CreateTodoRequest;
import enpalmer.intellimem.domain.todo.entity.Todo;
import enpalmer.intellimem.domain.todo.repository.TodoRepository;
import enpalmer.intellimem.domain.todo.utility.DateTimeFormatterUtil;
import enpalmer.intellimem.domain.user.dto.UserInfoResponse;
import enpalmer.intellimem.domain.user.repository.UserRepository;
import enpalmer.intellimem.domain.user.entity.User;
import enpalmer.intellimem.global.exception.errorcode.TodoErrorCode;
import enpalmer.intellimem.global.exception.errorcode.UserErrorCode;
import enpalmer.intellimem.global.exception.exception.TodoException;
import enpalmer.intellimem.global.exception.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final DateTimeFormatterUtil dateTimeFormatterUtil;
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional
    public int createNewTodo(CreateTodoRequest createTodoRequest){
        User user = userRepository.findById(createTodoRequest.userId())
                .orElseThrow(()-> new UserException(UserErrorCode.NOT_FOUND));

        Todo todo = Todo.builder()
                .user(user)
                .scheduledAt(dateTimeFormatterUtil.stringToLocalDateTime(createTodoRequest.time()))
                .task(createTodoRequest.task())
                .completed(false)
                .calendered(false)
                .build();

        todoRepository.save(todo);

        return todo.getId();
    }

    // TODO 삭제
    @Transactional
    public String deleteTodo(int todoId){
        todoRepository.deleteById(todoId);
        return "deleted";
    }

    // TODO 단건 조회
    @Transactional(readOnly = true)
    public TodoInfoResponse getTodoById(int todoId){
        return todoRepository.findById(todoId)
                .map(todo -> new TodoInfoResponse(
                        todo.getId(),
                        todo.getTask(),
                        dateTimeFormatterUtil.localDateTimeToString(todo.getScheduledAt()),
                        todo.isCalendered(),
                        todo.isCompleted()))
                .orElseThrow(()-> new TodoException(TodoErrorCode.NOT_FOUND));
    }

    // 특정 유저의 TODO 리스트 전체 조회
    @Transactional(readOnly = true)
    public List<TodoInfoResponse> getTodoListByUserId(String userId){
        return todoRepository.getTodoListByUserId(userId).stream()
                .map(todo -> new TodoInfoResponse(
                        todo.getId(),
                        todo.getTask(),
                        dateTimeFormatterUtil.localDateTimeToString(todo.getScheduledAt()),
                        todo.isCalendered(),
                        todo.isCompleted()))
                .toList();
    }

    // 당일 TODO 조회
    @Transactional(readOnly = true)
    public List<TodoInfoResponse> getTodayTodoListByUserId(String userId){

        // 한국 시간대로 오늘 날짜 계산
        String today = LocalDate.now(ZoneId.of("Asia/Seoul")).toString();

        return todoRepository.getCertainDateTodoListByUserId(userId, today).stream()
                .map(todo -> new TodoInfoResponse(
                        todo.getId(),
                        todo.getTask(),
                        dateTimeFormatterUtil.localDateTimeToString(todo.getScheduledAt()),
                        todo.isCalendered(),
                        todo.isCompleted()))
                .toList();
    }

    // 특정 날짜의 TODO 조회
    @Transactional(readOnly = true)
    public List<TodoInfoResponse> getCertainDateTodoListByUserId(String userId, String date){
        return todoRepository.getCertainDateTodoListByUserId(userId, date).stream()
                .map(todo -> new TodoInfoResponse(
                        todo.getId(),
                        todo.getTask(),
                        dateTimeFormatterUtil.localDateTimeToString(todo.getScheduledAt()),
                        todo.isCalendered(),
                        todo.isCompleted()))
                .toList();
    }

    @Transactional
    public int updateCompletedStatus(UpdateTodoStatusRequest updateTodoStatusRequest){
        Todo todo = todoRepository.findById(updateTodoStatusRequest.todoId())
                .orElseThrow(() -> new TodoException(TodoErrorCode.NOT_FOUND));

        todoRepository.updateCompletedStatus(todo.getId());

        return todo.getId();
    }

    @Transactional
    public int updateCalenderedStatus(UpdateTodoStatusRequest updateTodoStatusRequest){
        Todo todo = todoRepository.findById(updateTodoStatusRequest.todoId())
                .orElseThrow(() -> new TodoException(TodoErrorCode.NOT_FOUND));

        todoRepository.updateCalenderedStatus(todo.getId());

        return todo.getId();
    }
}
