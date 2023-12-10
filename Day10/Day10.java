package Day10;

import java.util.ArrayList;
import java.util.List;

import Util.Utils;

public class Day10 {
    public static void main(String[] args) {
        part1();
        part2();
    }

    private static void part1() {
        List<String> lines = Utils.readAllLines("Day10/input.txt");
        int lineLength = lines.get(0).length();
        String input = lines.stream().reduce("", (str, line) -> str += line);

        int startingIndex = input.indexOf("S");
        int index = startingIndex;
        char[] pipes = { '|', '-', 'L', 'J', '7', 'F' };
        boolean[] ups = { true, false };
        boolean[] lefts = { true, false };
        MAINLOOP: for (char pipe : pipes) {
            for (boolean up : ups) {
                for (boolean left : lefts) {
                    String newMap = input.replace('S', pipe);
                    char currentChar = newMap.charAt(startingIndex);
                    int steps = -1;
                    boolean foundLoop = true;
                    index = startingIndex;
                    char lastOperation = 'd';
                    do {
                        steps++;
                        switch (currentChar) {
                            case '|':
                                if (up) {
                                    currentChar = newMap.charAt(index - lineLength);
                                    if (currentChar == 'F' || currentChar == '7' || currentChar == '|') {
                                        index = index - lineLength;
                                        lastOperation = 'u';
                                    } else {
                                        foundLoop = false;
                                        currentChar = '.';
                                    }
                                } else {
                                    currentChar = newMap.charAt(index + lineLength);
                                    if (currentChar == 'J' || currentChar == 'L' || currentChar == '|') {
                                        index = index + lineLength;
                                        lastOperation = 'd';
                                    } else {
                                        foundLoop = false;
                                        currentChar = '.';
                                    }
                                }
                                break;
                            case '-':
                                if (left) {
                                    currentChar = newMap.charAt(index - 1);
                                    if (currentChar == 'F' || currentChar == 'L' || currentChar == '-') {
                                        index--;
                                        lastOperation = 'l';
                                    } else {
                                        foundLoop = false;
                                        currentChar = '.';
                                    }
                                } else {
                                    currentChar = newMap.charAt(index + 1);
                                    if (currentChar == '7' || currentChar == 'J' || currentChar == '-') {
                                        index++;
                                        lastOperation = 'r';
                                    } else {
                                        foundLoop = false;
                                        currentChar = '.';
                                    }
                                }
                                break;
                            case 'L':
                                if (left && lastOperation == 'l') {
                                    up = true;
                                    currentChar = newMap.charAt(index - lineLength);
                                    lastOperation = 'u';
                                    index = index - lineLength;
                                } else if (!up) {
                                    left = false;
                                    lastOperation = 'r';
                                    currentChar = newMap.charAt(index + 1);
                                    index++;
                                }
                                break;
                            case 'J':
                                if (!left && lastOperation == 'r') {
                                    up = true;
                                    lastOperation = 'u';
                                    currentChar = newMap.charAt(index - lineLength);
                                    index = index - lineLength;
                                } else if (!up) {
                                    left = true;
                                    lastOperation = 'l';
                                    up = false;
                                    currentChar = newMap.charAt(index - 1);
                                    index--;
                                }
                                break;
                            case '7':
                                if (!left && lastOperation == 'r') {
                                    up = false;
                                    currentChar = newMap.charAt(index + lineLength);
                                    lastOperation = 'd';
                                    index = index + lineLength;
                                } else if (up) {
                                    left = true;
                                    lastOperation = 'l';
                                    currentChar = newMap.charAt(index - 1);
                                    index--;
                                }
                                break;
                            case 'F':
                                if (up && lastOperation == 'u') {
                                    lastOperation = 'r';
                                    left = false;
                                    currentChar = newMap.charAt(index + 1);
                                    index++;
                                } else if (left) {
                                    lastOperation = 'd';
                                    up = false;
                                    currentChar = newMap.charAt(index + lineLength);
                                    index = index + lineLength;
                                }
                                break;
                            case '.':
                                foundLoop = false;
                                continue MAINLOOP;
                        }
                    } while (index != startingIndex && currentChar != '.');
                    if (foundLoop && steps > 1) {
                        System.out.println(steps / 2 + 1);
                        break MAINLOOP;
                    }
                }
            }

        }
    }

    private static void part2() {
        List<String> lines = Utils.readAllLines("Day10/example3.txt");
        int lineLength = lines.get(0).length();
        String input = lines.stream().reduce("", (str, line) -> str += line);

        int startingIndex = input.indexOf("S");
        int index = startingIndex;
        char[] pipes = { '-', '|', 'F', '7', 'L', 'J' };
        boolean[] ups = { true, false };
        boolean[] lefts = { true, false };
        List<Integer> indexes = new ArrayList<>();
        MAINLOOP: for (char pipe : pipes) {
            for (boolean up : ups) {
                for (boolean left : lefts) {
                    String newMap = input.replace('S', pipe);
                    char currentChar = newMap.charAt(startingIndex);
                    int steps = -1;
                    boolean foundLoop = true;
                    index = startingIndex;
                    char lastOperation = 'd';
                    indexes = new ArrayList<>();
                    do {
                        indexes.add(index);
                        steps++;
                        switch (currentChar) {
                            case '|':
                                if (up) {
                                    currentChar = newMap.charAt(index - lineLength);
                                    if (currentChar == 'F' || currentChar == '7' || currentChar == '|') {
                                        index = index - lineLength;
                                        lastOperation = 'u';
                                    } else {
                                        foundLoop = false;
                                        currentChar = '.';
                                    }
                                } else {
                                    currentChar = newMap.charAt(index + lineLength);
                                    if (currentChar == 'J' || currentChar == 'L' || currentChar == '|') {
                                        index = index + lineLength;
                                        lastOperation = 'd';
                                    } else {
                                        foundLoop = false;
                                        currentChar = '.';
                                    }
                                }
                                break;
                            case '-':
                                if (left) {
                                    currentChar = newMap.charAt(index - 1);
                                    if (currentChar == 'F' || currentChar == 'L' || currentChar == '-') {
                                        index--;
                                        lastOperation = 'l';
                                    } else {
                                        foundLoop = false;
                                        currentChar = '.';
                                    }
                                } else {
                                    currentChar = newMap.charAt(index + 1);
                                    if (currentChar == '7' || currentChar == 'J' || currentChar == '-') {
                                        index++;
                                        lastOperation = 'r';
                                    } else {
                                        foundLoop = false;
                                        currentChar = '.';
                                    }
                                }
                                break;
                            case 'L':
                                if (left && lastOperation == 'l') {
                                    up = true;
                                    currentChar = newMap.charAt(index - lineLength);
                                    lastOperation = 'u';
                                    index = index - lineLength;
                                } else if (!up) {
                                    left = false;
                                    lastOperation = 'r';
                                    currentChar = newMap.charAt(index + 1);
                                    index++;
                                }
                                break;
                            case 'J':
                                if (!left && lastOperation == 'r') {
                                    up = true;
                                    lastOperation = 'u';
                                    currentChar = newMap.charAt(index - lineLength);
                                    index = index - lineLength;
                                } else if (!up) {
                                    left = true;
                                    lastOperation = 'l';
                                    up = false;
                                    currentChar = newMap.charAt(index - 1);
                                    index--;
                                }
                                break;
                            case '7':
                                if (!left && lastOperation == 'r') {
                                    up = false;
                                    currentChar = newMap.charAt(index + lineLength);
                                    lastOperation = 'd';
                                    index = index + lineLength;
                                } else if (up) {
                                    left = true;
                                    lastOperation = 'l';
                                    currentChar = newMap.charAt(index - 1);
                                    index--;
                                }
                                break;
                            case 'F':
                                if (up && lastOperation == 'u') {
                                    lastOperation = 'r';
                                    left = false;
                                    currentChar = newMap.charAt(index + 1);
                                    index++;
                                } else if (left) {
                                    lastOperation = 'd';
                                    up = false;
                                    currentChar = newMap.charAt(index + lineLength);
                                    index = index + lineLength;
                                }
                                break;
                            case '.':
                                foundLoop = false;
                                continue MAINLOOP;
                        }
                    } while (index != startingIndex && currentChar != '.');
                    if (foundLoop && steps > 1) {
                        System.out.println(steps / 2 + 1);
                        break MAINLOOP;
                    }
                }
            }
        }

        // try (PrintWriter pw = new PrintWriter("Day10/output.txt")) {
        // StringBuilder sb = new StringBuilder(input);
        // indexes.forEach(i -> {
        // switch (input.charAt(i)) {
        // case '-':
        // sb.setCharAt(i, '─');
        // break;
        // case '|':
        // sb.setCharAt(i, '│');
        // break;
        // case 'F':
        // sb.setCharAt(i, '┌');
        // break;
        // case '7':
        // sb.setCharAt(i, '┐');
        // break;
        // case 'L':
        // sb.setCharAt(i, '└');
        // break;
        // case 'J':
        // sb.setCharAt(i, '┘');
        // break;
        // }
        // });
        // for (int j = 0; j <= lineLength; j++) {
        // sb.insert(j * (lineLength + 1), "\n");
        // }
        // pw.println(sb.toString());
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // }

        StringBuilder sb = new StringBuilder(input);
        indexes.forEach(i -> {
            if (sb.charAt(i) != '-') {
                sb.setCharAt(i, '@');
            }
        });
        int answer = 0;
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                int size = lineLength - i;
                if (line.charAt(i) != '.') {
                    continue;
                }
                int counter = 0;
                for (int j = i + 1; j < lineLength; j++) {
                    if (line.charAt(j) == '@') {
                        counter++;
                    }
                }
            }
        }
        for (int i = 0; i < input.length(); i++) {
            int size = lineLength - (i % lineLength);
            int currChar = input.charAt(i);
            if (currChar != '.') {
                continue;
            }
            int counter = 0;
            for (int j = i + 1; j < i + size; j++) {
                if (sb.charAt(j) == '@') {
                    counter++;
                }
            }
            if (counter % 2 == 1) {
                answer++;
            }
        }
        System.out.println("Part 2 answer: " + answer);
    }
}