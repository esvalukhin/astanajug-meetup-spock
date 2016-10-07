package net.astanajug.meetup.spock;

import lombok.NonNull;
import lombok.Setter;

import java.util.Optional;

/**
 * @author Eugene Svalukhin.
 */
public class StateResolveEngine {

    @Setter
    @NonNull
    private StateResolverFactory stateFactory;

    public Optional<Player> findWinner(@NonNull Player one, @NonNull Player two) {
//        if (one.getState() == State.ROCK && two.getState() == State.PAPER) {
//            return Optional.of(two);
//        }
        if (one.getState() == two.getState()) {
            return Optional.empty();
        }

        return stateFactory.produce(one.getState()).tryToBeat(two.getState()) ? Optional.of(one) : Optional.of(two);
    }
}
