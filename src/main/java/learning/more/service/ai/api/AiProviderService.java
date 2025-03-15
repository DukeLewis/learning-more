package learning.more.service.ai.api;

import com.alibaba.fastjson2.JSON;
import dev.langchain4j.service.TokenStream;
import jakarta.annotation.Resource;
import learning.more.service.ai.assistant.Assistant;
import learning.more.util.SseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/2/5
 * @Copyright： https://github.com/DukeLewis
 */
@Service
@Slf4j
public class AiProviderService {
    @Resource
    private Assistant assistant;

    public String createStreamChatCompletion(String prompt, SseUtil.StreamResultListener listener) throws InterruptedException {
        TokenStream tokenStream = assistant.chat(prompt);
        // 用于判断请求是否结束
        final CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<String> res = new AtomicReference<>("");
        tokenStream.onNext(s -> {
            try {
                listener.onMessage(s);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).onComplete(response -> {
            log.info("请求结束, Res: {}", response);
            res.set(response.content().text());
            latch.countDown();
            listener.onCompleted();
        }).onError(ex -> {
            try {
                listener.onError((Exception) ex);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
        // 等待请求结束
        latch.await();
        return res.get();
    }
}
