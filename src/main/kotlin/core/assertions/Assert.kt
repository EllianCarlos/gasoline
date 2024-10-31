package core.assertions

data class Assert<T>(
    val actual: T,
) {
    companion object {
        fun <T> assertThat(actual: T): Assert<T> = Assert(actual)
    }

    infix fun isEqualTo(expected: T): Assert<T> {
        if (actual != expected) {
            throw AssertionError("Expected $expected but was $actual")
        }
        return this
    }

    fun isNull(): Assert<T> {
        if (actual != null) {
            throw AssertionError("Expected null but was $actual")
        }
        return this
    }

    fun isNotNull(): Assert<T> {
        if (actual == null) {
            throw AssertionError("Expected non-null value but was null")
        }
        return this
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified C : Collection<E>, reified E> hasSize(expected: Int): Assert<C> {
        actual as? C ?: throw AssertionError("Expected a Collection of ${E::class} but was ${actual!!::class.simpleName}")

        if (actual.size != expected) {
            throw AssertionError("Expected size $expected but was ${actual.size}")
        }
        return this as Assert<C>
    }

    infix fun contains(substring: String): Assert<T> {
        if (actual !is String) {
            throw AssertionError("Expected a String but was ${actual!!::class.simpleName}")
        }

        if (!actual.contains(substring)) {
            throw AssertionError("Expected '$actual' to contain '$substring'")
        }

        return this
    }

    inline infix fun satisfies(crossinline predicate: (T) -> Boolean): Assert<T> {
        if (!predicate(actual)) {
            throw AssertionError("Value $actual did not satisfy the predicate")
        }
        return this
    }
}

inline fun <reified T> T.assertThat(): Assert<T> = Assert(this)

inline fun <reified T> assertion(
    description: String,
    crossinline assertion: Assert<T>.() -> Unit,
): (Assert<T>) -> Assert<T> =
    { assert ->
        try {
            assert.assertion()
            assert
        } catch (e: AssertionError) {
            throw AssertionError("$description: ${e.message}")
        }
    }
