package org.thermoweb.aoc.days;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DaySolver;

@DaySolver(2)
public class Day2 implements Day {
    //Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green

    //"si un set est KO, toute la game est KO"
    private final static Map<String, Integer> condition = Map.of(
            "blue", 14,
            "red", 12,
            "green", 13);

    @Override
    public Optional<BigInteger> partOne(String input) {
        var result = Arrays.stream(input.split("\n"))
                .map((line) -> {
                    String[] splits = line.split(":");
                    String gameNumber = splits[0].replace("Game ", "");
                    String[] sets = splits[1].split(";");
                    return Arrays.stream(sets).anyMatch(Day2::gameIsImpossible) ? 0 : Integer.parseInt(gameNumber);
                })
                .reduce(0, Integer::sum);
        System.out.println(result);
        return Optional.of(BigInteger.valueOf(result));
    }

    private static boolean gameIsImpossible(String set) {
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
        var result = Arrays.stream(input.split("\n"))
                .map((line) -> {
                    String[] splits = line.split(":");
                    String[] sets = splits[1].split(";");
                    Map<String, Integer> map = new HashMap<>();
                    for (String set : sets) {
                        String[] colors = set.trim().split(", ");
                        for (String color : colors) {
                            String[] entry = color.split(" ");
                            Integer numOfBalls = Integer.parseInt(entry[0]);
                            Optional<Integer> oldValue = Optional.ofNullable(map.get(entry[1]));
                            oldValue.ifPresentOrElse((v) -> {
                                if (v < numOfBalls) {
                                    map.replace(entry[1], numOfBalls);
                                }
                            }, () -> map.put(entry[1], numOfBalls));
                            Integer newValue = map.computeIfPresent(entry[1], (k, v) -> {
                                if (v < numOfBalls) {
                                    return numOfBalls;
                                } else {
                                    return v;
                                }
                            });
                            map.put(entry[1], newValue);
                        }
                    }
                    return map.values().stream().reduce(1, (a, b) -> a * b);
                })
                .reduce(0, Integer::sum);
        System.out.println(result);
        return Optional.of(BigInteger.valueOf(result));
    }
}
