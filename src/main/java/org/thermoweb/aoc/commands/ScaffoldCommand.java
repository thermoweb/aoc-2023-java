package org.thermoweb.aoc.commands;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.lang.model.element.Modifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.thermoweb.aoc.Day;
import org.thermoweb.aoc.DayRunner;
import org.thermoweb.aoc.DaySolver;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
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
        TypeSpec testClass = TypeSpec
                .classBuilder("Day" + dayNumber + "Test")
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec.builder(TypeName.get(Day.class), "day")
                        .initializer("new Day" + dayNumber + "()")
                        .addModifiers(Modifier.FINAL)
                        .addModifiers(Modifier.PRIVATE)
                        .build())
                .addMethod(MethodSpec
                        .methodBuilder("test_part_one")
                        .addAnnotation(Test.class)
                        .addException(Exception.class)
                        .addStatement("assertEquals($T.empty(), day.partOne($T.getExample(" + dayNumber + ")))", Optional.class, TypeName.get(DayRunner.class))
                        .build())
                .addMethod(MethodSpec
                        .methodBuilder("test_part_two")
                        .addAnnotation(Test.class)
                        .addException(Exception.class)
                        .addStatement("assertEquals($T.empty(), day.partTwo($T.getExample(" + dayNumber + ")))", Optional.class, TypeName.get(DayRunner.class))
                        .build())
                .build();

        Path currentDir = Paths.get("");
        Path classOutput = currentDir.toAbsolutePath().resolve(Path.of("src/main/java"));
        JavaFile javaFile = JavaFile
                .builder("org.thermoweb.aoc.days", dayClass)
                .indent("    ")
                .build();
        JavaFile testFile = JavaFile
                .builder("org.thermoweb.aoc.days", testClass)
                .indent("    ")
                .addStaticImport(Assertions.class, "assertEquals")
                .build();
        Path testOutput = currentDir.toAbsolutePath().resolve(Path.of("src/test/java"));
        try {
            javaFile.writeTo(classOutput);
            testFile.writeTo(testOutput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
