import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Welcome to my world... ;^)");

        Scanner scan = new Scanner(System.in);
        String data = scan.nextLine().trim();
        if (!data.equals("quit")) {
            System.out.println("Uknown command");
        }
        scan.close();
    }
}
