package org.thermoweb.aoc.commands;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import javax.lang.model.element.Modifier;

import org.thermoweb.aoc.AOC;
import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DaySolver;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import picocli.CommandLine;

@CommandLine.Command(name = "scaffold")
public class ScaffoldCommand implements Runnable {
    @CommandLine.Option(names = "--day")
    private int day;


    @Override
    public void run() {
        String dayNumber = String.valueOf(day);
        TypeSpec dayClass = TypeSpec
                .classBuilder("Day" + dayNumber)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(Day.class)
                .addAnnotation(AnnotationSpec.builder(DaySolver.class).addMember("value", dayNumber).build())
                .addMethod(MethodSpec
                        .methodBuilder("partOne")
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .addParameter(ParameterSpec.builder(String.class, "input").build())
                        .returns(ParameterizedTypeName.get(ClassName.get(Optional.class), ClassName.get(BigInteger.class)))
                        .addStatement("return Optional.empty()")
                        .build())
                .addMethod(MethodSpec
                        .methodBuilder("partTwo")
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .addParameter(ParameterSpec.builder(String.class, "input").build())
                        .returns(ParameterizedTypeName.get(ClassName.get(Optional.class), ClassName.get(BigInteger.class)))
                        .addStatement("return Optional.empty()")
                        .build())
                .build();
        System.out.println(dayClass);
        Path output = Paths.get("").toAbsolutePath().resolve(Path.of("src/main/java")); ///java/org/thermoweb/aoc/days/Day" + dayNumber + ".java
        System.out.println("writing class to : " + output);
        JavaFile javaFile = JavaFile
                .builder("org.thermoweb.aoc.days", dayClass)
                .indent("    ")
                .build();
        try {
            javaFile.writeTo(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
