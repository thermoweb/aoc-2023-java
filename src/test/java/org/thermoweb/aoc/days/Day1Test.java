package org.thermoweb.aoc.days;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.Exception;
import java.math.BigInteger;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DayRunner;

public class Day1Test {
    private final Day day = new Day1();

    @Test
    void test_part_one() throws Exception {
        assertEquals(Optional.of(BigInteger.valueOf(142)), day.partOne(DayRunner.getExample(1)));
    }

    @Test
    void test_part_two() throws Exception {
        String input = "two1nine\neightwothree\nabcone2threexyz\nxtwone3four\n4nineeightseven2\nzoneight234\n7pqrstsixteen";
        assertEquals(Optional.of(BigInteger.valueOf(281)), day.partTwo(input));
    }
}
