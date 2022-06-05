package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.*;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    OrderControllerV1 orderController(LogTrace logTrace){
        OrderControllerV1 orderController = new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(orderController, logTrace);
    }

    @Bean
    OrderServiceV1 orderService(LogTrace logTrace){
        OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(orderService, logTrace);
    }

    @Bean
    OrderRepositoryV1 orderRepository(LogTrace logTrace){
        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(orderRepository, logTrace);
    }

    @Bean
    LogTrace logTrace(){
        return new ThreadLocalLogTrace();
    }
}
