package Day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

class Day1 {
    static int s = 0;
    static HashMap<String, Integer> m = new HashMap<>();
    static {
        m.put("one", 1);
        m.put("two", 2);
        m.put("three", 3);
        m.put("four", 4);
        m.put("five", 5);
        m.put("six", 6);
        m.put("seven", 7);
        m.put("eight", 8);
        m.put("nine", 9);
        m.put("1", 1);
        m.put("2", 2);
        m.put("3", 3);
        m.put("4", 4);
        m.put("5", 5);
        m.put("6", 6);
        m.put("7", 7);
        m.put("8", 8);
        m.put("9", 9);
    }

    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }

    static void part1() throws IOException {
        var lines = Files.readAllLines(Path.of("Day1/input.txt"));
        lines.forEach(line -> {
            int first = line.chars().filter(c -> Character.isDigit(c)).findFirst().getAsInt() - '0';
            int second = new StringBuilder(line).reverse().toString().chars().filter(c -> Character.isDigit(c))
                    .findFirst().getAsInt() - '0';
            s += (first*10 + second);
        });
        System.out.println("Part 1 answer: " + s);
    }

    static void part2() throws IOException {
        var lines = Files.readAllLines(Path.of("Day1/input.txt"));

        for (String line : lines) {
            int[] values = firstIndex(line);
            s += (values[0]*10 + values[1]);
        }
        System.out.println("Part 2 answer: " + s);
    }

    static int[] firstIndex(String string) {
        int[] ret = new int[2];
        int first = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (var items : m.entrySet()) {
            int temp = string.indexOf(items.getKey());
            if (temp != -1 && temp < first) {
                first = temp;
                ret[0] = items.getValue();
            }
            temp = string.lastIndexOf(items.getKey());
            if (temp != -1 && temp > max) {
                max = temp;
                ret[1] = items.getValue();
            }
        }
        return ret;
    }
}
