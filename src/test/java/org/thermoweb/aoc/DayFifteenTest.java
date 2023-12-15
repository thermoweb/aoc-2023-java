package org.thermoweb.aoc;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.thermoweb.aoc.days.DayFifteen;

import static org.junit.jupiter.api.Assertions.*;

class DayFifteenTest {

    Day day15 = new DayFifteen();

    @Test
    void applyHash_should_return_correct_hash() {
        assertEquals(52, DayFifteen.applyHash("HASH"));
    }

    @Test
    void partOne() {
        String input = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7";
        assertEquals(Optional.of(1320), day15.partOne(input));
    }

    @Test
    void partTwo() {
        String input = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7";
        assertEquals(145, day15.partTwo(input));
    }

    @Test
    void test() {
        System.out.println(DayFifteen.applyHash("rn"));
        System.out.println(DayFifteen.applyHash("cm"));
        System.out.println(DayFifteen.applyHash("qp"));
    }

    @Test
    void regex_should_work() {
        assertTrue(DayFifteen.stepRegex.matcher("rm=3").find());
        assertTrue(DayFifteen.stepRegex.matcher("cm-").find());
        assertTrue(DayFifteen.stepRegex.matcher("xyz=9").find());
        assertTrue(DayFifteen.stepRegex.matcher("xyz=1").find());

        assertFalse(DayFifteen.stepRegex.matcher("rm=0").find());
        assertFalse(DayFifteen.stepRegex.matcher("rm1").find());
        assertFalse(DayFifteen.stepRegex.matcher("=1").find());
    }
}