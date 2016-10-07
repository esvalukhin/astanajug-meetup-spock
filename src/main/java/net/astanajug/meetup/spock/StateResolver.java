package net.astanajug.meetup.spock;

/**
 * @author Eugene Svalukhin.
 */
public interface StateResolver {
    boolean tryToBeat(State checkedState);
}
