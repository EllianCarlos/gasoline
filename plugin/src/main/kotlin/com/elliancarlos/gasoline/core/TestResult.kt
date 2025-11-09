package com.elliancarlos.gasoline.core

sealed class TestResult {
    data class Success(
        val testName: String,
    ) : TestResult()

    data class Failure(
        val testName: String,
        val error: String,
    ) : TestResult()
}
