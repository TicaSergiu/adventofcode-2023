package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day4 {
    public static void main(String[] args) throws IOException {
        part1();
        part2();
    }

    static void part1() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("Day4/input.txt"));
        int points = 0;
        for (int i = 0; i < lines.size(); i++) {
            String game = lines.get(i)
                    .substring(lines.get(i)
                            .indexOf(":") + 1);
            String[] winningNumbers = game.replaceAll("  ", " ")
                    .split("\\|")[0].trim()
                    .split(" ");
            String[] myNumbers = game.replaceAll("  ", " ")
                    .split("\\|")[1].trim()
                    .split(" ");

            Set<String> winning = new HashSet<>(Arrays.asList(winningNumbers));
            Set<String> mine = new HashSet<>(Arrays.asList(myNumbers));
            List<Integer> as = new ArrayList<>();
            mine.forEach((num) -> {
                if (winning.contains(num)) {
                    as.add(0);
                }
            });

            if (as.size() > 0) {
                points += Math.pow(2, as.size() - 1);
            }
        }
        System.out.println("Part 1 answer: " + points);
    }

    private static void part2() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("Day4/input.txt"));
        int points = lines.size();
        int[] newCards = new int[points];
        Arrays.fill(newCards, 1);
        for (int i = 0; i < lines.size(); i++) {
            String game = lines.get(i)
                    .substring(lines.get(i)
                            .indexOf(":") + 1);
            String[] winningNumbers = game.replaceAll("  ", " ")
                    .split("\\|")[0].trim()
                    .split(" ");
            String[] myNumbers = game.replaceAll("  ", " ")
                    .split("\\|")[1].trim()
                    .split(" ");

            Set<String> winning = new HashSet<>(Arrays.asList(winningNumbers));
            Set<String> mine = new HashSet<>(Arrays.asList(myNumbers));
            List<Integer> numOfWins = new ArrayList<>();
            mine.forEach((num) -> {
                if (winning.contains(num)) {
                    numOfWins.add(0);
                }
            });

            for (int j = 1; j <= numOfWins.size(); j++) {
                newCards[i + j] += newCards[i];
            }
        }
        System.out.println("Part 2 answer: " + Arrays.stream(newCards).sum());
    }

}
