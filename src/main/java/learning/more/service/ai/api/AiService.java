package learning.more.service.ai.api;

import dev.langchain4j.service.Result;
import jakarta.annotation.Resource;
import learning.more.util.SseUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ExecutorService;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/2/5
 * @Copyright： https://github.com/DukeLewis
 */
@Service
public class AiService {
    @Resource(name = "cachedThreadPool")
    private ExecutorService executorService;

    @Resource
    private AiProviderService aiProviderService;

    public void createStreamChatCompletionAll(String prompt, SseEmitter emitter) {
        // 开启线程执行
        executorService.execute(() -> {
            try {
                SseUtil.StreamResultListener streamResultListener = SseUtil.getDefaultStreamResultListener(emitter);
                aiProviderService.createStreamChatCompletion(prompt, streamResultListener);
            } catch (Exception ex) {
                // 处理异常信息
                SseUtil.handleSSEError(ex, emitter);
            }
        });
    }
}
