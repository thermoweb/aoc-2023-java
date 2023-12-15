package org.thermoweb.aoc;

import org.thermoweb.aoc.commands.ScaffoldCommand;
import org.thermoweb.aoc.commands.SolveDayCommand;

import picocli.CommandLine;

@CommandLine.Command(name = "aoc", subcommands = {SolveDayCommand.class, ScaffoldCommand.class})
public class AOC {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new AOC()).execute(args);
        System.exit(exitCode);
    }
}
