package org.thermoweb.aoc;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.thermoweb.aoc.days.DayOne;

import static org.junit.jupiter.api.Assertions.*;

class DayOneTest {

    Day day = new DayOne();

    @Test
    void test_part_one() throws Exception {
        assertEquals(Optional.of(BigInteger.valueOf(142)), day.partOne(DayRunner.getExample(1)));
    }

    @Test
    void test_part_two() {
        String input = "two1nine\neightwothree\nabcone2threexyz\nxtwone3four\n4nineeightseven2\nzoneight234\n7pqrstsixteen";
        assertEquals(Optional.of(BigInteger.valueOf(281)), day.partTwo(input));
    }
}