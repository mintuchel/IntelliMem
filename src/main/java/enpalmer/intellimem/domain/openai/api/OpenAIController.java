package enpalmer.intellimem.domain.openai.api;

import enpalmer.intellimem.domain.openai.dto.OpenAITransitionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import enpalmer.intellimem.domain.openai.service.OpenAIService;

@RestController
@RequestMapping("/api/v1/openai")
@RequiredArgsConstructor
public class OpenAIController {

    private final OpenAIService openAIService;

    @PostMapping("")
    public String changeToTodoList(@RequestBody OpenAITransitionRequest request){
        return openAIService.changeInputToTodoList(request.voiceInput());
    }
}
