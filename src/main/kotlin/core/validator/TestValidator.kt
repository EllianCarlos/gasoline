package core.validator

import core.Test
import core.exceptions.InvalidTestSuite
import core.lifecycle.methods.isLifecycleMethod
import java.lang.reflect.Method

object TestValidator {
    /**
     * TestValidator rules:
     * - A Test cannot be a lifecycle method
     * - A Test Suite cannot have multiple version of the same lifecycle method
     */
    fun validate(testClass: Any): Map<String, Int> {
        // map of Annotation simpleClassName to count of Presence in the same suite
        val lifecycleMethodsPresent = mutableMapOf<String, Int>()

        testClass::class.java
            .declaredMethods
            .map { validateMethod(it, lifecycleMethodsPresent) }

        return lifecycleMethodsPresent.toMap()
    }

    private fun validateMethod(
        method: Method,
        lifecycleMethodsPresent: MutableMap<String, Int>,
    ): Boolean {
        if (method.isTest() and method.isLifecycleMethod()) {
            throw InvalidTestSuite("Method ${method.name} is a lifecycle method and a test.")
        }

        validateLifecycleAnnotations(method, lifecycleMethodsPresent)

        return true
    }

    private fun validateLifecycleAnnotations(
        method: Method,
        lifecycleMethodsPresent: MutableMap<String, Int>,
    ) {
        method.annotations
            .filterNot { annotation -> annotation is Test }
            .map {
                validateLifecycleMethod(it, lifecycleMethodsPresent)
                it
            }
    }

    private fun validateLifecycleMethod(
        annotation: Annotation,
        lifecycleMethodsPresent: MutableMap<String, Int>,
    ) {
        val annotationName = annotation.annotationClass.simpleName.toString()
        if (lifecycleMethodsPresent.containsKey(annotationName)) {
            throw InvalidTestSuite("There are two ${annotation.annotationClass.simpleName} in the same test suite.")
        }
        lifecycleMethodsPresent[annotationName] = (lifecycleMethodsPresent[annotationName] ?: 0) + 1
    }
}

private fun Method.isTest(): Boolean = this.isAnnotationPresent(Test::class.java)
