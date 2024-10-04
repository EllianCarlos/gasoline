package core

data class TestSummary(
    val className: String,
    val results: List<TestResult>,
    val totalTests: Int = results.size,
    val passedTests: Int = results.count { it is TestResult.Success },
    val failedTests: Int = results.count { it is TestResult.Failure },
)
