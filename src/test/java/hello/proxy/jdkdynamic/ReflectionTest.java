package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0(){
        Hello hello = new Hello();

        log.info("start");
        String result1 = hello.callA();
        log.info("result={}", result1);

        log.info("start");
        String result2 = hello.callB();
        log.info("result={}", result2);

    }

    @Test
    void reflection1() throws Exception {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello target = new Hello();
        Method methodA = classHello.getMethod("callA");
        Object result1 = methodA.invoke(target);

        log.info("result1={}", result1);

        Method methodB = classHello.getMethod("callB");
        Object result2 = methodB.invoke(target);
        log.info("result2={}", result2);
    }

    @Test
    void reflection2() throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        Method methodA = classHello.getMethod("callA");
        dynamicCall(methodA, target);
        Method methodB = classHello.getMethod("callB");
        dynamicCall(methodB, target);
    }

    private void dynamicCall(Method methodA, Hello target) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        String result = (String)methodA.invoke(target);
        log.info("result={}", result);
    }

    @Slf4j
    static class  Hello{
       public String callA(){
          log.info("CallA");
          return "A";
        }

        public String callB(){
           log.info("CallB");
           return "B";
        }
    }
}
