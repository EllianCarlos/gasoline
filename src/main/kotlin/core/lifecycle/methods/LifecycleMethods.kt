package core.lifecycle.methods

import core.lifecycle.AfterAll
import core.lifecycle.AfterEach
import core.lifecycle.BeforeAll
import core.lifecycle.BeforeEach
import java.lang.reflect.Method

object LifecycleMethods {
    val perClass =
        listOf(
            BeforeAll::class.java,
            AfterAll::class.java,
        )

    val perMethod =
        listOf(
            BeforeEach::class.java,
            AfterEach::class.java,
        )

    val all = perClass.plus(perMethod)
}

fun Method.isLifecycleMethod() = LifecycleMethods.all.any { this.isAnnotationPresent(it) }

fun Method.isPerClass() = LifecycleMethods.perClass.any { this.isAnnotationPresent(it) }

fun Method.isPerMethod() = LifecycleMethods.perMethod.any { this.isAnnotationPresent(it) }
