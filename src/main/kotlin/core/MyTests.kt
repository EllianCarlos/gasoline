package core

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
    fun testAddition() {
        val result = 2 + 2
        assert(result == 4) { "Expected 4 but got $result" }
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
