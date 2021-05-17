import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TooManyListenersException;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Welcome to my world... ;^)");

        Scanner scan = new Scanner(System.in);
        String data = scan.nextLine().trim();

        while (!data.equals("quit")) {
            if (data.equals("fibo")) {
                System.out.println("Enter n:");
                data = scan.nextLine().trim();

                int n = Integer.parseInt(data);

                System.out.println(fibonacci(n));
            }
            else if (data.equals("freq")) {
                System.out.println("Enter the path of the file you want to analyze:");
                data = scan.nextLine().trim();

                try {
                    Path path = Paths.get(data);

                    String toPrint = analyse_freq(path);
                    System.out.println(toPrint);
                } catch (InvalidPathException | IOException e) {
                    System.out.println("Unreadable file: " + e.getClass() + " " + e.getMessage());
                }
            }
            else {
                System.out.println("Unknown command");
            }
            data = scan.nextLine().trim();
        }
        
        scan.close();
    }

    public static int fibonacci(int n) {
        int u1 = 0;
        int u2 = 1;

        while (n > 0) {
            int temp = u1;
            u1 = u1 + u2;
            u2 = temp;
            n--;
        }

        return u1;
    }

    public static String analyse_freq(Path path) throws IOException {
        String content = Files.readString(path);

        if (content.isBlank()) {
            throw new IOException();
        }

        String[] words = content.toLowerCase().split("\\W");

        Map<String, Long> map = Arrays.stream(words)
                                    .filter(str -> !str.isBlank())
                                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        String result = map.entrySet().stream()
                                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                    .limit(3)
                                    .map(e -> e.getKey())
                                    .collect(Collectors.joining(" "));

        return result;
    }
}
