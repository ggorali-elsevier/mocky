package mocky;

public class Mocky {

    static final RealMocky realMocky = new RealMocky();

    public static <T> T mock(Class<T> t) {

        return realMocky.mock(t);

    }

    public static <T> InvocationDetails<T> when(T methodCall) {

        return realMocky.when(methodCall);
    }
}
