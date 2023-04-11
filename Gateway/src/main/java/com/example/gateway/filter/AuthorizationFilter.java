package com.example.gateway.filter;


import com.example.gateway.client.OaaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(-1)
public class AuthorizationFilter implements GlobalFilter {

    @Autowired
    OaaClient oaaClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        // 获取请求微服务的请求路径
//        String path = exchange.getRequest().getPath().toString();
//        // 获取请求微服务的请求方法
//           if(path.equals("/oaa/login")){
//            return(chain.filter(exchange));
//        }
//
//        HttpHeaders headers = exchange.getRequest().getHeaders();
//        String id = headers.get("Authorization").toString();
//        if(id!=null){
//            String sessionId = id.substring(1,id.length()-1);
//            String authResult = oaaClient.isLogin(sessionId);
//            if (authResult.equals("已登录")){
                return(chain.filter(exchange));
//            }
//        }
//        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
//        System.out.println("未登录");
//        return exchange.getResponse().setComplete();


    }

}
