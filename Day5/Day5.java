package Day5;

import Util.ReadFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {

    public static void main(String[] args) {
        part1();
    }

    static void part1() {
        List<String> lines = ReadFile.readAllLinesAndRemoveEmpty("Day5/input.txt");

        String[] strSeeds = lines.get(0)
                .substring(lines.get(0)
                        .indexOf(":") + 2)
                .split(" ");
        List<Long> seeds = Arrays.stream(strSeeds)
                .map(Long::parseLong)
                .toList();

        List<Long> destinations = new ArrayList<>();
        List<Long> sources = new ArrayList<>();
        List<Long> range = new ArrayList<>();
        for (int i = 2; i < lines.size(); i++) {
            if (lines.get(i)
                    .contains("-") || i == lines.size() - 1) {
                seeds = seeds.stream()
                        .map(seed -> {
                            for (int j = 0; j < destinations.size(); j++) {
                                if (sources.get(j) <= seed && seed < sources.get(j) + range.get(j)) {
                                    return (seed - sources.get(j)) + destinations.get(j);
                                }
                            }
                            return seed;
                        })
                        .toList();
                destinations.clear();
                sources.clear();
                range.clear();
                continue;
            }
            String[] map = lines.get(i)
                    .split(" ");
            destinations.add(Long.parseLong(map[0]));
            sources.add(Long.parseLong(map[1]));
            range.add(Long.parseLong(map[2]));
        }
        System.out.println("Part 1 answer: " + seeds.stream()
                .min(Long::compareTo)
                .get());
    }

}
