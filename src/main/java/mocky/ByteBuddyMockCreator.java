package mocky;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.MethodDelegation;
import org.objenesis.ObjenesisStd;

import java.util.List;

import static java.lang.reflect.Modifier.PRIVATE;
import static net.bytebuddy.matcher.ElementMatchers.any;

/*
 Lifted from Mockito and simplified.
 */
public class ByteBuddyMockCreator implements MockCreator {

    private final ObjenesisStd objenesis = new ObjenesisStd();

    @Override
    public <T> T createMock(Class<T> mockTargetClass, List<InvocationDetails> behaviourList) {
        ByteBuddy byteBuddy = new ByteBuddy();

        Class<? extends T> classWithInterceptor = byteBuddy.subclass(mockTargetClass)
                .method(any())
                .intercept(MethodDelegation.to(InterceptorDelegate.class))
                .defineField("interceptor", MockyInterceptor.class, PRIVATE)
                .implement(MockyInterceptable.class)
                .intercept(FieldAccessor.ofBeanProperty())
                .make()
                .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER).getLoaded();


        T mockTargetInstance = objenesis.newInstance(classWithInterceptor);

        ((MockyInterceptable) mockTargetInstance).setInterceptor(new MockyInterceptor(behaviourList));

        return mockTargetInstance;

    }
}
