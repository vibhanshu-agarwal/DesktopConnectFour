import java.util.*;

class SetUtils {

    /**
     * @return symmetric difference between set1 and set2
     */
    public static Set<String> symmetricDifference(Set<String> set1, Set<String> set2) {
        // write your code here
        //Get a copy of a set
        Set<String> copySet1 = new HashSet<>(set1);
        Set<String> copySet2 = new HashSet<>(set2);
        copySet1.removeAll(set2);
        copySet2.removeAll(set1);
        Set<String> result = new HashSet<>(copySet1);
        result.addAll(copySet2);
        return Set.of(result.toArray(String[]::new));
    }

}

/* Do not change the code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String strSet1 = scanner.nextLine();
        String strSet2 = scanner.nextLine();

        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        if (!Objects.equals(strSet1, "empty")) {
            Collections.addAll(set1, strSet1.split("\\s+"));
        }

        if (!Objects.equals(strSet2, "empty")) {
            Collections.addAll(set2, strSet2.split("\\s+"));
        }

        Set<String> resultSet = SetUtils.symmetricDifference(set1, set2);

        System.out.println(String.join(" ", resultSet));
    }
}