package net.astanajug.meetup.spock

import spock.lang.Specification
import spock.lang.Unroll


/**
 * @author Eugene Svalukhin.
 */
class ClassicStatesResolverSpec extends Specification {

    @Unroll
    def "Classic states should use updated game rules"() {
        given: "Rock state resolver"
        def rockResolver = new RockStateResolver()

        and: "Paper state resolver"
        def paperResolver = new PaperStateResolver()

        and: "Scissors state resolver"
        def scissorsResolver = new ScissorsStateResolver()

        expect: "Resolvers beats states according game rules"
        rockResolver.tryToBeat(state) == beatenByRock
        paperResolver.tryToBeat(state) == beatenByPaper
        scissorsResolver.tryToBeat(state) == beatenByScissors

        where:
        state          || beatenByRock | beatenByPaper | beatenByScissors
        State.LIZARD   || true        | false         | true
        State.OUT      || true         | true          | true
        State.PAPER    || false        | false         | true
        State.ROCK     || false        | true          | false
        State.SCISSORS || true         | false         | false
        State.SPOCK    || false        | true          | false
    }

}