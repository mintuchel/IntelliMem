package enpalmer.intellimem.domain.openai.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OpenAIRequestBuilder {

    // Azure 환경변수에서 API KEY 주입
    @Value("${OPENAI_API_KEY}")
    private String OPENAI_API_KEY;

    // OpenAI API 로 쏠 HTTP REQUEST HEADER BODY 세팅해서 만들어줌
    public HttpEntity<Map<String, Object>> getOpenAIRequest(String userVoiceInput){
        HttpHeaders headers = setHeader();
        Map<String, Object> requestBody = setRequestBody(userVoiceInput);
        return new HttpEntity<>(requestBody, headers);
    }

    // REQUEST HEADER 설정
    private HttpHeaders setHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(OPENAI_API_KEY);

        return headers;
    }

    // REQUEST BODY MESSAGE 설정
    private Map<String,Object> setRequestBody(String userVoiceInput){
        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("model","gpt-4o-mini");

        List<Map<String,String>> messages = setMessages(userVoiceInput);
        requestBody.put("messages",messages);

        requestBody.put("temperature", 0.3);

        return requestBody;
    }

    private List<Map<String,String>> setMessages(String userVoiceInput){
        List<Map<String,String>> messages = new ArrayList<>();

        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role","system");
        systemMessage.put("content","""
            You are a text analysis assistant. Your task is to analyze input sentences and extract "task" and "time" pairs based on the following instructions:
            
            # Instructions:
            - Identify specific tasks and their associated times from the input sentences.
            - If a time is explicitly mentioned, extract it and associate it with the relevant task.
            - If no time is mentioned, leave the "time" field empty (e.g., `"time": ""`).
            - If relative times like "tomorrow" or "next day" are mentioned, calculate the exact date based on today's date and use the format "YYYY-MM-DD HH:mm:ss" for all timestamps.
            - If terms like "2 hours later", "this afternoon", "this morning", "this evening", "today afternoon", etc. are mentioned, infer the appropriate time based on the current time of day and associate it with the task. For example:
              - "2 hours later" should add 2 hours to the current time.
              - "this afternoon" should associate a typical afternoon time (e.g., 1:00 PM - 5:00 PM).
              - "this morning" should associate a typical morning time (e.g., 9:00 AM - 12:00 PM).
              - "this evening" should associate a typical evening time (e.g., 6:00 PM - 9:00 PM).
              - "today afternoon" should refer to a time between 12:00 PM and 5:00 PM.
              - "today evening" should refer to a time between 6:00 PM and 9:00 PM.
              - "today morning" should refer to a time between 6:00 AM and 12:00 PM.
            - Ensure the output is a JSON array of objects where each object contains:
              - `"task"`: The task description.
              - `"time"`: The associated time or an empty string.
            
            # Output Format:
            - Return only the `"result"` field, which is an array of JSON objects.\s
            - Each object in the array must contain:
              - `"task"`: The task description.
              - `"time"`: The associated time or an empty string.
            
            # Example Inputs and Outputs:
            1. Input: "I have to go to school at 7:00 AM."
               Output:
               ```json
               [
                 {
                   "task": "go to school",
                   "time": "2024-05-30 07:00:00"
                 }
               ]
            
            """);
        messages.add(systemMessage);

        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role","user");
        userMessage.put("content", userVoiceInput);
        messages.add(userMessage);

        return messages;
    }

}


