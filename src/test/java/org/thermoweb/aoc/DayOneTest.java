package org.thermoweb.aoc;

import java.math.BigInteger;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.thermoweb.aoc.DayOne;

import static org.junit.jupiter.api.Assertions.*;

class DayOneTest {

    Day day = new DayOne();

    @Test
    void test_part_one() {
        String test = "1abc2\npqr3stu8vwx\na1b2c3d4e5f\ntreb7uchet";
        assertEquals(Optional.of(BigInteger.valueOf(142)), day.partOne(test));
    }

    @Test
    void test_part_two() {
        String test = "two1nine\neightwothree\nabcone2threexyz\nxtwone3four\n4nineeightseven2\nzoneight234\n7pqrstsixteen";
        assertEquals(Optional.of(BigInteger.valueOf(281)), day.partTwo(test));
    }
}