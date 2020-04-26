package mocky.spring;

import mocky.InvocationDetails;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.List;

public class MockyAdvice implements MethodInterceptor {

    private List<InvocationDetails> recordedBehaviourList;

    public MockyAdvice(List<InvocationDetails> behaviourList) {
        this.recordedBehaviourList = behaviourList;
    }

    public Object invoke(org.aopalliance.intercept.MethodInvocation invocation) {

        String methodName = invocation.getMethod().getName();
        Object[] arguments = invocation.getArguments();

        InvocationDetails invocationDetails = new InvocationDetails(methodName, arguments, invocation.getThis().getClass().getName());

        if (!recordedBehaviourList.contains(invocationDetails)) {
            recordedBehaviourList.add(invocationDetails);
            return invocation.getMethod().getDefaultValue();

        } else {

            InvocationDetails recordedBehaviour = recordedBehaviourList.get(recordedBehaviourList.indexOf(invocationDetails));
            return recordedBehaviour.getResult();

        }
    }
}