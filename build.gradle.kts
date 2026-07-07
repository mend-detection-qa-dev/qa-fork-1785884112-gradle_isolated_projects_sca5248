plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    // Public deps with a real transitive tree, so a green scan is unambiguous.
    // guava pulls failureaccess, listenablefuture, jsr305, checker-qual, error_prone_annotations.
    api("com.google.guava:guava:33.4.6-jre")
    implementation("org.apache.commons:commons-lang3:3.20.0")
    testImplementation("junit:junit:4.13.2")
}
