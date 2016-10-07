package net.astanajug.meetup.spock;

/**
 * @author Eugene Svalukhin.
 */
public class SpockStateResolver implements StateResolver {
    @Override
    public boolean tryToBeat(State checkedState) {
        switch (checkedState) {
            case SCISSORS:
            case ROCK:
            case OUT:
                return true;
        }
        return false;
    }
}
