package net.astanajug.meetup.spock;

import lombok.NonNull;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

import static net.astanajug.meetup.spock.Player.NOBODY_PLAYER;

/**
 * @author Eugene Svalukhin.
 */
public class GameRunner {

    @NonNull
    @Setter
    public StateResolveEngine resolveEngine;

    public Optional<String> doublePlayerGame(@NonNull Player one, @NonNull Player two) {
        return Optional.of(resolveEngine.findWinner(one, two).orElse(NOBODY_PLAYER).getName());
    }

    public Set<String> nPlayerGame(@NonNull List<Player> players) {
        if (players.stream().filter(Objects::isNull).findFirst().isPresent()) {
            throw new NullPointerException("One of the player in list is null");
        }

        Set<Player> winners = players.stream().collect(HashSet::new,
                (set, player) -> {
                    Set<Player> beatenPlayers = players.stream()
                            .filter(pl -> !pl.equals(player))
                            .filter(pl -> {
                                Optional<Player> winner = resolveEngine.findWinner(player, pl);
                                return winner.isPresent() && winner.get() == player;
                            })
                            .collect(Collectors.toSet());
                    if (!beatenPlayers.isEmpty()) {
                        set.add(player);
                        System.out.println(player.getName() + " beats " + beatenPlayers.stream().map(Player::getName).collect(Collectors.joining(", ")));
                    } else {
                        System.out.println(player.getName() + " doesn't beat anybody");
                    }
                    System.out.println("-------------------");
                },
                HashSet::addAll);


        return winners.isEmpty() ? Collections.singleton("nobody") : winners.stream().map(Player::getName).collect(Collectors.toSet());
    }
}
