package mocky;

import java.util.ArrayList;
import java.util.List;

public class RealMocky {

    private final List<InvocationDetails> invocationDetailsList = new ArrayList<>();

    private final MockCreator mockCreator = new ByteBuddyMockCreator();

    public <T> T mock(Class<T> mockTargetClass) {

        T result = mockCreator.createMock(mockTargetClass, invocationDetailsList);

        return result;
    }

    public <T> InvocationDetails<T> when(T methodCall) {

        return invocationDetailsList.get(invocationDetailsList.size() - 1);
    }

}
