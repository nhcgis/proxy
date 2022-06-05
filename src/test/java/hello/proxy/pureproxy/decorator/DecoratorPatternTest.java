package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {
    @Test
    void noDecoratorTest(){
        Component component = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(component);

        client.execute();
    }

    @Test
    void messageDecoratorTest(){
        Component target = new RealComponent();
        MessageDecorator decorator = new MessageDecorator(target);
        DecoratorPatternClient client = new DecoratorPatternClient(decorator);
        client.execute();
    }

    @Test
    void timeDecoratorTest(){
        Component realTarget = new RealComponent();
        Component messageTarget = new MessageDecorator(realTarget);
        Component timeDecorator = new TimeDecorator(messageTarget);

        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        client.execute();
    }
}
