package org.thermoweb.aoc.days;

import java.lang.Override;
import java.lang.String;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DaySolver;

@DaySolver(1)
public class Day1 implements Day {
    @Override
    public Optional<BigInteger> partOne(String input) {
        return Optional.of(Arrays.stream(input.split("\n"))
                        .map(line -> line.replaceAll("[^0-9]", ""))
                        .map(str -> "" + str.charAt(0) + str.charAt(str.length() - 1))
                        .map(Integer::parseInt)
                        .reduce(0, Integer::sum))
                .map(BigInteger::valueOf);
    }

    @Override
    public Optional<BigInteger> partTwo(String input) {
        input = replaceNumbers(input);
        String testStr = String.join("\n", Arrays.stream(input.split("\n"))
                .map(Day1::replaceNumbers)
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
