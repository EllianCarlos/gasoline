package core

import core.lifecycle.AfterAll
import core.lifecycle.AfterEach
import core.lifecycle.BeforeAll
import core.lifecycle.BeforeEach
import java.lang.reflect.Method

object TestRunner {
    private fun preSuite(testClass: Any) =
        this.also {
            findFirstMethodAnnotatedAndExecute<BeforeAll>(testClass)
        }

    private fun postSuite(testClass: Any) =
        this.also {
            findFirstMethodAnnotatedAndExecute<AfterAll>(testClass)
        }

    private fun preTest(testClass: Any) =
        this.also {
            findFirstMethodAnnotatedAndExecute<BeforeEach>(testClass)
        }

    private fun postTest(testClass: Any) =
        this.also {
            findFirstMethodAnnotatedAndExecute<AfterEach>(testClass)
        }

    fun runTests(testClass: Any): TestSummary {
        val methods = testClass::class.java.declaredMethods
        val testMethods = methods.filter { it.isTest() && !it.isAnnotationPresent(Disabled::class.java) }

        preSuite(testClass)
        val toReturn =
            testClass::class.java
                .declaredMethods
                .asSequence()
                .filter { it.isAnnotationPresent(Disabled::class.java).not() and it.isAnnotationPresent(Test::class.java) }
                .map {
                    preTest(testClass)
                    val ret = executeTest(it, testClass)
                    postTest(testClass)
                    return@map ret
                }.filterNotNull()
                .toList<TestResult>()
                .toSummary(testClass::class.simpleName ?: "Unknown")
        postSuite(testClass)
        return toReturn
    }

    private fun executeMethod(
        method: Method,
        instance: Any,
    ): TestResult? {
        method.isAccessible = true
        method.invoke(instance)
        return null
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

    private inline fun <
        reified predicatedAnnotation : Any,
    > findFirstMethodAnnotated(testClass: Any) =
        testClass::class.java
            .declaredMethods
            .firstOrNull { it.annotations.firstOrNull { annotation -> annotation is predicatedAnnotation } != null }

    private inline fun <
        reified predicatedAnnotation : Any,
    > findFirstMethodAnnotatedAndExecute(testClass: Any) =
        findFirstMethodAnnotated<predicatedAnnotation>(testClass)?.let { executeMethod(it, testClass) }
}

private fun Method.isTest(): Boolean = this.isAnnotationPresent(Test::class.java)
