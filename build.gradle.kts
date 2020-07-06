import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.3.72"
  java
  id("com.gradle.plugin-publish") version "0.12.0"
  id("java-gradle-plugin")
}

repositories {
  mavenLocal()
  mavenCentral()
}

group = "mhalma"
version = "0.1.0"

dependencies {
  implementation(kotlin("stdlib-jdk8"))
  implementation(kotlin("reflect"))

  testImplementation("org.junit.jupiter:junit-jupiter:5.6.2")
  testImplementation("org.assertj:assertj-core:3.16.1")
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
