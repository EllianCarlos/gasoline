package core

import java.lang.reflect.Method

// Pure functions for test execution
object TestRunner {
    fun runTests(testClass: Any): TestSummary =
        testClass::class.java
            .declaredMethods
            .asSequence()
            .filter { it.isAnnotationPresent(Test::class.java) and it.isAnnotationPresent(Disabled::class.java).not() }
            .map { method -> executeTest(method, testClass) }
            .toList()
            .toSummary(testClass::class.simpleName ?: "Unknown")

    private fun executeTest(
        method: Method,
        instance: Any,
    ): TestResult =
        try {
            method.isAccessible = true
            method.invoke(instance)
            TestResult.Success(method.name)
        } catch (e: Exception) {
            TestResult.Failure(
                testName = method.name,
                error = e.cause?.message ?: e.message ?: "Unknown error",
            )
        }
}
