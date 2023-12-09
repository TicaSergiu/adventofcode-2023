package Day7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.Utils;

public class Part2 {
    public static class Hand {
        private String cards;
        private int bid;
        private int type = 0;
        static Map<Character, Integer> map = new HashMap<>();
        static {
            map.put('J', 1);
            map.put('2', 2);
            map.put('3', 3);
            map.put('4', 4);
            map.put('5', 5);
            map.put('6', 6);
            map.put('7', 7);
            map.put('8', 8);
            map.put('9', 9);
            map.put('T', 10);
            map.put('Q', 12);
            map.put('K', 13);
            map.put('A', 14);
        }

        Hand(String cards, int bid) {
            this.cards = cards;
            this.bid = bid;
            Map<Character, Integer> m = new HashMap<>();
            for (int i = 0; i < this.cards.length(); i++) {
                char currentChar = this.cards.charAt(i);
                if (m.containsKey(Character.valueOf(currentChar))) {
                    m.computeIfPresent(Character.valueOf(currentChar), (c, num) -> num += 1);
                } else {
                    m.put(Character.valueOf(currentChar), 1);
                }
            }

            if (m.containsKey('J') && m.get('J') != 5) {
                char c = 'J';
                int num = 0;
                for (var iter : m.entrySet()) {
                    if (iter.getValue() > num && iter.getKey() != 'J') {
                        num = iter.getValue();
                        c = iter.getKey();
                    }
                }
                m.compute(c, (card, n) -> n += m.get('J'));
                m.remove('J');
            }

            for (Map.Entry<Character, Integer> iter : m.entrySet()) {
                switch (iter.getValue()) {
                    case 5:
                        type = 10;
                        break;
                    case 4:
                        type = 9;
                        break;
                    case 3:
                        if (type == 1) {
                            type = 0;
                        }
                        type += 5;
                        break;
                    case 2:
                        if (type == 1) {
                            type = 0;
                        }
                        type += 2;
                        break;
                    case 1:
                        if (type == 0) {
                            type = 1;
                        }
                }
            }
        }

        static public int compare(Hand e1, Hand e2) {
            if (e1.type > e2.type) {
                return 1;
            }
            if (e2.type > e1.type) {
                return -1;
            }
            for (int i = 0; i < e1.cards.length(); i++) {
                if (map.get(e1.cards.charAt(i)) > map.get(e2.cards.charAt(i))) {
                    return 1;
                }
                if (map.get(e1.cards.charAt(i)) < map.get(e2.cards.charAt(i))) {
                    return -1;
                }
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        part1();
    }

    private static void part1() {
        List<String> lines = Utils.readAllLines("Day7/input.txt");

        List<Hand> hands = new ArrayList<>();
        lines.forEach(line -> {
            hands.add(new Hand(line.split(" ")[0], Integer.parseInt(line.split(" ")[1])));
        });

        Collections.sort(hands, Hand::compare);

        int answer = 0;
        for (int i = 0; i < hands.size(); i++) {
            answer += hands.get(i).bid * (i + 1);
        }

        System.out.println("Part 1 answer: " + answer);
    }
}
