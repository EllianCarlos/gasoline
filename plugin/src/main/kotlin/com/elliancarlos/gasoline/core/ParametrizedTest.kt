package com.elliancarlos.gasoline.core

import kotlin.reflect.KClass

@Test
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
annotation class ParametrizedTest(
    val generator: KClass<out ParameterGenerator<*>>,
)
