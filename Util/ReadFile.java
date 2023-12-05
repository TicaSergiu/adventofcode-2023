package Util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadFile {
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
}