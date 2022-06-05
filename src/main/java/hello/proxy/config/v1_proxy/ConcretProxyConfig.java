package hello.proxy.config.v1_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v1_proxy.concret_proxy.OrderControllerConcretProxy;
import hello.proxy.config.v1_proxy.concret_proxy.OrderRepositoryConcretProxy;
import hello.proxy.config.v1_proxy.concret_proxy.OrderServiceConcretProxy;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcretProxyConfig {

    @Bean
    OrderControllerV2 orderControllerV2(LogTrace logTrace){
        OrderControllerV2 orderControllerImpl = new OrderControllerV2(orderServiceV2(logTrace));
        return new OrderControllerConcretProxy(orderControllerImpl, logTrace);
    }

    @Bean
    OrderServiceV2 orderServiceV2(LogTrace logTrace){
        OrderServiceV2 orderServiceImpl = new OrderServiceV2(orderRepositoryV2(logTrace));
        return new OrderServiceConcretProxy(orderServiceImpl, logTrace);
    }
    @Bean
    OrderRepositoryV2 orderRepositoryV2(LogTrace logTrace){
        OrderRepositoryV2 orderRepositoryImpl = new OrderRepositoryV2();
        return new OrderRepositoryConcretProxy(orderRepositoryImpl, logTrace);
    }

    @Bean
    LogTrace logTrace(){
        return new ThreadLocalLogTrace();
    }
}
