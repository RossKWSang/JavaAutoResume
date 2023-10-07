package view.scanner;

import java.util.Scanner;

public class IntroductionScanner {
    private final Scanner introductionScanner=new Scanner(System.in);

    public String getSelfIntroduction() {
        String result="";

        while (true) {
            String scanLine = introductionScanner.nextLine();
            if(scanLine.equals(" ")) {
                return result;
            }
            result += scanLine + "\n";
        }
    }
}
