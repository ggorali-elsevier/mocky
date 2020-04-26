package mocky;

import java.util.Arrays;
import java.util.Objects;

public class InvocationDetails<T> {

    private String attachedClassName;

    private String methodName;

    private Object[] arguments;

    private T result;

    public InvocationDetails(String methodName, Object[]arguments, String attachedClassName) {
        this.methodName = methodName;
        this.arguments = arguments;
        this.attachedClassName = attachedClassName;
    }

    public void thenReturn(T t) {
        this.result = t;
    }

    public T getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvocationDetails<?> behaviour = (InvocationDetails<?>) o;
        return Objects.equals(attachedClassName, behaviour.attachedClassName) &&
                Objects.equals(methodName, behaviour.methodName) &&
                Arrays.equals(arguments, behaviour.arguments);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(attachedClassName, methodName);
        result = 31 * result + Arrays.hashCode(arguments);
        return result;
    }

}
