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
              id("mhalma.my-plugin") version "0.1.0"
          }
        """.trimIndent(),
        settingsFileName = "settings.gradle.kts",
        testClass = """
          package mhalma
          
          import org.junit.jupiter.api.Test
          import org.junit.jupiter.api.Assertions.assertEquals
          
          class ApplicationTest {
              @Test
              fun `A Test`() {
                  assertEquals("anything", "anything")
              }
          }
        """.trimIndent()
    )
