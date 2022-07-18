import java.util.Calendar;
import java.util.Scanner;

public class TimeEstimater {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the file size: (2.5gb, 100mb)");
        String fileSize = input.nextLine();
        System.out.println("Enter the download speed: (100mbps, 1gbps, 2mb/s, 1gb/s)");
        String downloadSpeed = input.nextLine();

        if (!(fileSize.endsWith("gb")) && !(fileSize.endsWith("mb"))) {
            System.out.println("Invalid input please enter the value as shown!");
            System.out.println("Enter the file size: (2.5gb, 100mb)");
            fileSize = input.nextLine();
        }

        if (!(downloadSpeed.endsWith("gbps")) && !(downloadSpeed.endsWith("mbps")) && !(downloadSpeed.endsWith("mb/s"))
                && !(downloadSpeed.endsWith("gb/s"))) {
            System.out.println("Invalid input please enter the value as shown!");
            System.out.println("Enter the download speed: (100mbps, 1gbps, 2mb/s, 1gb/s)");
            downloadSpeed = input.nextLine();
        }

        double newFileSize = Double.parseDouble(fileSize.substring(0, fileSize.length() - 2));
        double newDownloadSpeed = Double.parseDouble(downloadSpeed.substring(0, downloadSpeed.length() - 4));
        int totalTime, totalDays, totalHours, totalMins, totalSecs;
        int currentSeconds, currentHours, currentMins, currentDays;

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        if (fileSize.endsWith(("gb"))) {
            newFileSize *= 1000;
        }

        if (downloadSpeed.endsWith("gpbs")) {
            newDownloadSpeed *= 1000;
            totalTime = (int) (newFileSize / newDownloadSpeed * 8);
        } else if (downloadSpeed.endsWith("gb/s")) {
            newDownloadSpeed *= 1000;
            totalTime = (int) (newFileSize / newDownloadSpeed);
        } else {
            totalTime = (int) (newFileSize / newDownloadSpeed);
        }

        // These values keep the download time
        totalSecs = totalTime % 60;
        totalTime /= 60;
        totalMins = totalTime % 60;
        totalTime /= 60;
        totalHours = totalTime % 24;
        totalTime /= 24;
        totalDays = totalTime;

        // these values keep the time when the download process will be finished
        currentSeconds = (cal.get(Calendar.SECOND) + totalSecs) % 60;
        currentMins = (cal.get(Calendar.MINUTE) + totalMins + ((cal.get(Calendar.SECOND) + totalSecs) / 60)) % 60;
        currentHours = cal.get((Calendar.HOUR_OF_DAY)) + totalHours
                + ((cal.get(Calendar.MINUTE) + totalMins + ((cal.get(Calendar.SECOND) + totalSecs) / 60)) / 60) % 60;
        currentDays = cal.get(Calendar.DAY_OF_MONTH) + totalDays + (cal.get((Calendar.HOUR_OF_DAY)) + totalHours
                + ((cal.get(Calendar.MINUTE) + totalMins + ((cal.get(Calendar.SECOND) + totalSecs) / 60)) / 60)) / 24;

        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), currentDays, currentHours, currentMins, currentSeconds);

        System.out.println("Total time estimated is > " + totalDays + " days " + totalHours + " hours " + totalMins
                + " minutes and " + totalSecs + " seconds.");
        System.out.println("The time when the process will be finished is > " + cal.getTime());

        input.close();
    }
}