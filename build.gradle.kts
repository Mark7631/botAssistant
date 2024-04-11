plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "my.individual.project"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_19
}

repositories {
	mavenCentral()
	google()
}

dependencyManagement {
	dependencies {
		dependency("org.projectlombok:lombok:1.18.28")
		dependency("org.apache.commons:commons-lang3:3.13.0")
		dependency("org.springframework.boot:spring-boot-starter-actuator:3.1.2")
		dependency("org.telegram:telegrambots-spring-boot-starter:6.7.0")
		dependency("org.springframework.boot:spring-boot-starter:3.1.2")
		dependency("org.commonmark:commonmark:0.21.0")
		dependency("com.fasterxml.jackson.core:jackson-databind:2.15.0")
		dependency("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.0")
		dependency("org.springframework.boot:spring-boot-starter-test:2.7.12")
		dependency("org.junit.jupiter:junit-jupiter-api:5.0.3")
		dependency("org.junit.jupiter:junit-jupiter-engine:5.0.3")
		dependency("org.junit.jupiter:junit-jupiter-params:5.0.3") }
}

dependencies {
	annotationProcessor("org.projectlombok:lombok")
	compileOnly("org.projectlombok:lombok")
	implementation("org.apache.commons:commons-lang3")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.telegram:telegrambots-spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.commonmark:commonmark")
	implementation("com.fasterxml.jackson.core:jackson-databind")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml")
	testAnnotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude("org.junit.vintage", "junit-vintage-engine")
	}
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
	testImplementation("org.junit.jupiter:junit-jupiter-params")
}


tasks.withType<Test> {
	useJUnitPlatform()
}
