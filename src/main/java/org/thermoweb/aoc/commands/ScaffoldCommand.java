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
                        .returns(ParameterizedTypeName.get(ClassName.get(Optional.class), ClassName.get(BigInteger.class)))
                        .addStatement("return Optional.empty()")
                        .build())
                .addMethod(MethodSpec
                        .methodBuilder("partTwo")
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .returns(ParameterizedTypeName.get(ClassName.get(Optional.class), ClassName.get(BigInteger.class)))
                        .addStatement("return Optional.empty()")
                        .build())
                .build();
        System.out.println(dayClass);
        JavaFile javaFile = JavaFile
                .builder("com.baeldung.javapoet.dayClass", dayClass)
                .indent("    ")
                .addStaticImport(Date.class, "UTC")
                .addStaticImport(ClassName.get("java.time", "ZonedDateTime"), "*")
                .build();
        try {
            Path path = Path.of(AOC.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            System.out.println(path);
            File currentDirFile = new File(".");
            String helper = currentDirFile.getAbsolutePath();
            String currentDir = helper.substring(0, helper.length() - currentDirFile.getCanonicalPath().length());//this line may need a try-catch block
            System.out.println(currentDir);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
