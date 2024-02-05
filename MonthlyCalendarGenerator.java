//
// Name: Burke, Spencer
// Project: 3
// Due: 10-19-2023 
// Course: cs-1400-02-f23
//
// Description:
// This program will prompt for a month and year, and use that information to output a calendar for that info.
//
import java.io.*;
import java.util.Scanner;

public class MonthlyCalendarGenerator {
    static int getDayOfTheWeek(int day, int month, int year) {
        int a, y, m, d;
        a = (14 - month) / 12;
        y = year - a;
        m = month + (12 * a) - 2;
        d = (day + y + (y/4) - (y/100) + (y/400) + ((31 * m) / 12)) % 7;
        return d;
    }

    static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    static int getNumberOfDaysInMonth(int month, int year) {
        int numberOfDays;
        switch (month) {
            case 4: case 6: case 9: case 11:
                numberOfDays = 30;
                break;
            case 2:
                numberOfDays = isLeapYear(year) ? 29 : 28;
                break;
            default:
                numberOfDays = 31;
                break;
        }
        return numberOfDays;
    }

    static String getMonthName(int month) {
        String[] monthNames = {
            "January", "February", "March", "April",
            "May", "June", "July", "August",
            "September", "October", "November", "December"
        };
        return monthNames[month-1];
    }

    public static void main(String[] args) throws IOException {
        final int ITEM_PERLINE = 7;
        Scanner io = new Scanner(System.in);
        int perLine = 0, month, year, numDaysInMonth, firstDayOfTheWeek;

        System.out.println("Calendar by S. Burke\n");
        System.out.print("Enter month year ? ");
        month = io.nextInt();
        year = io.nextInt();

        firstDayOfTheWeek = getDayOfTheWeek(1, month, year);
        numDaysInMonth = getNumberOfDaysInMonth(month, year);

        PrintWriter fio = new PrintWriter(month + "-" + year + ".txt");
        System.out.println("\n" + month + "-" + year + ".txt generated.");

        fio.printf("%s %d\n\n", getMonthName(month), month);
        fio.println("Sun  Mon  Tue  Wed  Thu  Fri  Sat");
        fio.println("---------------------------------");

        // print skipped line and save info
        for (int i = 1; i <= firstDayOfTheWeek; i++) {
            fio.print("   ");
            if (++perLine == ITEM_PERLINE) {
                fio.println();
                perLine = 0;
            }
            else {
                fio.print("  ");
            }
        }

        // print rest of lines
        for (int day = 1; day <= numDaysInMonth; day++) {
                fio.printf("%3d", day);
            if (++perLine == ITEM_PERLINE) {
                fio.println();
                perLine = 0;
            }
            else {
                // if we are out of days print the newline
                if (day == numDaysInMonth)
                    fio.println();
                // if we have more days put the spacer
                else
                    fio.print("  ");
            }
        }
        io.close();
        fio.close();
    }
}