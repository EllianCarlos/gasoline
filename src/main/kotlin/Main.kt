import core.MyTests
import core.TestReporter
import core.TestRunner

fun main() {
    TestRunner
        .runTests(MyTests())
        .let(TestReporter::generateReport)
        .also(::println)
}
