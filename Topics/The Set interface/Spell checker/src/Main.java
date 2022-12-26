import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //The dictionary of words
        Set<String> dictionary = new HashSet<>();
        //The set of input text words
        Set<String> text = new HashSet<>();

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            dictionary.add(scanner.nextLine());
        }
        //The dictionary of words
        dictionary =
                dictionary.stream().map(String::toLowerCase).collect(Collectors.toSet());

        int m = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < m; i++) {
            text.addAll(Arrays.asList(scanner.nextLine().split(" ")));
        }

        Set<String> result = new HashSet<>(text);

        result = result.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        result.removeAll(dictionary);

        result.forEach(System.out::println);
    }
}