import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class RandomPasswordGenerator 
{
    static String chars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuopasdfghjklizxcvbnm1234567890*?!'+%&-";
    static int randomPasswordLength = chars.length();
    public static void main(String[] args) throws IOException {
        System.out.println("Enter what amount of characters you want to use in your password");
        Scanner input = new Scanner(System.in);
        int passwordCount = input.nextInt();
        String userPassword = "";
        System.out.println(userPassword);
        File file = new File("C:/Users/MSC/Desktop/VSCode-Projects/JavaProjects/RandomPasswordGenerator/passwords.txt");
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (int i = 0; i < passwordCount; i++) {
            userPassword = passwordGenerator(passwordCount, chars);
            printWriter.println(userPassword);
        }
        printWriter.close();
        input.close();
    }


 public static String passwordGenerator(int count, String password) 
{
    String newPassword = "";
    for (int i = 0; i < count; i++) {
        newPassword += password.charAt((int)(Math.random() * randomPasswordLength));
    }
    return newPassword;
}

}