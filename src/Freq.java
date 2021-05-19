import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Freq implements Command {

    @Override
    public String name() {
        return "freq";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Enter the path of the file you want to analyze:");
        String data = scanner.nextLine().trim();

        Path path;
        String content;
        try {
            path = Paths.get(data);
            content = Files.readString(path);

        } catch (InvalidPathException | IOException e) {
            System.out.println("Unreadable file: " + e.getClass() + " " + e.getMessage());
            return false;
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

        System.out.println(result);
        return false;
    }
    
}
