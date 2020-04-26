package mocky;

import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;

public class InterceptorDelegate {

    @RuntimeType
    public static Object intercept(@This Object mock,
                                   @FieldValue("interceptor") MockyInterceptor interceptor,
                                   @Origin Method invokedMethod,
                                   @AllArguments Object[] arguments) {

        return interceptor.invoke(mock, invokedMethod, arguments);
    }
}
