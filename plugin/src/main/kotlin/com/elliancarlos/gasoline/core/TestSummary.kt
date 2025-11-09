package com.elliancarlos.gasoline.core

data class TestSummary(
    val className: String,
    val results: Sequence<TestResult>,
    val totalTests: Int = results.count(),
    val passedTests: Int = results.count { it is TestResult.Success },
    val failedTests: Int = results.count { it is TestResult.Failure },
)
