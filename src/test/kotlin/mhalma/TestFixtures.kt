package mhalma

import java.io.File

data class ProjectDetails(
    val projectDir: File, val projectName: String, val packageDir: String, val mainClassName: String, val mainClass: String,
    val buildScriptName: String, val buildScript: String, val settingsFileName: String, val testClass: String) {
    override fun toString(): String = projectName
}

fun kotlinProjectDetails(projectDir: File) =
    ProjectDetails(
        projectDir = projectDir,
        projectName = "project-kotlin",
        packageDir = "src/main/kotlin/mhalma",
        mainClassName = "Application.kt",
        mainClass = """
          package mhalma
    
          fun main(args: Array<String>) {
            println("main")
          }
        """.trimIndent(),
        buildScriptName = "build.gradle.kts",
        buildScript = """
          plugins {
              kotlin("jvm") version "1.3.72"
          }
          repositories {
            mavenLocal()
            mavenCentral()
          }
          dependencies {
            implementation(kotlin("stdlib-jdk8"))
            testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
            testImplementation("org.assertj:assertj-core:3.16.1")
          }
        """.trimIndent(),
        settingsFileName = "settings.gradle.kts",
        testClass = """
          package mhalma
          
          import org.assertj.core.api.Assertions.assertThat
          import org.junit.jupiter.api.Test
          
          class ApplicationTest {
              @Test
              fun `A Test`() {
                  assertThat("anything").isEqualTo("anything")
              }
          }
        """.trimIndent()
    )
