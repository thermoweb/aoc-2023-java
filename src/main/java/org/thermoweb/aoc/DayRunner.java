package org.thermoweb.aoc;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

public class DayRunner {

    public static void runDay(Day dayToRun, int day) throws URISyntaxException, IOException {
        String input = getInput(day);
        runPartOne(dayToRun, input);
        runPartTwo(dayToRun, input);
    }

    public static String getInput(int day) throws IOException, URISyntaxException {
        return getFileContent("inputs/input_" + (day > 9 ? day : "0" + day) + ".txt");
    }

    public static String getExample(int day) throws IOException, URISyntaxException {
        return getFileContent("examples/example_" + (day > 9 ? day : "0" + day) + ".txt");
    }

    public static String getFileContent(String filename) throws IOException, URISyntaxException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(Objects.requireNonNull(classloader.getResource(filename)).toURI());
        return Files.readString(path);
    }

    private static void runPartOne(Day dayToRun, String input) {
        try {
            long start = System.nanoTime();
            Optional<BigInteger> result = dayToRun.partOne(input);
            long end = System.nanoTime();
            System.out.println("part 1: " + result.map(BigInteger::toString).orElse("<None>") + " (" + (end - start) / 1000000 + " ms)");
        } catch (Exception e) {
            System.out.println("exception occured in part 1!");
        }
    }

    private static void runPartTwo(Day dayToRun, String input) {
        try {
            long start = System.nanoTime();
            Optional<BigInteger> result = dayToRun.partTwo(input);
            long end = System.nanoTime();
            System.out.println("part 2: " + result.map(BigInteger::toString).orElse("<None>") + " (" + (end - start) / 1000000 + " ms)");
        } catch (Exception e) {
            System.out.println("exception occured in part 1!");
        }
    }
}
