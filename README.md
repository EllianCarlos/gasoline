# Gasoline: Project Overview

## Project Summary

Gasoline is a homemade, multiplatform-first test framework for Kotlin. Its primary goal is to provide a simple, lightweight, and intuitive testing experience, drawing inspiration from established frameworks like JUnit and Kotest but with a focus on minimalism and idiomatic functional Kotlin.

The framework currently supports:
- Test discovery via `@Test` annotations.
- Test lifecycle management with `@BeforeAll`, `@AfterAll`, `@BeforeEach`, and `@AfterEach` annotations.
- A fluent assertion library for validating results.
- A basic test runner and reporter that summarizes test outcomes.

## Tenets

The development of Gasoline is guided by the following principles:

*   **Simplicity and Minimalism:** The framework should be easy to understand, use, and maintain. It provides essential features without unnecessary complexity.
*   **Multiplatform by Default:** It is designed from the ground up to be compatible with multiple Kotlin platforms (e.g., JVM, Native).
*   **Idiomatic Kotlin:** The framework is written in a way that is natural for Kotlin developers, leveraging language features like annotations and extension functions.
*   **Convention over Configuration:** It relies on sensible defaults and conventions to minimize the amount of configuration required to write and run tests.
*   **Functional Programming for Testing:** It encourages the use of pure functions and immutable data in tests to create predictable, side-effect-free, and maintainable test suites.

## Running

To build the application from scratch just do:
```
nix build --impure
```
The impure flag is a tech debt of skill issues in nix. 


If you want to make development changes on an interactive terminal:
```
nix develop
```

This project uses [devenv](https://devenv.sh/).

## Troubleshooting

### Assertions never fails

This package uses assertions from `kotlin.test`, to enable this you need to run the application with the `-ea` in the JVM options.

## Next Steps for Improvement

To enhance the capabilities and usability of Gasoline, the following features and improvements are planned:

*   **Parameterized Tests:** Introduce the ability to run a single test method with multiple sets of input data.
*   **Test Suites:** Implement a mechanism to group multiple test classes together and run them as a single unit.
*   **Asynchronous Code Support:** Add built-in support for testing asynchronous operations, particularly Kotlin Coroutines.
*   **Mocking Framework:** Integrate a simple, lightweight mocking or spying mechanism to help isolate dependencies.
*   **Advanced Reporting:** Enhance the test reporter to generate more detailed output, including timing information, stack traces, and different formats (e.g., XML, HTML).
*   **Test Filtering:** Provide a way to run specific tests based on names, tags, or other criteria.
*   **Gradle Plugin:** Develop a Gradle plugin to simplify test execution and integration into the build process.
*   **Command-Line Interface (CLI):** Create a CLI for running tests outside of an IDE or build tool.
*   **Expand Assertion Library:** Add more matchers for different data types and conditions to the assertion library.
*   **Official Documentation:** Create comprehensive documentation to make the framework easier to adopt and use.
