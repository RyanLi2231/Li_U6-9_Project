import javax.swing.*;

public class Calender {
    private int day;
    private int[] calDay; // Month / Day / Season
    private final String[] MONTHS = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"};
    private final String[] SEASONS = {"Winter", "Spring", "Summer", "Autumn"};

    public Calender() {
        day = 0;
        calDay = new int[3];
        randomCalDay();
    }

    public int[] getCalDay() {
        return calDay;
    }

    public String printDay() {
        return ("Today is day " + calDay[1] + " of the month of " + MONTHS[calDay[0]] + " of " + SEASONS[calDay[2]]);
    }

    public void adjustDay() {
        day++;
        calDay[1]++;
        checkCal();
        calculateSeason();
        printDay();
    }

    private void checkCal() {
        if (calDay[1] > 30) {
            calDay[0]++;
            calDay[1] = 1;
        }
        if (calDay[0] > 11) {
            calDay[0] = 0;
        }
    }

    private void randomCalDay() {
        int rand1 = (int) (Math.random() * 12);
        int rand2 = (int) (Math.random() * 30 + 1);
        calDay[0] = rand1;
        calDay[1] = rand2;
        calculateSeason();
    }

    private void calculateSeason() {
        int month = calDay[0];
        if (month <= 1 || month >= 11) {
            calDay[2] = 0;
        } else if (month >= 2 && month <= 4) {
            calDay[2] = 1;
        } else if (month >= 5 && month <= 7) {
            calDay[2] = 2;
        } else if (month >= 8 && month <= 10) {
            calDay[2] = 3;
        }
    }
}
