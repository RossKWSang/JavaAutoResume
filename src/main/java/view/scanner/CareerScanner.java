package view.scanner;

import java.util.Scanner;

public class CareerScanner {
    private final Scanner careerScanner=new Scanner(System.in);

    public String[] getCareerInformation() {
        String[] result={"Terminate Loop"};

        System.out.println("경력 정보를 입력하세요 (종료는 q)");
        System.out.println("근무기간 근무처 담당업무 근속연수");
        String scanLine=careerScanner.nextLine();
        if(scanLine.equals("q")) {
            return result;
        }
        return scanLine.split(" ");
    }
}
