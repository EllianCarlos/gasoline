# TODO

This file tracks the planned features and improvements for the Gasoline test framework.

## High Priority
- [x] **Parameterized Tests:** Introduce the ability to run a single test method with multiple sets of input data.
- [ ] **Test Suites:** Implement a mechanism to group multiple test classes together and run them as a single unit.
- [ ] **Asynchronous Code Support:** Add built-in support for testing asynchronous operations, particularly Kotlin Coroutines.
- [ ] **Expand Assertion Library:** Add more matchers, especially for collections and verifying exceptions (`assertThrows`).
- [ ] **Advanced Reporting:** Enhance the test reporter to generate more detailed output, including timing information, stack traces, and different formats (e.g., XML, HTML).

## Medium Priority
- [ ] **Mocking and Stubbing:** Integrate a simple, lightweight mocking or spying mechanism to help isolate dependencies.
- [ ] **Gradle Plugin:** Develop a Gradle plugin to simplify test execution and integration into the build process.
- [ ] **Test Filtering:** Provide a way to run specific tests based on names, tags, or other criteria.
- [ ] **Conditional Test Execution:** Allow tests to be run based on specific conditions (e.g., platform, configuration).
- [ ] **Test Execution Control:** Add options for test ordering (e.g., random) and timeouts.

## Low Priority
- [ ] **Command-Line Interface (CLI):** Create a CLI for running tests outside of an IDE or build tool.
- [ ] **Official Documentation:** Create comprehensive documentation to make the framework easier to adopt and use.
- [ ] **Assertions on Threads:** Add support for testing multi-threaded code.

## Completed
- [x] **Core Test Annotations:** `@Test`, `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`, `@Disabled`.
- [x] **Basic Assertions:** `isEqualTo`, `isNull`, `isNotNull`, `contains`, etc.
- [x] **Automatic Test Discovery:** The runner automatically finds and executes tests.
