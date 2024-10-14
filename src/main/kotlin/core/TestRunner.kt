package core

import core.exceptions.InvalidTestSuit
import core.lifecycle.BeforeAll
import java.lang.reflect.Method

/**
 * TestRunner rules:
 * - A Test cannot be at the same time any lifecycle method
 */

object TestRunner {
    fun preSuitRunner(testClass: Any) =
        this.also {
            testClass::class.java
                .declaredMethods
                .asList()
                .filter {
                    it.isAnnotationPresent(BeforeAll::class.java) and it.isAnnotationPresent(Disabled::class.java).not()
                }.map {
                    val isValid =
                        it.isAnnotationPresent(BeforeAll::class.java) and
                            it.isAnnotationPresent(Test::class.java).not()
                    if (isValid) return@map it else throw InvalidTestSuit("BeforeAll cannot be a Test")
                }.also { if (it.size > 1) InvalidTestSuit("There can only be one BeforeAll per Test Suit") }
                // TODO:
                // Probably validations should be in a pre execution stage, I wouldn't want my tests to suddenly fail because I misplaced a decorator or forgot to delete something.
                // Separate into test runner and test validator!
                .firstOrNull()
                ?.let { executeMethod(it, testClass) }
        }

    fun runTests(testClass: Any): TestSummary =
        testClass::class.java
            .declaredMethods
            .asSequence()
            .filter { it.isAnnotationPresent(Disabled::class.java).not() and it.isTest() }
            .map { executeTest(it, testClass) }
            .toList()
            .toSummary(testClass::class.simpleName ?: "Unknown")

    private fun executeMethod(
        method: Method,
        instance: Any,
    ) {
        method.isAccessible = true
        method.invoke(instance)
    }

    private fun executeTest(
        method: Method,
        instance: Any,
    ): TestResult =
        try {
            executeMethod(method, instance)
            TestResult.Success(method.name)
        } catch (e: Exception) {
            TestResult.Failure(
                testName = method.name,
                error = e.cause?.message ?: e.message ?: "Unknown error",
            )
        }
}

private fun Method.isTest(): Boolean = this.isAnnotationPresent(Test::class.java)
