package net.astanajug.meetup.spock

import spock.lang.Specification
import spock.lang.Subject


/**
 * @author Eugene Svalukhin.
 */
class GameRunnerSpec extends Specification {

    @Subject
    GameRunner gameRunner

    def setup() {
        gameRunner = new GameRunner()
    }

    def "Should throw NullPointerException if one of player is null in two player game"() {
        given: "First pair of players"
        def firstPlayerOfPairOne = null;
        def secondPlayerOfPairOne = new Player("second", State.ROCK)

        and: "Second pair of players"
        def firstPlayerOfPairTwo = new Player("first", State.PAPER)
        def secondPlayerOfPairTwo = null

        when:
        gameRunner.doublePlayerGame(firstPlayerOfPairOne, secondPlayerOfPairOne)

        then:
        def exception = thrown(NullPointerException)
        exception

        when:
        gameRunner.doublePlayerGame(firstPlayerOfPairTwo, secondPlayerOfPairTwo)

        then:
        exception = thrown(NullPointerException)
        exception
    }

    def "Game runner should throw if one of player is null in multiplayer game"() {
        given: "Three players"
        def firstPlayer = new Player("first", State.ROCK)
        def secondPlayer = new Player("second", State.PAPER)
        def thirdPlayer = null

        when:
        gameRunner.nPlayerGame([firstPlayer, secondPlayer, thirdPlayer])

        then:
        thrown(NullPointerException)
    }

    def "Game runner should return name of winner in two player game"() {
        given: "Two players with names"
        def firstPlayer = new Player("first", State.ROCK)
        def secondPlayer = new Player("second", State.PAPER)

        and: "State resolve engine"
        def engine = new StateResolveEngine()
        engine.stateFactory = new StateResolverFactory()
        gameRunner.resolveEngine = engine

        when:
        def winnerName = gameRunner.doublePlayerGame(firstPlayer, secondPlayer).get()

        then:
        winnerName == "second"
    }

    def "Game runner should return 'nobody' if players have identical state"() {
        given: "Two players with names"
        def firstPlayer = new Player("first", State.ROCK)
        def secondPlayer = new Player("second", State.ROCK)

        and: "State resolve engine"
        def engine = new StateResolveEngine()
        engine.stateFactory = new StateResolverFactory()
        gameRunner.resolveEngine = engine

        when:
        def winnerName = gameRunner.doublePlayerGame(firstPlayer, secondPlayer).get()

        then:
        winnerName == "nobody"
    }

    def "Game runner should find winners in multi player game"() {
        given: "Players"
        def players = [new Player("first", State.ROCK),
                       new Player("second", State.SCISSORS),
                       new Player("third", State.SPOCK)]

        and: "State resolve engine"
        def engine = new StateResolveEngine()
        engine.stateFactory = new StateResolverFactory()
        gameRunner.resolveEngine = engine

        when:
        def winners = gameRunner.nPlayerGame(players)

        then:
        winners.size() == 2
        winners == ["first", "third"] as Set

    }

    def "Game runner should returns 'nobody' if all players have identical state"() {
        given: "Players"
        def players = [new Player("first", State.SPOCK), new Player("second", State.SPOCK), new Player("third", State.SPOCK)]

        and: "State resolve engine"
        def engine = new StateResolveEngine()
        engine.stateFactory = new StateResolverFactory()
        gameRunner.resolveEngine = engine

        when:
        def winners = gameRunner.nPlayerGame(players)

        then:
        winners.size() == 1
        winners == ["nobody"] as Set
    }

    def "Game runner should use engine"() {
        given: "Players"
        def players = [new Player("first", State.SPOCK), new Player("second", State.SPOCK), new Player("third", State.SPOCK), new Player("forth", State.SPOCK)]

        and: "Mocked engine"
        StateResolveEngine engine = Mock()
        gameRunner.resolveEngine = engine

        when:
        def winners = gameRunner.nPlayerGame(players)

        then:
        winners.size() == 1
        winners == ["nobody"] as Set
        (players.size() * (players.size() -1)) * engine.findWinner(_, _) >> {Player one, Player two -> Optional.empty()}

    }

}