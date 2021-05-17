import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Welcome to my world... ;^)");

        Scanner scan = new Scanner(System.in);
        String data = scan.nextLine().trim();

        while (!data.equals("quit")) {
            System.out.println("Unknown command");
            data = scan.nextLine().trim();
        }
        
        scan.close();
    }
}
