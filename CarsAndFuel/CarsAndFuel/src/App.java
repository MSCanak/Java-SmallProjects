import java.io.File;
import java.util.Scanner;

/*NOTES FOR THE PROJECT
 * Check the calculation methods of each operand to avoid miscalculations again and again.
 */

public class App {
    private static double choosenCarAverageUsage = 0;
    private static double choosenCarTank = 0;

    private static double gasolinePrice, gasolineInTank;
    private static String choosenCarName = null;

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        File carModels = new File(
                "C:/Users/MSC/Desktop/VSCode-Projects/JavaProjects/CarsAndFuel/CarsAndFuel/src/cars.txt");
        File carsAndFuels = new File(
                "C:/Users/MSC/Desktop/VSCode-Projects/JavaProjects/CarsAndFuel/CarsAndFuel/src/cars-and-fuels.txt");
        Scanner carScanner = new Scanner(carModels);
        Scanner carsAndFuelsScanner = new Scanner(carsAndFuels);

        // list the cars below and take the information
        System.out.println("Choose below which car is yours.");
        while (carScanner.hasNextLine()) {
            String[] cars = carScanner.nextLine().split("\t");
            System.out.println(cars[0] + " " + cars[1]);
        }

        int userCarChoice = input.nextInt();

        while (carsAndFuelsScanner.hasNextLine()) {
            String[] carFuels = carsAndFuelsScanner.nextLine().split("_");
            if (userCarChoice == Integer.parseInt(carFuels[0])) {
                choosenCarName = carFuels[1];
                choosenCarTank = Double.parseDouble(carFuels[2]);
                choosenCarAverageUsage = Double.parseDouble(carFuels[3]);
            }
        }

        // take current tank info from the user
        System.out.println("Enter your tank information > (e.g. 1/4, 1/1 etc.)");
        String[] tankInfo = input.next().split("/");
        gasolineInTank = Double.parseDouble(tankInfo[0]) * choosenCarTank / Double.parseDouble(tankInfo[1]);

        // take current gasoline price per liter info from the user
        System.out.println("Enter gasoline price for per liter > ");
        gasolinePrice = input.nextDouble();
        System.out.println(
                "Choose what operand you want to use: \n0 > for estimating needed money for specified trip.\n1 > for estimating your travel distance according to your money.\n2 > for estimating how much money to full your tank.");
        int userChoice = input.nextInt();
        if (userChoice == 0) {
            // recieve the total distance that user will take
            double distance;
            System.out.println("Enter your distance > ");
            distance = input.nextDouble();

            // calculate gallons and price and show the output to the user
            if (distance - (gasolineInTank * 100 / choosenCarAverageUsage) <= 0) {
                System.out.printf("The gas in the tank is enough for %s. You do not have to pay for anything.\n",
                        choosenCarName);
                System.out.printf("The gas left in the tank of %s is %.1f liter.\n", choosenCarName,
                        gasolineInTank - (distance * choosenCarAverageUsage / 100));
            } else {
                System.out.printf("Total price that trip will cost for %s > %.2f\n", choosenCarName,
                        (((distance - (gasolineInTank * 100 / choosenCarAverageUsage)) * choosenCarAverageUsage / 100)
                                * gasolinePrice));
            }
        } else if (userChoice == 1) {
            // take the users total money
            System.out.println("Enter your total money > ");
            double userMoney = input.nextDouble();

            // calculte how much can user go and display to user
            System.out.printf("The distance you can travel with this money with %s > %.3f km.", choosenCarName,
                    gasolineInTank + (userMoney / gasolinePrice) < choosenCarTank
                            ? (userMoney / gasolinePrice / choosenCarAverageUsage * 100
                                    + gasolineInTank / choosenCarAverageUsage * 100)
                            : choosenCarTank * 100 / choosenCarAverageUsage);
        } else if (userChoice == 2) {
            // calculate how much money user will have to pay to full the tank
            System.out.printf("The money user should pay > %.2f TL", (choosenCarTank - gasolineInTank) * gasolinePrice);
        }
        input.close();
        carScanner.close();
        carsAndFuelsScanner.close();
    }
}
