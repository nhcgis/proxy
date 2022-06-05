package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component{
    private Component target;

    public MessageDecorator(Component target) {
        this.target = target;
    }

    @Override
    public String operation() {
        log.info("MessageDecorator 실행");
        String result = target.operation();
        String decoResult = "****" + result + "****";
        log.info("result={}, decoResult={}", result, decoResult);
        return decoResult;
    }
}
