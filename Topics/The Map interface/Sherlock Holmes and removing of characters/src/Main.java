import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String word1 = scanner.nextLine().toLowerCase();
        String word2 = scanner.nextLine().toLowerCase();
        //A Map of letters and their frequencies for word 1
        Map<Character, Integer> word1Map = new HashMap<>();
        //A Map of letters and their frequencies for word 2
        Map<Character, Integer> word2Map = new HashMap<>();

        //Add all characters and their frequencies to word1Map
        buildMapFromWordChars(word1, word1Map);

        //Add all characters and their frequencies to word2Map
        buildMapFromWordChars(word2, word2Map);

        Map<Character, Integer> letterDiffMap = new HashMap<>();
        word1Map.keySet().forEach(key -> {
            if (!word2Map.containsKey(key)) {
                letterDiffMap.put(key, word1Map.get(key));
            } else if (!Objects.equals(word2Map.get(key), word1Map.get(key))) {
                //get absolute difference between the frequencies of the same letter in both words
                letterDiffMap.put(key, Math.abs(word2Map.get(key) - word1Map.get(key)));
            }
        });

        word2Map.keySet().forEach(key -> {
            if (!word1Map.containsKey(key)) {
                letterDiffMap.put(key, word2Map.get(key));
            } else if (!Objects.equals(word1Map.get(key), word2Map.get(key))) {
                //get absolute difference between the frequencies of the same letter in both words
                letterDiffMap.putIfAbsent(key, Math.abs(word2Map.get(key) - word1Map.get(key)));
            }
        });

        AtomicInteger sum = new AtomicInteger();
        letterDiffMap.forEach((key, value) -> sum.addAndGet(value));

        System.out.println(sum.get());

    }

    private static void buildMapFromWordChars(String word, Map<Character, Integer> wordMap) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (wordMap.containsKey(c)) {
                wordMap.put(c, wordMap.get(c) + 1);
            } else {
                wordMap.put(c, 1);
            }
        }
    }
}