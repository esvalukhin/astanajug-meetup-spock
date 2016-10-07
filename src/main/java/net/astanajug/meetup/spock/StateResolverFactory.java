package net.astanajug.meetup.spock;

/**
 * @author Eugene Svalukhin.
 */
public class StateResolverFactory {
    public StateResolver produce(State state) {
        switch (state) {
            case PAPER:
                return new PaperStateResolver();
            case ROCK:
                return new RockStateResolver();
            case LIZARD:
                return new LizardStateResolver();
            case SCISSORS:
                return new ScissorsStateResolver();
            case SPOCK:
                return new SpockStateResolver();
        }
        throw new IllegalStateException("Unsupported state");
    }
}
