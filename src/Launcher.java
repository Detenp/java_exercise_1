import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Welcome to my world... ;^)");

        List<Command> commands = new ArrayList<Command>();
        
        commands.add(new Quit());
        commands.add(new Fibo());
        commands.add(new Freq());

        Scanner scan = new Scanner(System.in);

        boolean bool = false;
        do {
            String data = scan.nextLine().trim();

            boolean found = false;
            for (Command command : commands) {
                if (command.name().equals(data))
                {
                    found = true;
                    bool = command.run(scan);
                    break;
                }
            }

            if (!found)
                System.out.println("Unknown command");


        } while(!bool);
        
        scan.close();
    }
}
