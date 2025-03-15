package learning.more.util;

import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

/**
 * @description: 字符串工具类
 * @author：dukelewis
 * @date: 2025/2/11
 * @Copyright： https://github.com/DukeLewis
 */
public class StringUtil {
    public static Boolean isNullAndEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String parsePromptString(String str, Map<String, Object> map) {
        StringSubstitutor sub = new StringSubstitutor(map);
        return sub.replace(str);
    }
}
