package Day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Util.Utils;

public class Day9 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        List<String> lines = Utils.readAllLines("Day9/input.txt");

        int result = 0;
        for (String line : lines) {
            Integer[] parsedNums = Utils.parseListOfNums(line.split(" "));
            List<Integer[]> numbersList = new ArrayList<>();
            numbersList.add(parsedNums);
            while (true) {
                Integer[] newRow = new Integer[numbersList.get(numbersList.size() - 1).length - 1];
                Integer[] zerosArray = new Integer[newRow.length];
                Arrays.fill(zerosArray, 0);
                for (int i = 0; i < newRow.length; i++) {
                    newRow[i] = numbersList.get(numbersList.size() - 1)[i + 1]
                            - numbersList.get(numbersList.size() - 1)[i];
                }
                numbersList.add(newRow);
                if (Arrays.equals(newRow, zerosArray)) {
                    break;
                }
            }
            result += numbersList.stream().reduce(0, (acc, nums) -> acc + nums[nums.length - 1], Integer::sum);
        }
        System.out.println("Part 1 answer: " + result);
    }

    private static void part2() {
        List<String> lines = Utils.readAllLines("Day9/inpu.txt");

        int result = 0;
        for (String line : lines) {
            Integer[] parsedNums = Utils.parseListOfNums(line.split(" "));
            List<Integer[]> numbersList = new ArrayList<>();
            numbersList.add(parsedNums);
            while (true) {
                Integer[] newRow = new Integer[numbersList.get(numbersList.size() - 1).length - 1];
                Integer[] zerosArray = new Integer[newRow.length];
                Arrays.fill(zerosArray, 0);
                for (int i = 0; i < newRow.length; i++) {
                    newRow[i] = numbersList.get(numbersList.size() - 1)[i + 1]
                            - numbersList.get(numbersList.size() - 1)[i];
                }
                numbersList.add(newRow);
                if (Arrays.equals(newRow, zerosArray)) {
                    break;
                }
            }
            int x = 0;
            for (int i = numbersList.size() - 1; i > 0; i--) {
                x = numbersList.get(i - 1)[0] - x;
            }
            result += x;
        }
        System.out.println("Part 2 answer: " + result);
    }
}
