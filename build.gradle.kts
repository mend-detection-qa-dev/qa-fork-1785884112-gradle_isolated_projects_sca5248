plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    // Three direct, non-test public deps so the scan detects >= 3 direct dependencies.
    // guava pulls a real transitive tree (failureaccess, listenablefuture, error_prone_annotations, ...).
    api("com.google.guava:guava:33.4.6-jre")
    implementation("org.apache.commons:commons-lang3:3.20.0")
    implementation("commons-io:commons-io:2.16.1")
    // Test-scope dep is excluded from the scan by default (gradle.ignoredConfigurations), so it does not
    // count toward direct dependencies -- kept only to exercise a realistic build.
    testImplementation("junit:junit:4.13.2")
}
