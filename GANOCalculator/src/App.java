import java.util.Scanner;

public class App {
    static Scanner input = new Scanner(System.in);
    static String letterGrade, isUserDone;
    static int totalAKTS = 0;
    static double GANO = 0, totalGrade = 0;

    public static void main(String[] args) {
        System.out.println(
                "Choose which operand you want to use: (0; to convert grade to letter grade, 1; to calculate GANO)");
        String operand = input.nextLine();
        if (Integer.parseInt(operand) != 0 && Integer.parseInt(operand) != 1) {
            System.out.println("Input is valid!\nPlese enter the value as shown.");
            System.out.println(
                    "Choose which operand you want to use: (0; to convert grade to letter grade, 1; to calculate GANO)");
        }
        if (Integer.parseInt(operand) == 0) {
            System.out.println("Enter your grade: ");
            letterGrade = convertToLetter(input.nextInt());
            System.out.println("Your letter grade is > " + letterGrade);
        }
        if (Integer.parseInt(operand) == 1) {
            boolean userChoice = true;
            System.out.println(
                    "Enter your letter grade and ECTS(AKTS) in order with blanks: (AA 6)");
            String s = input.nextLine();
            String[] sa = s.split(" ");
            totalAKTS += Integer.parseInt(sa[1]);
            totalGrade += (gradeCalculator(sa[0]) * Double.parseDouble(sa[1]));
            System.out.println("total akts and total grade before loop > " + totalAKTS + "\t" + totalGrade);
            while (userChoice) {
                sa[0] = null;
                sa[1] = null;
                System.out.println("If you finished with your lessons just type 'done', else please go on.");
                isUserDone = input.nextLine();
                if (isUserDone.equalsIgnoreCase("done")) {
                    break;
                }
                System.out.println(
                        "Enter your letter grade and ECTS(AKTS) in order with blanks: (AA 6)");
                s = input.nextLine();
                sa = s.split(" ");
                totalAKTS += Integer.parseInt(sa[1]);
                totalGrade += gradeCalculator(sa[0]) * Double.parseDouble(sa[1]);
                System.out.println("total akts and total grade in loop > " + totalAKTS + "\t" + totalGrade);
            }
            GANO = totalGrade / totalAKTS;
            System.out.println("User's GANO > " + GANO);
        }
    }

    static String convertToLetter(int grade) {
        if (grade <= 44) {
            return "FF";
        } else if (grade <= 49) {
            return "FD";
        } else if (grade <= 54) {
            return "DD";
        } else if (grade <= 64) {
            return "DC";
        } else if (grade <= 74) {
            return "CC";
        } else if (grade <= 79) {
            return "CB";
        } else if (grade <= 84) {
            return "BB";
        } else if (grade <= 89) {
            return "BA";
        } else if (grade <= 100)
            return "AA";
        else {
            System.out.println("Invalid input!\nGrade should be less then 100.");
            grade = input.nextInt();
            return convertToLetter(grade);
        }

    }

    static double gradeCalculator(String letterGrade) {
        if (letterGrade.equalsIgnoreCase("AA")) {
            return 4.0;
        } else if (letterGrade.equalsIgnoreCase("BA")) {
            return 3.5;
        } else if (letterGrade.equalsIgnoreCase("BB")) {
            return 3.0;
        } else if (letterGrade.equalsIgnoreCase("CB")) {
            return 2.5;
        } else if (letterGrade.equalsIgnoreCase("CC")) {
            return 2.0;
        } else if (letterGrade.equalsIgnoreCase("DC")) {
            return 1.5;
        } else if (letterGrade.equalsIgnoreCase("DD")) {
            return 1.0;
        } else if (letterGrade.equalsIgnoreCase("FD")) {
            return 0.5;
        } else if (letterGrade.equalsIgnoreCase("FF")) {
            return 0;
        } else {
            System.out
                    .println("Invalid letter grade!\nPlease enter a valid input (just the letter grade): (AA, BB, FG)");
            return gradeCalculator(input.nextLine());
        }

    }
}
