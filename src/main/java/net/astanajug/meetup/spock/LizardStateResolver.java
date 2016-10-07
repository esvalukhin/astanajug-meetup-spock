package net.astanajug.meetup.spock;

/**
 * @author Eugene Svalukhin.
 */
public class LizardStateResolver implements StateResolver {
    @Override
    public boolean tryToBeat(State checkedState) {
        switch (checkedState) {
            case SPOCK:
            case OUT:
            case PAPER:
                return true;
        }
        return false;
    }
}
