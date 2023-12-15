package org.thermoweb.aoc.commands;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.Callable;

import org.reflections.Reflections;
import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DayRunner;
import org.thermoweb.aoc.DaySolver;

import picocli.CommandLine;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

@CommandLine.Command(name = "solve")
public class SolveDayCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-d", "--day"}, description = "day to solve", required = true)
    private Integer day;

    @Override
    public Integer call() throws Exception {
        System.out.println("solving day " + day);
        Reflections reflections = new Reflections("org.thermoweb.aoc");
        Set<Class<?>> daySolvers = reflections.get(SubTypes.of(TypesAnnotated.with(DaySolver.class)).asClass());
        Class<?> daySolver = daySolvers.stream()
                .filter(ds -> ds.getAnnotation(DaySolver.class).value() == 1 && Arrays.stream(ds.getInterfaces()).anyMatch(i -> i == Day.class))
                .findFirst()
                .orElseThrow();
        Constructor<?> constructor = Arrays.stream(daySolver.getDeclaredConstructors())
                .filter(c -> c.getParameterCount() == 0)
                .findFirst()
                .orElseThrow();
        Day dayRunner = (Day) constructor.newInstance();
        DayRunner.runDay(dayRunner, day);
        return 0;
    }
}
