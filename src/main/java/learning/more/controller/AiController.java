package learning.more.controller;

import jakarta.annotation.Resource;
import learning.more.service.ai.api.AiService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @description:
 * @author：dukelewis
 * @date: 2025/2/5
 * @Copyright： https://github.com/DukeLewis
 */
@RestController
@RequestMapping("/api/ai")
@CrossOrigin("${api.config.cross-origin}")
public class AiController {
    @Resource
    private AiService aiService;

    @GetMapping("/test")
    public SseEmitter test(String msg) {
        SseEmitter sseEmitter = new SseEmitter();
        aiService.createStreamChatCompletionAll(msg, sseEmitter);
        return sseEmitter;
    }
}
