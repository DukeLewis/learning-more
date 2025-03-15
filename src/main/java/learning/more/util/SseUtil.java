package learning.more.util;

import learning.more.common.enums.EventType;
import lombok.SneakyThrows;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

/**
 * @description: SSE (Server-Sent Events) 工具类,流式返回
 * @author：dukelewis
 * @date: 2024/8/31
 * @Copyright： https://github.com/DukeLewis
 */
public class SseUtil {
    /**
     * 获取默认的 Stream 结果监听器
     * @return 监听器
     */
    public static StreamResultListener getDefaultStreamResultListener(SseEmitter emitter) {
        return new StreamResultListener() {
            @Override
            public void onMessage(String message) throws IOException {
                // 推送消息
                emitter.send(message);
            }

            @Override
            public void onError(Exception ex) throws IOException {
                // 推送错误消息
                emitter.send(SseEmitter.event().data(ex.getMessage()).name(EventType.ERROR.toString()));
                // 结束
                emitter.completeWithError(ex);
            }
        };
    }

    /**
     * 处理 SSE 错误
     * @param ex 异常信息
     */
    @SneakyThrows
    public static void handleSSEError(Exception ex, SseEmitter emitter) {
        // 推送错误消息
        emitter.send(SseEmitter.event().data(ex.getMessage()).name(EventType.ERROR.toString()));
        // 结束
        emitter.completeWithError(ex);
    }

    /**
     * 构建内容不合法 SseEmitter
     */
    @SneakyThrows
    public static SseEmitter buildNotModerationSseEmitter() {
        // 构建 SseEmitter
        SseEmitter emitter = new SseEmitter();
        // 推送错误消息
        emitter.send(SseEmitter.event().data("I'm sorry, but I can't generate content on this topic. Let's explore a different topic that is both educational and beneficial for learning."));
        // 结束
        emitter.complete();
        return emitter;
    }


    /**
     * Stream 结果监听器
     */
    public static interface StreamResultListener {
        /**
         * 接收到消息
         * @param message 消息内容
         * @throws IOException IO 异常
         */
        void onMessage(String message) throws IOException;

        /**
         * 完成
         */
        default void onCompleted() {}

        /**
         * 取消
         */
        default void onCancelled() {}

        /**
         * 出现错误
         * @param ex 异常
         * @throws IOException IO 异常
         */
        void onError(final Exception ex) throws IOException;
    }
}
