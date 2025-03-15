package learning.more.service.ai.assistant;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/2/5
 * @Copyright： https://github.com/DukeLewis
 */
@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, streamingChatModel = "streamingChatLanguageModel")
public interface Assistant {
    @SystemMessage("""
            你是一个学前教育专家
            """)
    TokenStream chat(String message);
}
