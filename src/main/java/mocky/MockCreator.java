package mocky;

import java.util.List;

public interface MockCreator {

     <T> T createMock(Class<T> mockTargetClass, List<InvocationDetails> behaviourList);
}
