import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Predict implements Command {

    @Override
    public String name() {
        return "predict";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Enter the path of the file you want me to learn:");
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
        List<String> cleanWords = Arrays.stream(words)
                                    .filter(str->!str.isBlank())
                                    .collect(Collectors.toList());

        Map<String, Map<String, Integer>> map = new HashMap<>();

        for (int i = 0; i < cleanWords.size() - 1; i++) {
            String word = cleanWords.get(i);

            if (!map.containsKey(word)) {
                Map <String, Integer> assoc = new HashMap<>();
                
                String next_word = cleanWords.get(i + 1);
                assoc.put(next_word, 1);

                map.put(word, assoc);
            }
            else {
                Map <String, Integer> old = map.get(word);

                String next_word = cleanWords.get(i + 1);

                if (old.containsKey(next_word)) {
                    int value = old.get(next_word);
                    value += 1;
                    old.replace(next_word, value);
                }
                else {
                    old.put(next_word, 1);
                }

                map.replace(word, old);
            }
        }

        Map<String, String> finalList = new HashMap<>();
        for (String word : map.keySet()) {
            Map<String, Integer> assocs = map.get(word);

            List<String> sorted = assocs.entrySet().stream()
                                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                        .limit(1)
                                        .map(e -> e.getKey())
                                        .collect(Collectors.toList());

            finalList.put(word, sorted.get(0));
        }

        System.out.println("Enter a word:");
        data = scanner.nextLine().trim();
        String toPrint = data;
        String word = toPrint;

        for (int i = 0; i < 19; i++) {
            word = finalList.get(word);
            toPrint += " " + word;
        }

        System.out.println(toPrint);
 
        return false;
    }
    
}
