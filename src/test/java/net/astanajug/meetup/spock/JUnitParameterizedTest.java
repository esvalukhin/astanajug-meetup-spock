package net.astanajug.meetup.spock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * @author Eugene Svalukhin.
 */
@RunWith(Parameterized.class)
public class JUnitParameterizedTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { false, State.SPOCK },
                { true, State.LIZARD },
                { true, State.OUT },
                { true, State.PAPER },
                { false, State.ROCK },
                { false, State.SCISSORS },

        });
    }

    @Parameterized.Parameter(value = 0)
    public boolean expected;

    @Parameterized.Parameter(value = 1)
    public State state;

    @Test
    public void testWithParams() {
        StateResolver scissorsResolver = new ScissorsStateResolver();

        assertEquals(expected, scissorsResolver.tryToBeat(state));
    }
}
