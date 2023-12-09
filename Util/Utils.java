package Util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Utils {
    public static List<String> readAllLines(String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static List<String> readAllLinesAndRemoveEmpty(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.removeIf(line -> line.equals(""));
            return lines;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public static Integer[] parseListOfNums(String[] stringNums) {
        Integer[] ret = new Integer[stringNums.length];

        for (int i = 0; i < ret.length; i++) {
            ret[i] = Integer.parseInt(stringNums[i]);
        }
        return ret;
    }
}