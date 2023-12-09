package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    static final Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        // part1();
        part2();
    }

    static void part1() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("Day3/input.txt"));
        int sum = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (Character.isDigit(line.charAt(j))) {
                    int number = 0;
                    boolean validNumber = false;
                    if (Character.isDigit(line.charAt(j + 1)) && Character.isDigit(line.charAt(j + 2))) {
                        validNumber = findNeighbour(lines, i, j, 3);
                        number = Integer.parseInt(line.substring(j, j + 3));
                        j += 2;
                    } else if (Character.isDigit(line.charAt(j + 1))) {
                        validNumber = findNeighbour(lines, i, j, 2);
                        number = Integer.parseInt(line.substring(j, j + 2));
                        j++;
                    } else {
                        number = line.charAt(j) - '0';
                        validNumber = findNeighbour(lines, i, j, 1);
                    }
                    if (validNumber) {
                        sum += number;
                    }
                }
            }
        }
        System.out.println("Part 1 answer: " + sum);
    }

    private static boolean findNeighbour(List<String> lines, int lineNumber, int charLocation, int numSize) {
        for (int k = 1; k > -2; k--) {
            try {
                String currentLine = lines.get(lineNumber - k);
                for (int i = charLocation - 1; i <= charLocation + numSize; i++) {
                    try {
                        if (currentLine.charAt(i) != '.' && !Character.isDigit(currentLine.charAt(i))) {
                            return true;
                        }
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    static void part2() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("Day3/example.txt"));
        int result = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '*') {
                    List<Integer> nums = new ArrayList<>();
                    final Pattern pattern = Pattern.compile("[\\d]{1,3}");
                    Matcher matcher = null;
                    try {
                        String previous = lines.get(i - 1);
                        if (Character.isDigit(previous.charAt(j - 1))) {
                            if (Character.isDigit(previous.charAt(j))) {
                            }

                        }
                    } catch (Exception e) {
                    }
                    matcher = pattern.matcher(line.substring(j - 3, j + 4));
                    while (matcher.find()) {
                        nums.add(Integer.parseInt(matcher.group(0)));
                    }

                    if (nums.size() == 2) {
                        result += nums.get(0) * nums.get(1);
                    }
                }
            }
        }
        System.out.println("Part 2 answer: " + result);
    }
}