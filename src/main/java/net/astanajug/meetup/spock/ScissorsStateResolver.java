package net.astanajug.meetup.spock;

/**
 * @author Eugene Svalukhin.
 */
public class ScissorsStateResolver implements StateResolver {
    @Override
    public boolean tryToBeat(State checkedState) {
        switch (checkedState) {
            case PAPER:
            case LIZARD:
            case OUT:
                return true;
        }
        return false;
    }
}
