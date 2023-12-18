package org.thermoweb.aoc.days;

import java.lang.Exception;
import java.math.BigInteger;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DayRunner;

import static org.junit.jupiter.api.Assertions.*;

public class Day15Test {
    private final Day day = new Day15();

    @Test
    void test_part_one() throws Exception {
        assertEquals(Optional.of(BigInteger.valueOf(1320)), day.partOne(DayRunner.getExample(15)));
    }

    @Test
    void test_part_two() throws Exception {
        assertEquals(Optional.of(BigInteger.valueOf(145)), day.partTwo(DayRunner.getExample(15)));
    }

    @Test
    void applyHash_should_return_correct_hash() {
        assertEquals(52, Day15.applyHash("HASH"));
    }

    @Test
    void test() {
        System.out.println(Day15.applyHash("rn"));
        System.out.println(Day15.applyHash("cm"));
        System.out.println(Day15.applyHash("qp"));
    }

    @Test
    void regex_should_work() {
        assertTrue(Day15.stepRegex.matcher("rm=3").find());
        assertTrue(Day15.stepRegex.matcher("cm-").find());
        assertTrue(Day15.stepRegex.matcher("xyz=9").find());
        assertTrue(Day15.stepRegex.matcher("xyz=1").find());

        assertFalse(Day15.stepRegex.matcher("rm=0").find());
        assertFalse(Day15.stepRegex.matcher("rm1").find());
        assertFalse(Day15.stepRegex.matcher("=1").find());
    }
}
