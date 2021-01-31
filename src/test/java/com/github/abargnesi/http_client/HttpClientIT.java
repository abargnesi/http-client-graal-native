package com.github.abargnesi.http_client;

import static java.lang.System.getProperty;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Test;

public class HttpClientIT {

  private static final String httpClientCommand = Paths
      .get(System.getProperty("user.dir"), "target", "http-client")
      .toString();

  @Test
  public void nativeCallMissingArguments() throws IOException, InterruptedException {
    final Process process = new ProcessBuilder()
        .command(httpClientCommand)
        .start();

    final List<String> standardOut = new BufferedReader(
        new InputStreamReader(process.getInputStream())).lines().collect(toList());
    final List<String> standardErr = new BufferedReader(
        new InputStreamReader(process.getErrorStream())).lines().collect(toList());

    assertEquals(0, standardOut.size());
    assertEquals(2, standardErr.size());
    assertThat(1, equalTo(process.waitFor()));
  }

  @Test
  public void nativeCallDefaults() throws IOException, InterruptedException {
    final Process process = new ProcessBuilder()
        .command(httpClientCommand, "https://www.google.com")
        .start();

    final List<String> standardOut = new BufferedReader(
        new InputStreamReader(process.getInputStream())).lines().collect(toList());

    assertEquals(10, standardOut.size());
    assertThat(standardOut, everyItem(containsString("200")));
    assertThat(0, equalTo(process.waitFor()));
  }

  @Test
  public void nativeCallCountOne() throws IOException, InterruptedException {
    final Process process = new ProcessBuilder()
        .command(httpClientCommand, "https://www.google.com", "1")
        .start();

    final List<String> standardOut = new BufferedReader(
        new InputStreamReader(process.getInputStream())).lines().collect(toList());

    assertEquals(1, standardOut.size());
    assertThat(standardOut, everyItem(containsString("200")));

    assertThat(0, equalTo(process.waitFor()));
  }
}
