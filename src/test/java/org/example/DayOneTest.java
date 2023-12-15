package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayOneTest {

    @Test
    void test_part_one() {
        String test = "1abc2\npqr3stu8vwx\na1b2c3d4e5f\ntreb7uchet";
        assertEquals(142, DayOne.partOne(test));
    }

    @Test
    void test_part_two() {
        String test = "two1nine\neightwothree\nabcone2threexyz\nxtwone3four\n4nineeightseven2\nzoneight234\n7pqrstsixteen";
        assertEquals(281, DayOne.partTwo(test));
    }
}