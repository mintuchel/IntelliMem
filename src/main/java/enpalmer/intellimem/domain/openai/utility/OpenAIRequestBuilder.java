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
            - Identify specific tasks and their associated times from the input sentence.
            - When handling LocalDateTime, ensure it aligns with the South Korean local time (KST, UTC+9).
            - If a time is explicitly mentioned, extract it and associate it with the relevant task.
            - If relative times like "tomorrow" or “the day after tommorow” or “next day” are mentioned, calculate the exact date and time based on today's date.
            - Use the format "YYYY-MM-DD HH:mm" for all timestamps.
            - If terms like “ten minutes later”, “three hours later", "this afternoon", "this morning", "this evening", "today afternoon", etc. are mentioned, infer the appropriate time based on the current time of day and associate it with the task. For example:
                - two hours later" should add 2 hours to the current time.
                - "this afternoon" should associate a specific time of 3:00 PM.
                - "this morning" should associate a specific time of 9:00 AM.
                - "this evening" should associate a specific time of 6:00 PM.
            - If no time is mentioned, leave the "time" field empty (e.g., `"time": ""`).
            - Ensure the output is a JSON array of objects where each object contains:
              - `"task"`: The task description.
              - `"time"`: The associated time or an empty string.
            
            # Output Format:
            - Return only the `"result"` field, which is an array of JSON objects.\s
            - Each object in the array must contain:
              - `"task"`: The task description.
              - `"time"`: The associated time or an empty string.
            
            # Example Inputs and Outputs:
            1. Input: "I have to take my pills and go to exercise with Charlie at 9:00 AM. Then I have meeting with Mr.Brown at 3'o clock at starbucks and need to go to grocery store"
               Output:
               ```json
               [
                 {
                   "task": "take pills",
                   "time": ""
                 },
                 {
                   "task": "exercies with Charile",
                   "time": "2024-12-28 09:00"
                 },
                 {
                   "task": "meeting with Mr.Brown",
                   "time": "2024-12-28 15:00"
                 },
                 {
                   "task": "go to grocery store",
                   "time": ""
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


