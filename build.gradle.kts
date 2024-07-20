plugins {
	id("org.springframework.boot") version "3.2.7"
	id("io.spring.dependency-management") version "1.1.5"
	kotlin("plugin.jpa") version "1.9.24"
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	kotlin("kapt") version "1.9.21"
}

group = "hope"
version = "0.0.1-SNAPSHOT"

val queryDslVersion: String by extra

repositories {
	mavenCentral()
}

dependencies {
	// spring
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// h2
	runtimeOnly("com.h2database:h2")

	// test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.mockito:mockito-core:4.8.0")
	testImplementation("org.mockito.kotlin:mockito-kotlin:3.2.0")
	testImplementation("org.mockito:mockito-junit-jupiter:4.8.0")

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")

	kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
	kapt("jakarta.annotation:jakarta.annotation-api")
	kapt("jakarta.persistence:jakarta.persistence-api")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

noArg {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

val generated = file("src/main/generated")

tasks.withType<JavaCompile> {
	options.generatedSourceOutputDirectory.set(generated)
}

sourceSets {
	main {
		kotlin.srcDirs += generated
	}
}

tasks.named("clean") {
	doLast {
		generated.deleteRecursively()
	}
}


kapt {
	generateStubs = true
}
