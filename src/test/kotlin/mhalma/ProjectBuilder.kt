package mhalma

import org.gradle.testkit.runner.GradleRunner
import java.io.File
import java.nio.file.Files

fun makeProject(projectDetails: ProjectDetails) {
  with(projectDetails) {
    makeBuildScript(
        projectDir,
        buildScriptName,
        buildScript
    )
      makeSrcFile(
          projectDir,
          packageDir,
          mainClassName,
          mainClass
      )
      makeTestSrcFile(
          projectDir,
          packageDir,
          mainClassName,
          testClass
      )
      makeSettingsScript(
          projectDir,
          settingsFileName,
          projectName
      )
  }
}

private fun makeSrcFile(projectDir: File, packageDir: String, mainClassName: String, mainClass: String) {
  val srcDir = File(projectDir, packageDir)
  srcDir.mkdirs()
  val srcFile = File(srcDir, mainClassName)
  Files.writeString(srcFile.toPath(), mainClass)
}

private fun makeTestSrcFile(projectDir: File, packageDir: String, mainClassName: String, testClass: String) {
  val srcDir = File(projectDir, packageDir.replace("main", "test"))
  srcDir.mkdirs()
  val srcFile = File(srcDir, mainClassName.replace(".kt", "Test.kt"))
  Files.writeString(srcFile.toPath(), testClass)
}

private fun makeBuildScript(projectDir: File, buildScriptName: String, buildScript: String) {
  val buildFile = File(projectDir, buildScriptName)
  Files.writeString(buildFile.toPath(), buildScript)
}

private fun makeSettingsScript(projectDir: File, settingsFileName: String, projectName: String) {
  val settingsFile = File(projectDir, settingsFileName)
  val settingsScript = """
        pluginManagement {
          repositories {
            mavenLocal()
            gradlePluginPortal()
          }
        }
        rootProject.name = "$projectName"
      """.trimIndent()
  Files.writeString(settingsFile.toPath(), settingsScript)
}

fun buildProject(projectDir: File, vararg arguments: String) =
    projectBuilder(projectDir, *arguments).build()

private fun projectBuilder(projectDir: File, vararg arguments: String) =
    GradleRunner.create()
        .withProjectDir(projectDir)
        .withArguments("clean", *arguments)
        .withPluginClasspath()
