package core

interface ParameterGenerator<out T> {
    fun generate(): List<T>
}
