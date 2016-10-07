package net.astanajug.meetup.spock;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Eugene Svalukhin.
 */
public class JUnitClassicStateResolverSpec {

    @Test
    public void classicStatesShouldUseGameRules() {

        StateResolver scissorsResolver = new ScissorsStateResolver();
        StateResolver rockResolver = new RockStateResolver();
        StateResolver paperResolver = new PaperStateResolver();

        assertEquals(false, scissorsResolver.tryToBeat(State.SPOCK));
        assertEquals(true, scissorsResolver.tryToBeat(State.LIZARD));
        assertEquals(true, scissorsResolver.tryToBeat(State.OUT));
        assertEquals(true, scissorsResolver.tryToBeat(State.PAPER));
        assertEquals(false, scissorsResolver.tryToBeat(State.ROCK));
        assertEquals(false, scissorsResolver.tryToBeat(State.SCISSORS));

        assertEquals(false, rockResolver.tryToBeat(State.SPOCK));
        assertEquals(true, rockResolver.tryToBeat(State.LIZARD));
        assertEquals(true, rockResolver.tryToBeat(State.OUT));
        assertEquals(false, rockResolver.tryToBeat(State.PAPER));
        assertEquals(false, rockResolver.tryToBeat(State.ROCK));
        assertEquals(true, rockResolver.tryToBeat(State.SCISSORS));

        assertEquals(true, paperResolver.tryToBeat(State.SPOCK));
        assertEquals(false, paperResolver.tryToBeat(State.LIZARD));
        assertEquals(true, paperResolver.tryToBeat(State.OUT));
        assertEquals(false, paperResolver.tryToBeat(State.PAPER));
        assertEquals(true, paperResolver.tryToBeat(State.ROCK));
        assertEquals(false, paperResolver.tryToBeat(State.SCISSORS));

    }
}
