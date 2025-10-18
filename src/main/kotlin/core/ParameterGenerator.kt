package core

interface ParameterGenerator<out T> {
    fun generate(): Sequence<T>
}
