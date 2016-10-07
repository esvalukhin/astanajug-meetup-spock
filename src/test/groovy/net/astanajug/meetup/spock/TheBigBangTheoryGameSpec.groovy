package net.astanajug.meetup.spock

import spock.lang.Specification
import spock.lang.Unroll


/**
 * @author Eugene Svalukhin.
 */
class TheBigBangTheoryGameSpec extends Specification {

    @Unroll
    def "Game between heroes of the Big Bang Theory series"() {
        given: "Heroes"
        def heroes = [new Player("Sheldon", sheldon),
                      new Player("Leonard", leonard),
                      new Player("Howard", howard),
                      new Player("Raj", raj)]

        and: "Configured game runner"
        def bigBangTheoryGame = new GameRunner()
        bigBangTheoryGame.resolveEngine = new StateResolveEngine()
        bigBangTheoryGame.resolveEngine.stateFactory = new StateResolverFactory()

        when:
        def winners = bigBangTheoryGame.nPlayerGame(heroes);

        then:
        winners == result as Set

        where:
        sheldon        | leonard        | howard         | raj            || result
        State.SPOCK    | State.SPOCK    | State.SPOCK    | State.SPOCK    || ["nobody"]
        State.SPOCK    | State.SCISSORS | State.PAPER    | State.SPOCK    || ["Sheldon", "Leonard", "Howard", "Raj"]
        State.LIZARD   | State.SPOCK    | State.SCISSORS | State.PAPER    || ["Sheldon", "Leonard", "Howard", "Raj"]
        State.PAPER    | State.PAPER    | State.LIZARD   | State.SCISSORS || ["Howard", "Raj"]
    }

}