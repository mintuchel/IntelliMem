package enpalmer.intellimem.domain.openai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import enpalmer.intellimem.domain.openai.utility.*;

@Service
@RequiredArgsConstructor
public class OpenAIService {
    private final RestTemplate restTemplate;
    private final OpenAIContentParser openAIContentParser;
    private final OpenAIRequestBuilder openAIRequestBuilder;

    String url = "https://api.openai.com/v1/chat/completions";

    private ResponseEntity<String> sendRequest(String url, HttpEntity<Map<String, Object>> request){
        try{
            return restTemplate.postForEntity(url, request, String.class);
        }catch(RestClientException e){
            throw new RestClientException("OpenAI API 호출 중 오류가 발생했습니다", e);
        }
    }

    public String changeInputToTodoList(String userVoiceInput){

        HttpEntity<Map<String, Object>> request = openAIRequestBuilder.getOpenAIRequest(userVoiceInput);

        ResponseEntity<String> response = sendRequest(url, request);

        return openAIContentParser.parseContent(response.getBody());
    }
}

