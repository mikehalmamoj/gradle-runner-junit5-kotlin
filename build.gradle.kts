import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.3.72"
  id("com.gradle.plugin-publish") version "0.12.0"
  id("java-gradle-plugin")
  id("maven-publish")
}

repositories {
  mavenLocal()
  mavenCentral()
  maven {
    url = uri("https://plugins.gradle.org/m2/")
  }
}

group = "mhalma"
version = "0.1.0"

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation(kotlin("reflect"))

  implementation("org.springframework.boot:spring-boot-gradle-plugin:2.3.1.RELEASE")
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
  implementation("io.spring.dependency-management:io.spring.dependency-management.gradle.plugin:1.0.9.RELEASE")

  testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
  testImplementation("org.assertj:assertj-core:3.16.1")
}

gradlePlugin {
  plugins {
    create("mhalmaMyPlugin") {
      id = "mhalma.my-plugin"
      implementationClass = "mhalma.MyPlugin"

      displayName = "My Plugin"
      description = "Test running gradle runner junit 5 tests"
    }
  }
}

tasks {
  test {
    useJUnitPlatform()
  }

  withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = JavaVersion.VERSION_11.toString()
    }
  }
}
