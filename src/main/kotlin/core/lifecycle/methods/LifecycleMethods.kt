package core.lifecycle.methods

import core.lifecycle.AfterAll
import core.lifecycle.AfterEach
import core.lifecycle.BeforeAll
import core.lifecycle.BeforeEach
import java.lang.reflect.Method

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

fun Method.isLifecycleMethod() = all.any { this.isAnnotationPresent(it) }

fun Method.isPerClass() = perClass.any { this.isAnnotationPresent(it) }
