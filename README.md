# http-client-graal-native

## Purpose

Provide a simple example project on how to use GraalVM JDK 11, native-image, and Maven together.

I also wanted to experiment with the Java HttpClient API.

## Components

Maven
- Configures Java 11, native-image, and runs integration tests.

Graal
- Uses native-image to produce a compiled binary for the application.

## Build and Test

Prerequisites:
1. Install GraalVM.
1. Install native-image with `gu install native-image`.

Run `mvn clean verify` to build `http-client` binary and run integration tests against it.

## Run

The `http-client` binary is in the target directory after compilation.

Command:

```sh
usage: http-client URL COUNT
```

Example:

```sh
$ http-client https://example.com 1
200
```

