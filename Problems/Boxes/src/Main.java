import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] box1 = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] box2 = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int vol1 = volume(box1);
        int vol2 = volume(box2);
         boolean b = false;
        if (vol1 < vol2) {
            b = lesserThanVector(box1, box2);
            System.out.println(b ? "Box 1 < Box 2" : "Incompatible");
        } else if (vol2 < vol1) {
            b = lesserThanVector(box2, box1);
            System.out.println(b ? "Box 1 > Box 2" : "Incompatible");
        } else {
            System.out.println("Incompatible");
        }
    }

    public static int volume(int[] size) {
        if (size.length == 3) {
            return size[0] * size[1] * size[2];
        }
        return -1;
    }

    public static boolean lesserThanVector(int[] vector1, int[] vector2) {
        Arrays.sort(vector1);
        Arrays.sort(vector2);
        for (int i = 0; i < vector1.length; i++) {
            if (vector1[i] >= vector2[i]) {
                return false;
            }
        }
        return true;
    }
}