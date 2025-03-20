import java.util.Scanner;

public class Logic {
    Scanner scan = new Scanner(System.in);
    Frame frame = new Frame();
    Calender calender = new Calender(frame);
    DisplayPanel panel = frame.getPanel();


    public Logic() {
        start();
    }

    private void start() {
        calender.printDay();
        panel.addMessage("You have awakened in a farm");
        menu();
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
            action(input);
        }
    }

    private void action(int input) {
        if (input == 3) {
            calender.printDay();
        } else if (input == 4) {
            calender.adjustDay();
        }
    }
}
