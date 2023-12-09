package Day5;

import Util.Utils;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Part2 {
    static class Range {
        long start, end;

        public String toString() {
            return "Range start: " + start + " range end: " + end;
        }

        Range(long start, long end) {
            this.start = start;
            this.end = end;
        }

        static public int compare(Range arg0, Range arg1) {
            if (arg0.start < arg1.start) {
                return -1;
            }
            if (arg0.start == arg1.start) {
                return 0;
            } else {
                return 1;
            }
        }

    }

    public static void main(String[] args) {
        List<String> lines = Utils.readAllLinesAndRemoveEmpty("Day5/input.txt");

        String[] strSeeds = lines.get(0)
                .substring(lines.get(0)
                        .indexOf(":") + 2)
                .split(" ");

        List<Range> ranges = new ArrayList<>();
        for (int i = 0; i < strSeeds.length; i += 2) {
            long start = Long.parseLong(strSeeds[i]);
            long end = start + Long.parseLong(strSeeds[i + 1]);
            ranges.add(new Range(start, end));
        }

        List<Long> destinations = new ArrayList<>();
        List<Long> sources = new ArrayList<>();
        List<Long> intervalRange = new ArrayList<>();
        for (int i = 2; i < lines.size(); i++) {
            if (lines.get(i)
                    .contains("-") || i == lines.size() - 1) {
                List<Range> newRanges = new ArrayList<>();
                LOOP: for (Range range : ranges) {
                    for (int j = 0; j < destinations.size(); j++) {
                        // if (range.start < sources.get(j) && range.end <= sources.get(j)) {
                        // newRanges.add(new Range(range.start, sources.get(j) - range.start - 1));
                        // continue;
                        // }
                        if (range.start >= sources.get(j) && range.end <= sources.get(j) + intervalRange.get(j)) {
                            long newRangeStart = (range.start - sources.get(j)) + destinations.get(j);
                            long newRangeEnd = (range.end - sources.get(j)) + destinations.get(j);
                            newRanges.add(new Range(newRangeStart, newRangeEnd));
                            continue LOOP;
                        }
                        if (range.start >= sources.get(j) && range.start < sources.get(j) + intervalRange.get(j)
                                && range.end >= sources.get(j) + intervalRange.get(j)) {
                            long rangeStart = range.start - sources.get(j) + destinations.get(j);
                            long end = range.start + (sources.get(j) + intervalRange.get(j) - range.start);
                            long newRangeEnd = end - sources.get(j) + destinations.get(j);
                            newRanges.add(new Range(rangeStart, newRangeEnd));

                            long newRangeStart = sources.get(j) + intervalRange.get(j);
                            hack(newRanges, new Range(newRangeStart, range.end), destinations, sources, intervalRange);
                            continue LOOP;
                        }
                        if (range.start < sources.get(j) && range.end > sources.get(j) + intervalRange.get(j)) {
                            long newRangeEnd = range.start + (sources.get(j) - range.start) - 1;
                            newRanges.add(new Range(range.start, newRangeEnd));

                            long newRangeStart = (newRangeEnd + 1) - sources.get(j) + destinations.get(j);
                            newRangeEnd = range.end - sources.get(j) + destinations.get(j);
                            newRanges.add(new Range(newRangeStart, newRangeEnd));

                            newRangeStart = range.end + 1;
                            newRanges.add(new Range(newRangeStart, range.end));
                            continue LOOP;
                        }
                    }
                    newRanges.add(range);
                }
                newRanges.stream().forEach(System.out::println);
                System.out.println();
                ranges = newRanges.stream().toList();
                destinations.clear();
                sources.clear();
                intervalRange.clear();
                continue;
            }
            String[] map = lines.get(i)
                    .split(" ");
            destinations.add(Long.parseLong(map[0]));
            sources.add(Long.parseLong(map[1]));
            intervalRange.add(Long.parseLong(map[2]));
        }
        System.out.println("Part 2 answer: " + ranges.stream().min(Range::compare).get().start);
    }

    // hacky as all there is
    private static void hack(List<Range> ranges, Range range, List<Long> destinations, List<Long> sources,
            List<Long> intervalRange) {
        for (int j = 0; j < destinations.size(); j++) {
            if (range.start >= sources.get(j) && range.end <= sources.get(j) + intervalRange.get(j)) {
                long newRangeStart = (range.start - sources.get(j)) + destinations.get(j);
                long newRangeEnd = (range.end - sources.get(j)) + destinations.get(j);
                ranges.add(new Range(newRangeStart, newRangeEnd));
                return;
            }
            if (range.start >= sources.get(j) && range.start < sources.get(j) + intervalRange.get(j)
                    && range.end >= sources.get(j) + intervalRange.get(j)) {
                long rangeStart = range.start - sources.get(j) + destinations.get(j);
                long end = range.start + (sources.get(j) + intervalRange.get(j) - range.start);
                long newRangeEnd = end - sources.get(j) + destinations.get(j);
                ranges.add(new Range(rangeStart, newRangeEnd));
                return;
            }
            if (range.start < sources.get(j) && range.end > sources.get(j) + intervalRange.get(j)) {
                long newRangeEnd = range.start + (sources.get(j) - range.start) - 1;
                ranges.add(new Range(range.start, newRangeEnd));

                long newRangeStart = (newRangeEnd + 1) - sources.get(j) + destinations.get(j);
                newRangeEnd = range.end - sources.get(j) + destinations.get(j);
                ranges.add(new Range(newRangeStart, newRangeEnd));

                newRangeStart = range.end + 1;
                ranges.add(new Range(newRangeStart, range.end));
                return;
            }
        }
        ranges.add(range);
    }
}
