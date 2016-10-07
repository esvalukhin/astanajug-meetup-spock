package net.astanajug.meetup.spock;

/**
 * @author Eugene Svalukhin.
 */
public class PaperStateResolver implements StateResolver {
    @Override
    public boolean tryToBeat(State checkedState) {
        switch (checkedState) {
            case SPOCK:
            case ROCK:
            case OUT:
                return true;
        }
        return false;
    }
}
