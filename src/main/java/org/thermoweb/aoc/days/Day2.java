package org.thermoweb.aoc.days;

import java.lang.Override;
import java.lang.String;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DaySolver;

@DaySolver(2)
public class Day2 implements Day {
    //Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green

    //"si un set est KO, toute la game est KO"

    @Override
    public Optional<BigInteger> partOne(String input) {
        Map<String, Integer> condition = Map.of(
                "blue", 14,
                "red", 12,
                "green", 13);
        int sum = 0;
        var result = Arrays.stream(input.split("\n"))
                .map((line) -> {
                    String[] splits = line.split(":");
                    String gameNumber = splits[0].replace("Game ", "");
                    String[] sets = splits[1].split(";");
                    boolean result1 = Arrays.stream(sets).anyMatch((set) -> gameIsImpossible(set, condition, gameNumber));
                    if (result1) {
                        return 0;
                    }
                    return Integer.parseInt(gameNumber);
                })
                .reduce(0, Integer::sum);
        System.out.println(result);
        return Optional.of(BigInteger.valueOf(result));
    }

    private static boolean gameIsImpossible(String set, Map<String, Integer> condition, String gameNumber) {
        String[] colors = set.trim().split(", ");
        for (String color : colors) {
            String[] entry = color.split(" ");
            if (condition.get(entry[1]) < Integer.parseInt(entry[0])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<BigInteger> partTwo(String input) {
        return Optional.empty();
    }
}
