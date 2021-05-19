import java.util.Scanner;

public class Fibo implements Command {

    @Override
    public String name() {
        return "fibo";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Enter n:");
        String data = scanner.nextLine().trim();

        int n = Integer.parseInt(data);

        int u1 = 0;
        int u2 = 1;

        while (n > 0) {
            int temp = u1;
            u1 = u1 + u2;
            u2 = temp;
            n--;
        }

        System.out.println(u1);
        return false;
    }
    
}
