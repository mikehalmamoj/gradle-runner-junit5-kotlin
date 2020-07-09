# Update #
Turned out that we were excluding the wrong module. Changing`springBootTest.exclude(mapOf("group" to "junit", "module" to "junit"))` to `springBootTest.exclude(mapOf("group" to "junit", "module" to "org.junit.vintage"))` fixed the problem.


# gradle-runner-junit5-kotlin


Since we tried to update our Gradle plugin to use Gradle 6.4 some tests that assert JUnit 5 is working have started failing.

The problem appears to be that when using the Gradle Runner to build and run a project in a test, it somehow picks junit-vintage as the test engine even though it's been excluded from the project.  It can't find junit-vintage, so it falls over.

This project is a minimal implementation to demonstrate the problem.

To reproduce:
* Check out the project and run `./gradlew test`

To workaround the problem:
* Switch to Gradle 6.3 with command `./gradlew wrapper --gradle-version 6.3`
* Run command `./gradlew test`
* (No idea why this works)

An alternative workaround:
* Switch back to Gradle 6.4!
* In function MyPlugin#addDependencies, comment out line `springBootTest.exclude(mapOf("group" to "junit", "module" to "junit"))`
* Run command `./gradlew test`
* (The test now works because with junit 4 back on the classpath the wrongly chosen engine is now available)
