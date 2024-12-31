package enpalmer.intellimem.domain.todo.api;

import enpalmer.intellimem.domain.todo.dto.CreateTodoRequest;
import enpalmer.intellimem.domain.todo.dto.TodoInfoResponse;
import enpalmer.intellimem.domain.todo.dto.UpdateTodoStatusRequest;
import enpalmer.intellimem.domain.todo.service.TodoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todo")
@Tag(name = "Todo API", description = "Todo 조회, 추가, 상태변경")
public class TodoController {
    private final TodoService todoService;

    @GetMapping("all")
    @Operation(summary = "특정 유저의 TODO 전체 조회")
    public List<TodoInfoResponse> getAllTodoListByUserId(@RequestParam String userId){
        return todoService.getTodoListByUserId(userId);
    }

    @GetMapping("today")
    @Operation(summary = "특정 유저의 TODO 당일 조회")
    public List<TodoInfoResponse> getTodayTodoListByUserId(@RequestParam String userId){
        return todoService.getTodayTodoListByUserId(userId);
    }

    @GetMapping("calender")
    @Operation(summary = "특정 유저의 TODO 특정 날짜 조회")
    public List<TodoInfoResponse> getCertainDateTodoListByUserId(@RequestParam String userId, @RequestParam String date){
        return todoService.getCertainDateTodoListByUserId(userId, date);
    }

    @PostMapping("")
    @Operation(summary = "특정 유저 오늘의 할일 추가")
    public int createTodo(@RequestBody CreateTodoRequest createTodoRequest){
        return todoService.createNewTodo(createTodoRequest);
    }

    @DeleteMapping("")
    @Operation(summary = "특정 유저 오늘의 할일 삭제")
    public String deleteTodo(@RequestParam int todoId){
        return todoService.deleteTodo(todoId);
    }

    @PatchMapping("/completed")
    @Operation(summary = "특정 유저의 오늘의 할일 실행완료/실행취소")
    public int updateCompletedStatus(@RequestBody UpdateTodoStatusRequest updateTodoStatusRequest){
        return todoService.updateCompletedStatus(updateTodoStatusRequest);
    }

    @PatchMapping("/calendered")
    @Operation(summary = "특정 유저의 오늘의 할일 달력에 추가/삭제")
    public int updateCalenderedStatus(@RequestBody UpdateTodoStatusRequest updateTodoStatusRequest){
        return todoService.updateCalenderedStatus(updateTodoStatusRequest);
    }
}

