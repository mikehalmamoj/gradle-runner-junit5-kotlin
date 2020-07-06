package mhalma

import org.assertj.core.api.Assertions.assertThat
import org.gradle.internal.impldep.org.codehaus.plexus.util.FileUtils
import org.gradle.testkit.runner.TaskOutcome
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File

class GradleTestsJunit5 {

    companion object {
        @TempDir
        @JvmStatic
        lateinit var projectDir: File
    }

    @AfterEach
    fun `Delete project`() {
        FileUtils.cleanDirectory(projectDir)
    }

    @Test
    fun `Junit 5 tests can be executed`() {
        makeProject(kotlinProjectDetails(projectDir))

        val result = buildProject(projectDir, "test")
        assertThat(result.task(":test")?.outcome).isEqualTo(TaskOutcome.SUCCESS)
    }

}
