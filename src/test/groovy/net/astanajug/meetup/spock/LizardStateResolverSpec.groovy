package net.astanajug.meetup.spock

import spock.lang.Specification
import spock.lang.Unroll


/**
 * @author Eugene Svalukhin.
 */
class LizardStateResolverSpec extends Specification {

    @Unroll
    def "Lizard should use game rules"() {
        given: "Spock state resolver"
        def resolver = new LizardStateResolver()

        expect: "resolver beats states according game rules"
        resolver.tryToBeat(state) == beaten

        where:
        state          | beaten
        State.LIZARD   | false
        State.OUT      | true
        State.PAPER    | true
        State.ROCK     | false
        State.SCISSORS | false
        State.SPOCK    | true
    }

}