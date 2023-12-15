package org.example;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayOne {
    public static int partOne(final String test) {
        return Arrays.stream(test.split("\n"))
                .map(line -> line.replaceAll("[^0-9]", ""))
                .map(str -> "" + str.charAt(0) + str.charAt(str.length() - 1))
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        Path path = Paths.get(Objects.requireNonNull(classloader.getResource("input_01.txt")).toURI());
        String string = Files.readString(path);
//        System.out.println(partOne(string));
        System.out.println(partTwo(string));
    }

    public static int partTwo(String test) {
        test = replaceNumbers(test);
        String testStr = String.join("\n", Arrays.stream(test.split("\n"))
                .map(DayOne::replaceNumbers)
                .toList());
        return partOne(testStr);
    }

    private static String replaceNumbers(String test) {

        Map<String, String> numbers = Map.of(
                "one", "1",
                "two", "2",
                "three", "3",
                "four", "4",
                "five", "5",
                "six", "6",
                "seven", "7",
                "eight", "8",
                "nine", "9");
        String allNumbers = String.join("|", numbers.keySet());
        String regex = "(" + allNumbers + ")";
        Pattern pattern = Pattern.compile(regex);
        Matcher result = pattern.matcher(test);
        // two -> two2two
        //eightwone -> eightwone
        for (Map.Entry<String, String> entry : numbers.entrySet()) {
            test = test.replaceAll(entry.getKey(), entry.getKey() + entry.getValue() + entry.getKey());
        }
//        while (result.find()) {
//            String group = result.group(1);
//            test = test.replaceFirst(group, group + numbers.get(group) + group);
//        }
        return test;
    }
}
