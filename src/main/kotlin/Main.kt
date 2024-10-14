import core.MyTests
import core.TestReporter
import core.TestRunner

fun main() {
    TestRunner
        .preSuitRunner(MyTests())
        .runTests(MyTests())
        .let(TestReporter::generateReport)
        .also(::println)
}
