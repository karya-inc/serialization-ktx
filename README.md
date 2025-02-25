# Serialization-KTX

A Kotlin serialization utility library.

## Installation
Add the following dependency to your `build.gradle.kts` file:
```kotlin
dependencies {
    implementation("io.github.karya-inc:serialization-ktx:0.0.1")
}
```

If using Maven, add this to `pom.xml`:
```xml
<dependency>
  <groupId>io.github.karya-inc</groupId>
  <artifactId>serialization-ktx</artifactId>
  <version>0.0.1</version>
</dependency>
```

## Contribution Guidelines

### Prerequisites
- Install [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
- Install [JDK 17+](https://adoptopenjdk.net/) (Recommended: Latest LTS version)
- Ensure Kotlin plugin is installed in IntelliJ IDEA (comes pre-installed in recent versions)

### Clone the Repository
To get started, clone the repository using the following command:

```sh
git clone https://github.com/karya-inc/serialization-ktx.git
```

### Open in IntelliJ IDEA
1. Open **IntelliJ IDEA**.
2. Click on **File > Open**.
3. Select the cloned repository folder and open it.
4. IntelliJ will automatically detect and configure the project.

### Run Tests
To run the tests:

1. Open the **Run** tool window or press `Shift + F10`.
2. Locate the test files inside the `src/test/kotlin` directory.
3. Right-click on the test file or class and select **Run 'Tests in...'**.
4. Alternatively, you can run all tests using the following Gradle command:

```sh
./gradlew test
```

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

