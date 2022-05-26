package com.gujiacheng.gateway.filter;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.gujiacheng.gateway.utils.JWTUtils;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器
 * @author gujiacheng
 */
@Configuration
public class MyGlobalFilter implements GlobalFilter, Ordered {
    private static final String TOKEN = "token";
    private static final String USER_LOGIN_URL = "/user/login";
    private static final String USER_REGISTER_URL = "/user/register";
    private static final String SYSTEM_DATA_URL = "/system/";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders headers = request.getHeaders();
        String path = request.getURI().getPath();
        String token = headers.getFirst(TOKEN);
        if (path.startsWith(USER_LOGIN_URL) | path.startsWith(USER_REGISTER_URL)| path.startsWith(SYSTEM_DATA_URL)) {
            return chain.filter(exchange);
        }
        if (StringUtils.isEmpty(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        try {
            JWTUtils.verify(token);
        } catch (Exception e) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1;
    }
}


