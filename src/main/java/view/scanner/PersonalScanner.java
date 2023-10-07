package view.scanner;

import java.util.Scanner;

public class PersonalScanner {
    private final Scanner personalScanner = new Scanner(System.in);

    public String getPersonName() {
        System.out.print("이름을 입력하세요: ");
        return personalScanner.nextLine();
    }
    public String getEmail() {
        System.out.print("이메일을 입력하세요: ");
        return personalScanner.nextLine();
    }
    public String getAddress() {
        System.out.print("주소를 입력하세요: ");
        return personalScanner.nextLine();
    }
    public String getPhoneNumber() {
        System.out.print("전화번호를 입력하세요: ");
        return personalScanner.nextLine();
    }
    public String getBirthDate() {
        System.out.print("생년월일을 입력하세요 (예: 1990-01-01): ");
        return personalScanner.nextLine();
    }


}


/*
    private String personName;
    private String email;
    private String address;
    private String phoneNumber;
    private String birthDate;



* */