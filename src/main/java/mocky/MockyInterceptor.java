package mocky;

import java.lang.reflect.Method;
import java.util.List;

public class MockyInterceptor {

    private List<InvocationDetails> recordedInvocationDetails;

    public MockyInterceptor(List<InvocationDetails> recordedInvocationDetails) {
        this.recordedInvocationDetails = recordedInvocationDetails;
    }

    public Object invoke(Object mock, Method invokedMethod, Object[] arguments) {

        String methodName = invokedMethod.getName();

        InvocationDetails invocationDetails = new InvocationDetails(methodName, arguments, mock.getClass().getName());

        if (!recordedInvocationDetails.contains(invocationDetails)) {
            recordedInvocationDetails.add(invocationDetails);
            return invokedMethod.getDefaultValue();

        } else {

            InvocationDetails recordedBehaviour = recordedInvocationDetails.get(recordedInvocationDetails.indexOf(invocationDetails));
            return recordedBehaviour.getResult();

        }
    }
}
