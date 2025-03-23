package learning.more.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import learning.more.common.AppResult;
import learning.more.common.enums.ResultCode;
import learning.more.dao.mapper.UserMapper;
import learning.more.exception.ApplicationException;
import learning.more.model.domain.User;
import learning.more.model.entity.AuthUserEntity;
import learning.more.service.auth.JwtAuthenticationTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author：dukelewis
 * @date: 2024/9/22
 * @Copyright： https://github.com/DukeLewis
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Resource
    private UserMapper userMapper;


    /**
     * 密码明文加密方式配置
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setHideUserNotFoundExceptions(false);
        return authenticationProvider;
    }

    /**
     * 身份验证管理器
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            //通过用户名查询密码进行比对然后进行返回
            User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
            if(user==null){
                throw new ApplicationException(AppResult.failed(ResultCode.FAILED_USER_NOT_EXISTS));
            }
            return new AuthUserEntity(username, user.getPassword(), Collections.EMPTY_LIST, user.getId(), user.getTenantId());
        };
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.OPTIONS, "/api/course/generateCourseBaseInfo"))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/doc.html",
                                "/webjars/js/**",
                                "/webjars/css/**",
                                "/swagger-ui.html",
                                "/favicon.ico",
                                "/webjars/springfox-swagger-ui/**",
                                "/swagger-resources",
                                "/v3/api-docs/**",
                                "/api/user/**",
                                "/api/test/**",
                                "/api/session/**",
                                "/api/class/listClassOverview",
                                "/api/class/getClassInfo",
                                "/api/student/listStudentOverview",
                                "/api/class/deleteClass",
                                "/api/class/updateClassInfo",
                                "/api/student/listStudentOverviewPage",
                                "/api/student/createStudent",
                                "/api/student/updateStudent",
                                "/api/student/deleteStudent",
                                "/api/course/listCourseOverviewPage",
                                "/api/course/getCourseDetail",
                                "/api/course/deleteCourse",
                                "/api/course/updateCourse",
                                "/api/course/generateCourseBaseInfo",
                                "/api/school/listSchoolOverviewPage",
                                "/api/school/updateSchool",
                                "/api/school/createSchool",
                                "/api/school/deleteSchool",
                                "/api/weeklyPlan/updateWeeklyPlan",
                                "/api/weeklyPlan/createWeeklyPlan",
                                "/api/weeklyPlan/deleteWeeklyPlan",
                                "/api/weeklyPlan/listWeeklyPlanPage",
                                "/api/ai/test"
                        )
                        .permitAll()
                        .anyRequest()
                        .authenticated());
        return http.build();
    }

//    /**
//     * 配置跨源访问(CORS)
//     * @return
//     */
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedHeaders(Collections.singletonList("*"));
//        configuration.setAllowedMethods(Collections.singletonList("*"));
//        configuration.setAllowedOrigins(Collections.singletonList("*"));
//        configuration.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // 允许的前端地址
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setExposedHeaders(List.of("Cache-Control", "Content-Language", "Content-Type"));
//        configuration.setAllowCredentials(true); // 允许 Cookie（如果有需要）

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
