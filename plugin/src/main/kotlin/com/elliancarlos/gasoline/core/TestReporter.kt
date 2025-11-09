package com.elliancarlos.gasoline.core

// Pure function for formatting output
object TestReporter {
    fun generateReport(summary: TestSummary): String =
        buildString {
            appendLine("Running tests for ${summary.className}")
            appendLine("----------------------------------------")

            summary.results.forEach { result ->
                when (result) {
                    is TestResult.Success ->
                        appendLine("✅ ${result.testName} - PASSED")
                    is TestResult.Failure -> {
                        appendLine("❌ ${result.testName} - FAILED")
                        appendLine("   Error: ${result.error}")
                    }
                }
            }

            appendLine("\nTest Summary:")
            appendLine("----------------------------------------")
            appendLine("Total tests: ${summary.totalTests}")
            appendLine("Passed: ${summary.passedTests}")
            appendLine("Failed: ${summary.failedTests}")
        }
}
