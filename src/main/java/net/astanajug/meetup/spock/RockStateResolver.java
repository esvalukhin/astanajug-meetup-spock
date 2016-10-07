package net.astanajug.meetup.spock;

/**
 * @author Eugene Svalukhin.
 */
public class RockStateResolver implements StateResolver {
    @Override
    public boolean tryToBeat(State checkedState) {
        switch (checkedState) {
            case LIZARD:
            case SCISSORS:
            case OUT:
                return true;
        }
        return false;
    }
}
