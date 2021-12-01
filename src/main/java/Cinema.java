import java.util.Scanner;

/**
 * @author tobukhova on 11/27/21
 * testJetBrains
 **/
public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        String[][] cinemaArr = getCinemaArray(rows, seats);
        int numberOfPurchasedTickets = 0;
        int currentIncome = 0;

        boolean toContinue = true;
        do {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            byte menu = scanner.nextByte();

            switch (menu) {
                case 1:
                    printArray(cinemaArr);
                    break;
                case 2:
                    currentIncome += buyTicket(cinemaArr);
                    numberOfPurchasedTickets++;
                    break;
                case 3:
                    statistics(cinemaArr, numberOfPurchasedTickets, currentIncome);
                    break;
                case 0:
                    toContinue = false;
                    break;
                default:
                    System.out.println("\nWrong input!");
                    break;
            }
        } while (toContinue);
    }

    private static void statistics(String[][] cinemaArr, int number, int curIncome) {
        System.out.println();
        numberOfPurchasedTickets(number);
        percentageOfPurchasedTickets(cinemaArr, number);
        currentIncome(curIncome);
        totalIncome(cinemaArr);
    }

    private static void currentIncome(int curIncome) {
        System.out.println("Current income: $" + curIncome);
    }

    private static void percentageOfPurchasedTickets(String[][] cinemaArr, int number) {
        int rows = cinemaArr.length - 1;
        int seats = cinemaArr[0].length - 1;
        float numberOfSeats = rows * seats;
        float percentagePrice = number / numberOfSeats * 100;
        String result = String.format("Percentage: %.2f", percentagePrice);
        System.out.println(result + "%");
    }

    private static void numberOfPurchasedTickets(int number) {
        System.out.println("Number of purchased tickets: " + number);
    }

    private static void totalIncome(String[][] cinemaArr) {
        int rows = cinemaArr.length - 1;
        int seats = cinemaArr[0].length - 1;
        int numberOfSeats = rows * seats;
        int totalPrice;

        if (numberOfSeats < 60) {
            totalPrice = numberOfSeats * 10;
        } else {
            totalPrice = seats * (rows / 2 * 10 + (rows - rows / 2) * 8);
        }
        System.out.println("Total income: $" + totalPrice);
    }

    private static int buyTicket(String[][] cinemaArr) {
        boolean correctPlace = false;
        int rowNum, seatNum;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nEnter a row number:");
            rowNum = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNum = scanner.nextInt();

            int rows = cinemaArr.length - 1;
            int seats = cinemaArr[0].length - 1;
            if ((rowNum > rows) || (seatNum > seats)) {
                System.out.println("\nWrong input!");
            } else if (cinemaArr[rowNum][seatNum].equals("B")) {
                System.out.println("\nThat ticket has already been purchased!");
            } else {
                correctPlace = true;
            }

        } while (!correctPlace);

        cinemaArr[rowNum][seatNum] = "B";

        return getThePrice(cinemaArr.length - 1, cinemaArr[0].length - 1, rowNum);
    }

    private static int getThePrice(Integer rows, Integer seats, Integer rowNum) {
        int numberOfSeats = rows * seats;
        int ticketPrice;

        if ((numberOfSeats >= 60) && (rowNum > rows / 2)) {
            ticketPrice = 8;
        } else {
            ticketPrice = 10;
        }

        System.out.println("\nTicket price: $" + ticketPrice);
        return ticketPrice;
    }

    public static String[][] getCinemaArray(int seats, int rows) {

        String[][] finalCinema = new String[seats + 1][rows + 1];

        for (int i = 0; i <= seats; i++) {
            for (int j = 0; j <= rows; j++) {
                if ((i == 0) && (j == 0)) {
                    finalCinema[i][j] = " ";
                } else if ((i == 0) || (j == 0)) {
                    finalCinema[i][j] = (i == 0) ? String.valueOf(j) : String.valueOf(i);
                } else {
                    finalCinema[i][j] = "S";
                }
            }
        }
        return finalCinema;
    }

    public static void printArray(String[][] array) {
        System.out.println("\nCinema:");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j]);
                if (j < array[0].length - 1) {
                    System.out.print(" ");
                } else {
                    System.out.print("\n");
                }
            }
        }
    }
}
