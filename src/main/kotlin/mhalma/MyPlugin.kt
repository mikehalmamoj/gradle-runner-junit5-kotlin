package mhalma

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.tasks.testing.Test
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class MyPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.plugins.apply(KotlinPluginWrapper::class.java)

        applyRepositories(project)
        addDependencies(project)
        setKotlinCompileJvmVersion(project)
        setJunit5(project)
    }

    private fun applyRepositories(project: Project) {
        project.repositories.apply {
            mavenLocal()
            mavenCentral()
        }
    }

    private fun setJunit5(project: Project) {
        val testTask = project.tasks.getByName("test") as Test
        testTask.useJUnitPlatform()
    }

    private fun addDependencies(project: Project) {
        project.dependencies.add("implementation", "org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        project.dependencies.add("implementation", "org.jetbrains.kotlin:kotlin-reflect")

        val springBootTest = project.dependencies.add("testImplementation", "org.springframework.boot:spring-boot-starter-test:2.3.1.RELEASE") as ExternalModuleDependency
        // To make GradleTestJunit5 work, either comment out this line or revert back to 6.3 with command `./gradlew wrapper --gradle-version 6.3`
        springBootTest.exclude(mapOf("group" to "junit", "module" to "junit"))
    }

    private fun setKotlinCompileJvmVersion(project: Project) {
        project.tasks.withType(KotlinCompile::class.java).forEach {
            it.kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

}
