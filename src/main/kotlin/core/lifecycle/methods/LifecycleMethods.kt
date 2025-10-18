package core.lifecycle.methods

import core.Disabled
import core.lifecycle.AfterAll
import core.lifecycle.AfterEach
import core.lifecycle.BeforeAll
import core.lifecycle.BeforeEach
import kotlin.collections.any
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.findAnnotations

object LifecycleMethods {
    val perClass =
        listOf(
            BeforeAll::class,
            AfterAll::class,
        )

    val perMethod =
        listOf(
            BeforeEach::class,
            AfterEach::class,
        )

    val all = perClass.plus(perMethod)
}

private fun KFunction<*>.findLifecycleAnnotations(klass: KClass<out Annotation>): Boolean {
    return this.findAnnotations(klass).isNotEmpty()
}

fun KFunction<*>.isLifecycleMethod() = LifecycleMethods.all.any(this::findLifecycleAnnotations)

fun KFunction<*>.isPerClass() = LifecycleMethods.perClass.any(this::findLifecycleAnnotations)

fun KFunction<*>.isPerMethod() = LifecycleMethods.perMethod.any(this::findLifecycleAnnotations)
