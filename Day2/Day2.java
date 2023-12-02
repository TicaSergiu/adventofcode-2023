package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day2 {
    static final int numRedCubes = 12;
    static final int numGreenCubes = 13;
    static final int numBlueCubes = 14;

    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }

    static void part1() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("Day2/input.txt"));
        int sumOfId = 0;
        for (int i = 0; i < lines.size(); i++) {
            String game = lines.get(i);
            String info = game.substring(game.indexOf(':') + 1);
            String[] sets = info.split(";");
            boolean validGame = true;
            for (String s : sets) {
                String[] draws = s.split(",");
                for (String d : draws) {
                    int val = Integer.parseInt(d.substring(0, 3).trim());
                    switch (d.substring(3).trim()) {
                        case "green":
                            if (val > numGreenCubes) {
                                validGame = false;
                            }
                            break;
                        case "red":
                            if (val > numRedCubes) {
                                validGame = false;
                            }
                            break;
                        case "blue":
                            if (val > numBlueCubes) {
                                validGame = false;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            if (validGame) {
                sumOfId += i + 1;
            }
        }
        System.out.println("Part 2 answer: " + sumOfId);
    }

    static void part2() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("Day2/input.txt"));
        int sumOfPower = 0;
        for (int i = 0; i < lines.size(); i++) {
            String game = lines.get(i);
            String info = game.substring(game.indexOf(':') + 1);
            String[] sets = info.split(";");
            int maxRed = 0, maxBlue = 0, maxGreen = 0;
            for (String s : sets) {
                String[] draws = s.split(",");
                for (String d : draws) {
                    int val = Integer.parseInt(d.substring(0, 3).trim());
                    switch (d.substring(3).trim()) {
                        case "green":
                            if (val > maxGreen) {
                                maxGreen = val;
                            }
                            break;
                        case "red":
                            if (val > maxRed) {
                                maxRed = val;
                            }
                            break;
                        case "blue":
                            if (val > maxBlue) {
                                maxBlue = val;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            sumOfPower += (maxRed * maxBlue * maxGreen);
        }
        System.out.println("Part 2 answer: " + sumOfPower);
    }

}
