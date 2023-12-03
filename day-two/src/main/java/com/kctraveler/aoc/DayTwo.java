package com.kctraveler.aoc;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class DayTwo {

    final static int RED = 12;
    final static int GREEN = 13;
    final static int BLUE = 14;

    public static void main(String[] args) {
        String file = "daytwo-input.txt";
        partOne(file);
        partTwo(file);
    }

    public static Stream<String> getInput(String inputFile){
        ClassLoader classLoader = DayTwo.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(inputFile);
        assert inputStream != null;
        return new BufferedReader(new InputStreamReader(inputStream)).lines();
    }

    private static void partOne(String input){
        Stream<String> in = getInput(input);
        int result = in.map(game -> game.split("[:;]"))
                .filter(DayTwo::checkGame)
                .map(DayTwo::getGameNumber)
                .mapToInt(i -> i)
                .sum();
        System.out.println("Day Two Part One: " + result);
    }

    private static Integer getGameNumber(String[] groups){
        return Integer.parseInt(groups[0].replaceAll("[ a-zA-Z]", ""));
    }

    private static boolean checkGame(String[] game){
        return Arrays.stream(game).skip(1).allMatch(g -> {
            String[] step = g.split(",");
            for (String color : step) {
                String[] d = color.split(" ");
                int num = Integer.parseInt(d[1]);
                if (d[2].equals("red") && num > RED) {
                    return false;
                } else if (d[2].equals("green") && num > GREEN) {
                    return false;
                } else if (d[2].equals("blue") && num > BLUE) {
                    return false;
                }
            }
            return true;
        });
    }

    private static int calcPower(String[] game){
        AtomicInteger maxRed = new AtomicInteger();
        AtomicInteger maxGreen = new AtomicInteger();
        AtomicInteger maxBlue = new AtomicInteger();
        Arrays.stream(game)
                .skip(1)
                .map(g -> g.split(","))
                .flatMap(Arrays::stream)
                .map(color -> color.trim().split(" "))
                .forEach(d -> {
                    int num = Integer.parseInt(d[0]);
                    switch (d[1]) {
                        case "red" -> maxRed.getAndAccumulate(num, Math::max);
                        case "green" -> maxGreen.getAndAccumulate(num, Math::max);
                        case "blue" -> maxBlue.getAndAccumulate(num, Math::max);
                    }
                });
        return maxBlue.get() * maxGreen.get() * maxRed.get();
    }

    private static void partTwo(String inputFile){
        Stream<String> in = getInput(inputFile);
        int res = in.map(g -> g.split("[:;]"))
                .map(DayTwo::calcPower)
                .mapToInt(i -> i)
                .sum();
        System.out.println("Day 2 Part 2: " + res);
    }
}
