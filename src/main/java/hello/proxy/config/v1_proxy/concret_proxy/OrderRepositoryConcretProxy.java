package hello.proxy.config.v1_proxy.concret_proxy;

import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderRepositoryConcretProxy extends OrderRepositoryV2 {
    private OrderRepositoryV2 target;
    private LogTrace logTrace;

    public OrderRepositoryConcretProxy(OrderRepositoryV2 target, LogTrace logTrace) {
        this.target = target;
        this.logTrace = logTrace;
    }

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try{
            status = logTrace.begin("OrderRepository.save()");
            target.save(itemId);
            logTrace.end(status);
        } catch(Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}
