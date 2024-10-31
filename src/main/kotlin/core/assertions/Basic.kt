package core.assertions

inline infix fun <reified T> Assert<T>.isEqualTo(expected: T): Assert<T> =
    apply {
        if (actual != expected) {
            throw AssertionException("Expected $expected but was $actual")
        }
    }

inline fun <reified T> Assert<T>.isNull(): Assert<T> =
    apply {
        if (actual != null) {
            throw AssertionException("Expected null but was $actual")
        }
    }

inline fun <reified T> Assert<T>.isNotNull(): Assert<T> =
    apply {
        if (actual == null) {
            throw AssertionException("Expected non-null value but was null")
        }
    }

inline infix fun <reified T> Assert<T>.satisfies(crossinline predicate: (T) -> Boolean): Assert<T> =
    apply {
        if (!predicate(actual)) {
            throw AssertionException("Value $actual did not satisfy the predicate")
        }
    }
