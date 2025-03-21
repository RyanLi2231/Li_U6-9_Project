import java.util.Scanner;

public class Logic {
    private Scanner scan = new Scanner(System.in);

    public Logic() {
    }

    private void menu() {
        int input = 0;
        while (input != 5) {
            System.out.println("1. Go to farm");
            System.out.println("2. Explore outside");
            System.out.println("3. Check Day");
            System.out.println("4. Next Day");
            System.out.println("5. End Game");
            input = scan.nextInt();
        }
    }
}
