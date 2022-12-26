// You can experiment here, it won’t be checked

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Task {
  public static void main(String[] args) {
    // put your code here
    Map<String, Integer> namesToAges1 = Map.of("John", 30, "Alice", 28);
    Map<String, Integer> namesToAges2 = new HashMap<>();

    namesToAges2.put("Alice", 28);
    namesToAges2.put("John", 30);

    System.out.println(Objects.equals(namesToAges1, namesToAges2));
  }
}
