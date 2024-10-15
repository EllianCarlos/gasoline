import core.MyTests
import core.TestReporter
import core.TestRunner
import core.validator.TestValidator

fun main() {
    MyTests()
        .also {
            TestValidator.validate(it)
        }.also {
            TestRunner
                .runTests(it)
                .let(TestReporter::generateReport)
                .also(::println)
        }
}
