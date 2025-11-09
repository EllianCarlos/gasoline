package com.elliancarlos.gasoline.core

interface ParameterGenerator<out T> {
    fun generate(): Sequence<T>
}
