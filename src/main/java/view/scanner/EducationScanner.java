package view.scanner;

import java.util.Scanner;

public class EducationScanner {
    private final Scanner educationScanner=new Scanner(System.in);

    public String[] getEducationInformation() {
        String[] result={"Terminate Loop"};

        System.out.println("학력 정보를 입력하세요 (종료는 q)");
        System.out.println("졸업년도 학교명 전공 졸업여부");
        String scanLine=educationScanner.nextLine();
        if(scanLine.equals("q")) {
            return result;
        }
        return scanLine.split(" ");
    }
}
