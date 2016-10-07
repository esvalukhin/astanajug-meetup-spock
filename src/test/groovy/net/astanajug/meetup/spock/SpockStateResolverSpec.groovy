package net.astanajug.meetup.spock

import spock.lang.Unroll


/**
 * @author Eugene Svalukhin.
 */
class SpockStateResolverSpec extends spock.lang.Specification {

    def "Spock should smash scissors"() {
        given: "Scissors state"
        def scissors = State.SCISSORS

        and: "Spock state resolver"
        def resolver = new SpockStateResolver()

        when: "State resolver try to beat"
        def beaten = resolver.tryToBeat(scissors)

        then: "Scissors are beaten"
        beaten
    }

    def "Spock can't beat himself"() {
        given: "Spock state"
        def state = State.SPOCK

        and: "Spock state resolver"
        def resolver = new SpockStateResolver()

        when: "Resolver try to beat"
        def beaten = resolver.tryToBeat(state)

        then: "State are not beaten"
        !beaten
    }

    def "Spock should beat scissors and rock"() {
        given: "Scissors state"
        def scissors = State.SCISSORS

        and: "Rock state"
        def rock = State.ROCK

        and: "Spock state resolver"
        def resolver = new SpockStateResolver()

        when: "State resolver try to beat"
        def beatenScissors = resolver.tryToBeat(scissors)
        def beatenRock = resolver.tryToBeat(rock)

        then: "Scissors and rock are beaten"
        beatenRock
        beatenScissors
    }

    @Unroll
    def "Spock should use game rules"() {
        given: "Spock state resolver"
        def resolver = new SpockStateResolver()

        expect: "resolver beats state according game rules"
        resolver.tryToBeat(state) == beaten

        where:
        state          | beaten
        State.LIZARD   | false
        State.OUT      | true
        State.PAPER    | false
        State.ROCK     | true
        State.SCISSORS | true
        State.SPOCK    | false
    }
}
