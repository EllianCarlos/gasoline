package core

// Extension functions for better readability
fun List<TestResult>.toSummary(className: String): TestSummary = TestSummary(className, this)
