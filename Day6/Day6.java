package Day6;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Util.Utils;

public class Day6 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    static void part1() {
        List<String> line = Utils.readAllLines("Day6/input.txt");

        Pattern pattern = Pattern.compile("[\\d]{1,4}");
        Matcher matcherTimes = pattern.matcher(line.get(0));
        Matcher matcherDistances = pattern.matcher(line.get(1));

        List<Integer> numberOfWays = new ArrayList<>();
        while (matcherTimes.find() && matcherDistances.find()) {
            int start = 0;
            int maxTime = Integer.parseInt(matcherTimes.group(0));
            int recordDistance = Integer.parseInt(matcherDistances.group(0));
            for (int i = 1; i < maxTime / 2; i++) {
                int distance = i * (maxTime - i);
                if (distance > recordDistance) {
                    start = i;
                    break;
                }
            }
            int end = 0;
            for (int i = maxTime; i > 0; i--) {
                int distance = i * (maxTime - i);
                if (distance > recordDistance) {
                    end = i;
                    break;
                }
            }
            numberOfWays.add(end - start + 1);
        }
        System.out.println("Part 1 asnwer: " + numberOfWays.stream().reduce(1, (e1, e2) -> e1 * e2).intValue());
    }

    private static void part2() {
        List<String> line = Utils.readAllLines("Day6/input.txt");

        Pattern pattern = Pattern.compile("[\\d]+");
        Matcher matcherTimes = pattern.matcher(line.get(0).replaceAll("[\\s]", ""));
        Matcher matcherDistances = pattern.matcher(line.get(1).replaceAll("[\\s]", ""));

        while (matcherTimes.find() && matcherDistances.find()) {
            int start = 0;
            Long maxTime = Long.parseLong(matcherTimes.group(0));
            Long recordDistance = Long.parseLong(matcherDistances.group(0));
            for (int i = 1; i < maxTime / 2; i++) {
                long distance = i * (maxTime - i);
                if (distance > recordDistance) {
                    start = i;
                    break;
                }
            }
            long end = 0;
            for (long i = maxTime; i > 0; i--) {
                long distance = i * (maxTime - i);
                if (distance > recordDistance) {
                    end = i;
                    break;
                }
            }
            System.out.println("Part 2 answer: " + (end - start + 1));
        }

    }
}
