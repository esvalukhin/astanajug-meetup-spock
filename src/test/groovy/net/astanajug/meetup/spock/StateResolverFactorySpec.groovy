package net.astanajug.meetup.spock

import spock.lang.Specification


/**
 * @author Eugene Svalukhin.
 */
class StateResolverFactorySpec extends Specification {

    def "Factory should produce resolvers according states"() {
        given: "State resolver factory"
        def factory = new StateResolverFactory()

        when: "factory produces state resolver"
        def resolver = factory.produce(state)

        then: "resolver should be compatible with state"
        resolver.getClass() == type

        where:
        state          | type
        State.LIZARD   | LizardStateResolver.class
        State.SPOCK    | SpockStateResolver.class
        State.SCISSORS | ScissorsStateResolver.class
        State.ROCK     | RockStateResolver.class
        State.PAPER    | PaperStateResolver.class
    }

    def "Factory should generate IllegalStateException for unsupported states"() {
        given: "State resolver factory"
        def factory = new StateResolverFactory()

        when: "Factory receives unsupported state"
        factory.produce(State.OUT)

        then: "IllegalStateException are thrown"
        def exception = thrown(IllegalStateException)
        with (exception) {
            message == "Unsupported state"
        }
    }

    def "Factory should generate NullPointerException if null parameter is received"() {
        given: "State resolver factory"
        def factory = new StateResolverFactory()

        when:
        factory.produce(null)

        then:
        thrown(NullPointerException)
    }

}