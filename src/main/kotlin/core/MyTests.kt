package core

import core.assertions.Assert
import core.assertions.assertThat
import core.lifecycle.AfterAll
import core.lifecycle.AfterEach
import core.lifecycle.BeforeAll
import core.lifecycle.BeforeEach

// Example test class
class MyTests {
    @BeforeAll
    fun beforeAll() {
        println("BeforeAll")
    }

    @AfterAll
    fun afterAll() {
        println("AfterAll")
    }

    @BeforeEach
    fun beforeEach() {
        println("BeforeEach")
    }

    @AfterEach
    fun afterEach() {
        println("AfterEach")
    }

    @Test
    fun testAssertions() {
        Assert.assertThat(83).isNotNull()
        42.assertThat().isEqualTo(42)

        "Hello".assertThat().contains("ell")

        try {
            42.assertThat().contains("something")
        } catch (e: AssertionError) {
            println(e.message)
        }

        val list = listOf(1, 2, 3)
        list.assertThat().hasSize<List<Int>, Int>(3)

        42.assertThat().satisfies { it > 40 }

        data class RandomClass(
            val name: String? = null,
            val surname: String? = "anySurname",
        )

        RandomClass().name.assertThat().isNull()
        RandomClass().surname.assertThat().isNotNull()
    }

    @Test
    fun testAddition() {
        val result = 2 + 2
        result.assertThat().isEqualTo(4)
    }

    @Test
    fun testSubtraction() {
        val result = 5 - 3
        assert(result == 2) { "Expected 2 but got $result" }
    }

    @Disabled
    @Test
    fun testFailingTest() {
        val result = 2 * 2
        assert(result == 5) { "Expected 5 but got $result" }
    }
}
