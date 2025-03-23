package learning.more.service.auth;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import learning.more.common.AppResult;
import learning.more.common.enums.ResultCode;
import learning.more.model.vo.SimpleUserVo;
import learning.more.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * token检查过滤器
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("进入过滤器");
        //清除上下文（清除认证信息）
        SecurityContextHolder.clearContext();
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("请求路径：" + req.getRequestURI());
        String token = req.getHeader("X-Token");
        logger.info("验证jwt");
        Claims claims = null;
        try {
            // 如果没有 token 则直接放行，如果不是 security 中配置的放行请求，那么将会被 security 拦截器拦截
            if (!StringUtils.hasText(token)) {
                filterChain.doFilter(request, response);
                log.info("token为空");
                return;
            }
            claims = JwtUtils.getClaims(token);
        } catch (ExpiredJwtException e) {
            log.debug("解析JWT失败，JWT过期:{},{}", e.getClass().getName(), e.getMessage());
            String errorMessage = "登录信息已过期，请重新登录";
            AppResult jsonResult = AppResult.failed(ResultCode.ERR_JWT_EXPIRED);
            String jsonResultString = JSON.toJSONString(jsonResult);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().println(jsonResultString);
            return;
        } catch (SignatureException e) {
            log.debug("解析JWT失败，签名错误:{},{}", e.getClass().getName(), e.getMessage());
            AppResult jsonResult = AppResult.failed(ResultCode.ERR_JWT_INVALID);
            String jsonResultString = JSON.toJSONString(jsonResult);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().println(jsonResultString);
            return;
        } catch (MalformedJwtException e) {
            log.debug("解析JWT失败，JWT数据有误:{},{}", e.getClass().getName(), e.getMessage());
            AppResult jsonResult = AppResult.failed(ResultCode.ERR_JWT_INVALID);
            String jsonResultString = JSON.toJSONString(jsonResult);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().println(jsonResultString);
            return;
        } catch (Throwable e) {
            log.debug("解析JWT失败，错误详情:{},{}", e.getClass().getName(), e.getMessage());
            AppResult jsonResult = AppResult.failed(ResultCode.ERR_JWT_INVALID);
            String jsonResultString = JSON.toJSONString(jsonResult);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().println(jsonResultString);
            return;
        }
        // 如果有 token, 并且解析成功

        long id = Long.parseLong(String.valueOf(claims.get("id")));

        SimpleUserVo simpleUserEntity = new SimpleUserVo(id, (String) claims.get("username"), (Long) claims.get("tenantId"));

        //todo 后续如果需要鉴权可以传入权限列表
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(simpleUserEntity, null, null);
        //放置上下文
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request, response);
    }
}
