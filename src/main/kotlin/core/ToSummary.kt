package core

// Extension functions for better readability
fun Sequence<TestResult>.toSummary(className: String): TestSummary = TestSummary(className, this)
