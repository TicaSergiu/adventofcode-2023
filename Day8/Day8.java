package Day8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.ReadFile;

public class Day8 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        List<String> lines = ReadFile.readAllLinesAndRemoveEmpty("Day8/input.txt");

        char[] instructions = lines.get(0).toCharArray();

        Map<String, String[]> map = new HashMap<>();
        lines.stream().skip(1).forEach(line -> {
            map.put(line.split("=")[0].trim(), line.split("=")[1].replaceAll("[(|)\s]", "").split(","));
        });

        String previousLocation = map.get("AAA")[instructions[0] == 'L' ? 0 : 1];
        int steps = 1;
        for (int i = 1; i < instructions.length; i++) {
            String currentLocation = map.get(previousLocation)[instructions[i] == 'L' ? 0 : 1];
            steps++;
            if (currentLocation.equals("ZZZ")) {
                break;
            }
            if (i == instructions.length - 1) {
                i = -1;
            }
            previousLocation = currentLocation;
        }
        System.out.println("Part 1 answer: " + steps);
    }

    private static void part2() {
        List<String> lines = ReadFile.readAllLinesAndRemoveEmpty("Day8/input.txt");

        char[] instructions = lines.get(0).toCharArray();

        Map<String, String[]> map = new HashMap<>();
        lines.stream().skip(1).forEach(line -> {
            map.put(line.split("=")[0].trim(), line.split("=")[1].replaceAll("[(|)\s]", "").split(","));
        });

        List<String> previousLocations = new ArrayList<>();
        for (String loc : map.keySet()) {
            if (loc.matches("\\w{2}A")) {
                previousLocations.add(loc);
            }
        }

        List<Integer> stepsList = new ArrayList<>();
        for (String loc : previousLocations) {
            int steps = 0;
            String previous = loc;
            for (int i = 0; i < instructions.length; i++) {
                String currentLocation = map.get(previous)[instructions[i] == 'L' ? 0 : 1];
                steps++;
                if (currentLocation.matches("\\w{2}Z")) {
                    break;
                }
                if (i == instructions.length - 1) {
                    i = -1;
                }
                previous = currentLocation;
            }
            stepsList.add(steps);
        }

        System.out.println("Part 2 answer: " + lcm(stepsList));
    }

    private static long lcm(List<Integer> nums) {
        long res = nums.get(0);
        for (Integer num : nums) {
            res = lcm(res, num);
        }
        return res;
    }

    private static long lcm(long a, int b) {
        return (a * b) / gcd(a, b);
    }

    private static long gcd(long a, long b) {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }
}