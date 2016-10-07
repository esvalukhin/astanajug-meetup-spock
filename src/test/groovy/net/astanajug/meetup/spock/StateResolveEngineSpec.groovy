package net.astanajug.meetup.spock

import spock.lang.Specification
import spock.lang.Subject


/**
 * @author Eugene Svalukhin.
 */
class StateResolveEngineSpec extends Specification {

    @Subject
    StateResolveEngine engine

    def setup() {
        engine = new StateResolveEngine()
    }

//    def "Engine should find winner between two players with different states"() {
//        given: "Two players"
//        def playerOne = new Player("one", State.ROCK)
//        def playerTwo = new Player("two", State.PAPER)
//
//        expect: "player two is winner"
//        engine.findWinner(playerOne, playerTwo).get() == playerTwo
//    }

    def "Engine can't find winner if players have identical state"() {
        given: "Two player"
        def playerOne = new Player("one", state)
        def playerTwo = new Player("two", state)

        when: "try to find winner"
        def winner = engine.findWinner playerOne, playerTwo

        then: "Nobody wins"
        winner.isPresent() == result

        where:
        state           | result
        State.SPOCK     | false
        State.ROCK      | false
        State.SCISSORS  | false
        State.LIZARD    | false
        State.PAPER     | false
        State.OUT       | false
    }

    def "Engine should use state factory and state resolver"() {
        given: "Two players"
        def first = new Player("first", State.ROCK)
        def second = new Player("second", State.PAPER)

        and: "Mocked factory"
        StateResolverFactory factory = Mock()
        engine.stateFactory = factory

        and: "Mocked state resolver"
        StateResolver stateResolver = Mock()

        when: "Engine try to find winner"
        def winner = engine.findWinner(first, second)

        then: "Second player is winner"
        winner.get() == second

        and: "Factory was called"
        1 * factory.produce(first.getState()) >> stateResolver

        and: "State resolver was called"
        1 * stateResolver.tryToBeat(_ as State) >> false

    }

    def "Engine should produce exception if one of parameters is null"() {
        given: "First pair of players"
        def firstPlayerOfPairOne = null;
        def secondPlayerOfPairOne = new Player("second", State.ROCK)

        and: "Second pair of players"
        def firstPlayerOfPairTwo = new Player("first", State.PAPER)
        def secondPlayerOfPairTwo = null

        when:
        engine.findWinner firstPlayerOfPairOne, secondPlayerOfPairOne
        then:
        thrown(NullPointerException)

        when:
        engine.findWinner firstPlayerOfPairTwo, secondPlayerOfPairTwo

        then:
        thrown(NullPointerException)

    }


}