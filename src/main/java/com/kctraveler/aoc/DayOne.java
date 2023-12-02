package com.kctraveler.aoc;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * https://adventofcode.com/2023/day/1
 */
public class DayOne
{

    public static void main( String[] args )
    {
        String inputFile = "day1-input.txt";
        partOne(inputFile);
        partTwo(inputFile);

    }

    private static Stream<String> getInput(String inputFile){
        ClassLoader classLoader = DayOne.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(inputFile);
        assert inputStream != null;
        return new BufferedReader(new InputStreamReader(inputStream)).lines();
    }

    private static void partOne(String inputFile){
            Stream<String> lines = getInput(inputFile);
            int result = lines.map(l -> {
                String ints = l.replaceAll("[a-zA-Z]", "");
                return String.valueOf(ints.charAt(0)) + ints.charAt(ints.length() - 1);
            }).mapToInt(Integer::parseInt).sum();
            System.out.println("The part one result is: " + result);
    }

    private static void partTwo(String inputFile){
        Stream<String> lines = getInput(inputFile);
        int res = lines.map(s -> {
                      return s.replace("one", "o1e")
                            .replace("two", "t2o")
                            .replace("three", "t3e")
                            .replace("four", "f4r")
                            .replace("five", "f5e")
                            .replace("six", "s6x")
                            .replace("seven", "s7n")
                            .replace("eight", "e8t")
                            .replace("nine", "n9e")
                            .replaceAll("[a-zA-Z]", "");
                })
                .map(s -> String.valueOf(s.charAt(0)) + s.charAt(s.length() - 1))
                .mapToInt(Integer::parseInt)
                .sum();
        System.out.println("The part two result is: " + res);
    }

}
