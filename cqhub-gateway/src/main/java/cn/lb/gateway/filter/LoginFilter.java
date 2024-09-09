package cn.lb.gateway.filter;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 登录过滤器
 *
 * @author RainSoul
 * @create 2024-09-09
 */
@Slf4j
@Component
public class LoginFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        // 获取响应对象
        ServerHttpResponse response = exchange.getResponse();
        // 创建请求对象的可变副本
        ServerHttpRequest.Builder mutate = request.mutate();
        // 获取请求的URL路径
        String url = request.getURI().getPath();
        // 记录请求的URL信息
        log.info("LoginFilter.filter.url:{}", url);

        // 如果是登录接口，则直接放行
        if (url.contains("/user/doLogin")) {
            return chain.filter(exchange);
        }

        // 获取Sa-Token信息
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        // 记录Sa-Token信息
        log.info("LoginFilter.filter.tokenInfo:{}", new Gson().toJson(tokenInfo));

        // 获取登录账号
        String loginId = (String) tokenInfo.getLoginId();
        // 向请求头中添加登录账号
        mutate.header("loginId", loginId);

        // 使用修改后的请求对象创建新的ServerWebExchange，并继续链式处理
        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }
}
