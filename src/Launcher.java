import java.util.Scanner;

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
}
