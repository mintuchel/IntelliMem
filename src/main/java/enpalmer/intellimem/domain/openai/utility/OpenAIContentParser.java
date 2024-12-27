package enpalmer.intellimem.domain.openai.utility;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class OpenAIContentParser {
    public String parseContent(String response) {
        JSONObject rootObject = new JSONObject(response);

        return rootObject.getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content");
    }
}
