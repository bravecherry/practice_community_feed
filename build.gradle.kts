plugins {
    id("java")
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "org.fastcampus"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    //mysql
    implementation("org.mariadb.jdbc:mariadb-java-client:3.5.3")

    //lombok
    implementation("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    //test
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.test {
    useJUnitPlatform()
}