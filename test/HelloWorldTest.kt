import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

// TODO: Decide which test style to use
// https://kotest.io/docs/framework/testing-styles.html#behavior-spec

class HelloWorldTest : BehaviorSpec({
    given("A String of the value 'Hello World'") {
        val helloWorld = "Hello World"

        then("it should have the value 'Hello World'") {
            helloWorld shouldBe "Hello World"
        }
    }
})