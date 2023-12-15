package org.thermoweb.aoc.days;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DaySolver;

@DaySolver(15)
public class DayFifteen implements Day {
    public static final Pattern stepRegex = Pattern.compile("^(\\w+)([=-])([1-9])?$");

    @Override
    public Optional<BigInteger> partOne(final String test) {
        return Optional.of(Arrays.stream(test.split(","))
                .map(DayFifteen::applyHash)
                .reduce(0, Integer::sum))
                .map(BigInteger::valueOf);
    }

    @Override
    public Optional<BigInteger> partTwo(String test) {
        Arrays.stream(test.split(","))
                .forEach(step -> {
                    Matcher matcher = stepRegex.matcher(step);
                    System.out.printf("%n[%s] -> %s%n", step, matcher.find());
                    String label = matcher.group(1);
                    int boxNumber = applyHash(label);
                    if (matcher.group(3) == null) {
                        System.out.printf("remove lens %s in box %d", label, boxNumber);
                        //remove lentille
                    } else {
                        //remplacement lentille
                        int focal = Integer.parseInt(matcher.group(3));
                        System.out.printf("insert/replace lens(%d) %s in box %d", focal, label, boxNumber);
                    }
                });
        //ArrayList<Pair<String, Integer>> listItems = new ArrayList<>(List.of(Pair.of("ab", 2),Pair.of("cd", 2),Pair.of("ef", 2)));
        //        if (listItems.contains("cd")) {
        //            int index = listItems.indexOf("cd");
        //            listItems.set(index, Pair.of("newvalue", 1));
        //        } else {
        //            listItems.add(newPair);
        //        }
        return Optional.of(BigInteger.ZERO);
    }

    public static int applyHash(String input) {
        int current = 0;
        for (char c : input.toCharArray()) {
            if (c != '\n') {
                current += c;
                current *= 17;
                current %= 256;
            }
//            System.out.println(current);
        }
        return current;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Path path = Paths.get(Objects.requireNonNull(classloader.getResource("inputs/input_15.txt")).toURI());
        String string = Files.readString(path);
        Day day = new DayFifteen();
        System.out.println(day.partOne(string));
        System.out.println(day.partTwo(string));
    }
}
