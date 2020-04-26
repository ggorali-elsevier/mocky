package mocky;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MockyTest {


    @Test
    public void shouldMockClassWithTwoMethods() {

        Target mockTarget = Mocky.mock(Target.class);

        Mocky.when(mockTarget.doSomething(5)).thenReturn("Mocked!");
        Mocky.when(mockTarget.saySomething(0)).thenReturn(10);


        assertThat(mockTarget.doSomething(5), is("Mocked!"));
        assertThat(mockTarget.saySomething(0), is(10));

    }

    @Test
    public void shouldMockTwoClassesHavingMethodsWithSameSignature(){
        Target mockTarget = Mocky.mock(Target.class);
        Target2 mockTarget2 = Mocky.mock(Target2.class);

        Mocky.when(mockTarget.doSomething(5)).thenReturn("Mocked!");
        Mocky.when(mockTarget.saySomething(0)).thenReturn(10);
        Mocky.when(mockTarget2.doSomething(5)).thenReturn("Made a Thingy");

        assertThat(mockTarget.doSomething(5), is("Mocked!"));
        assertThat(mockTarget2.doSomething(5), is("Made a Thingy"));
        assertThat(mockTarget.saySomething(0), is(10));

    }


    @Test
    public void testConcreteClasses(){
        Target mockTarget = Mocky.mock(Target.class);
        Mocky.when(mockTarget.getTargetName()).thenReturn("Orange");
        Target2 mockTarget2 = Mocky.mock(Target2.class);
        Mocky.when(mockTarget2.getTarget("apple")).thenReturn(mockTarget);


        assertThat(mockTarget2.getTarget("apple").getTargetName(), is("Orange"));

    }


    @Test
    public void shouldMockMethodsHavingTwoArguments(){
        Target mockTarget = Mocky.mock(Target.class);
        Mocky.when(mockTarget.doThings(5, "Thing")).thenReturn("Mocked!");

        assertThat(mockTarget.doThings(5, "Thing"), is("Mocked!"));

    }

}

