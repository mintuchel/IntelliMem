package enpalmer.intellimem.domain.openai.api;

import enpalmer.intellimem.domain.openai.dto.OpenAITransitionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import enpalmer.intellimem.domain.openai.service.OpenAIService;

@RestController
@RequestMapping("/api/v1/openai")
@RequiredArgsConstructor
@Tag(name = "OpenAI API", description = "OpenAI 활용")
public class OpenAIController {

    private final OpenAIService openAIService;

    @PostMapping("")
    @Operation(summary = "OpenAI 로 task-time 쌍 파싱 요청")
    public String changeToTodoList(@RequestBody OpenAITransitionRequest request){
        return openAIService.changeInputToTodoList(request.voiceInput());
    }
}
