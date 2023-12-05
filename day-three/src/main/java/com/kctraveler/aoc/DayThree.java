package com.kctraveler.aoc;


import java.util.ArrayList;
import java.util.List;

public class DayThree {


    public static void main(String[] args){
        List<String[]> in = DayTwo.getInput("day-three-input.txt")
                .map(row -> row.split("")).toList();
        int result = 0;
        for (int i = 0; i < in.size(); i++ ){
            for (int j = 0; j < in.get(0).length ; j++ ){
                if(!in.get(i)[j].matches("[.0-9]")){
                    System.out.print(in.get(i)[j]);
                    result += getPartNumber(in, i, j);
                }
            }


        }
        System.out.println("Day 3 Part 1: " + result);


    }

    private static int getPartNumber(List<String[]> input, int row, int col){
        ArrayList<String> results = new ArrayList<>();
        if (row - 1 > 0) {
            results.add(input.get(row - 1)[col]);
            if (col > 0){
                 results.add(input.get(row - 1)[col - 1]);
            }
            if (col + 1 < input.get(row).length){
                results.add(input.get(row -1)[col + 1]);
            }
        }

        if (row + 1 < input.size()){
            results.add(input.get(row + 1)[col]);
            if (col > 0){
                results.add(input.get(row + 1)[col - 1]);
            }
            if (col < input.get(row).length){
                results.add(input.get(row +1)[col + 1]);
            }
        }

        if (col > 0){
            results.add(input.get(row)[col -1]);
        }
        if ( col < input.get(row).length){
            results.add(input.get(row)[col + 1]);
        }
        int total = results.stream().filter(n -> n.matches("[0-9]")).mapToInt(Integer::parseInt).sum();
        System.out.println(total);
        return total;
    }
}
