package core

// Example usage with composition
class MyTests {
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

    @Test
    fun testFailingTest() {
        val result = 2 * 2
        assert(result == 5) { "Expected 5 but got $result" }
    }
}
