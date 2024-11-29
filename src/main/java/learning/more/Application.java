package learning.more;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/11/20
 * @Copyright： https://github.com/DukeLewis
 */
@SpringBootApplication
@MapperScan(basePackages = "learning.more.dao.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
