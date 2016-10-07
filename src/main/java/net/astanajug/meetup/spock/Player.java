package net.astanajug.meetup.spock;

import lombok.*;

/**
 * @author Eugene Svalukhin.
 */
@Data
public class Player {

    public static Player NOBODY_PLAYER = new Player("nobody", State.OUT);

    @Getter
    @NonNull
    private String name;

    @Getter
    @NonNull
    private State state;

}
