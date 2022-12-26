// You can experiment here, it won’t be checked

import java.util.*;

public class Task {
  public static void main(String[] args) {
    // put your code here
    Objects.equals(Set.of(1, 2, 3), Set.of(1, 3));    // false
    Objects.equals(Set.of(1, 2, 3), Set.of(1, 2, 3)); // true
    Objects.equals(Set.of(1, 2, 3), Set.of(1, 3, 2)); // true

    Set<Integer> numbers = new HashSet<>();

    numbers.add(1);
    numbers.add(2);
    numbers.add(3);

    Objects.equals(numbers, Set.of(1, 2, 3)); // true
  }
}
