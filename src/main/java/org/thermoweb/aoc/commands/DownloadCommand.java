package org.thermoweb.aoc.commands;

import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@CommandLine.Command(name = "download")
public class DownloadCommand implements Runnable {

    @CommandLine.Option(names = "--day")
    private int day;

    @CommandLine.Option(names = "--year", defaultValue = "2023")
    private int year;

    @CommandLine.Option(names = "--token")
    private String session;

    @Override
    public void run() {
        String sessionCookie = Optional.ofNullable(session).orElseGet(() -> getCookieSessionFromFile().orElse(""));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://adventofcode.com/" + year + "/day/" + day + "/input"))
                .header("Cookie", "session=" + sessionCookie)
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String input = response.body();
            Files.createDirectories(Paths.get("inputs"));
            Files.write(Path.of("inputs/input_" + (day > 9 ? day : "0" + day) + ".txt"), input.getBytes(), StandardOpenOption.CREATE);
            Files.createDirectories(Paths.get("examples"));
            Files.createFile(Path.of("examples/example_" + (day > 9 ? day : "0" + day) + ".txt"));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<String> getCookieSessionFromFile() {
        Path sessionCookieFile = Path.of(System.getProperty("user.home") + "/.adventofcode.session");
        if (sessionCookieFile.toFile().exists()) {
            try {
                return Optional.of(Files.readString(sessionCookieFile, Charset.defaultCharset()).replaceAll("[\n\r]+", ""));
            } catch (IOException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
