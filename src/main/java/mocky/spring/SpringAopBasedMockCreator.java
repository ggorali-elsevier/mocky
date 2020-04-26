package mocky.spring;

import mocky.InvocationDetails;
import mocky.MockCreator;
import org.objenesis.ObjenesisStd;
import org.springframework.aop.framework.ProxyFactory;

import java.util.List;

public class SpringAopBasedMockCreator implements MockCreator {

    private final ObjenesisStd objenesis = new ObjenesisStd();

    @Override
    public  <T> T createMock(Class<T> mockTargetClass, List<InvocationDetails> behaviourList) {
        T mockTargetClassInstance = objenesis.newInstance(mockTargetClass);

        ProxyFactory proxy = new ProxyFactory(mockTargetClassInstance);
        proxy.addAdvice(new MockyAdvice(behaviourList));
        Object mockInstance = proxy.getProxy();

        return mockTargetClass.cast(mockInstance);
    }
}
