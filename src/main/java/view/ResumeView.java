package view;

import model.CareerDTO;
import model.EducationDTO;
import model.PersonalDTO;

import view.scanner.CareerScanner;
import view.scanner.EducationScanner;
import view.scanner.PersonalScanner;
import view.scanner.IntroductionScanner;

import controller.ResumeController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResumeView {
    public PersonalDTO getPersonalDTO() {
        PersonalScanner personalScanner = new PersonalScanner();
        return new PersonalDTO(
                personalScanner.getPersonName(),
                personalScanner.getEmail(),
                personalScanner.getAddress(),
                personalScanner.getPhoneNumber(),
                personalScanner.getBirthDate()
        );
    }

    public List<CareerDTO> getListOfCareerDTO() {
        List<CareerDTO> CareerList = new ArrayList<CareerDTO>();
        String[] tempScan;
        while (true) {
            CareerScanner careerScanner = new CareerScanner();
            tempScan = careerScanner.getCareerInformation();
            if (tempScan[0].equals("Terminate Loop")) {
                break;
            }
            CareerList.add(new CareerDTO(tempScan[0], tempScan[1], tempScan[2], tempScan[3]));
        }
        return CareerList;
    }

    public List<EducationDTO> getListOfEducationDTO() {
        List<EducationDTO> EducationList = new ArrayList<EducationDTO>();
        String[] tempScan;
        while (true) {
            EducationScanner educationScanner = new EducationScanner();
            tempScan = educationScanner.getEducationInformation();
            if (tempScan[0].equals("Terminate Loop")) {
                break;
            }
            EducationList.add(new EducationDTO(tempScan[0], tempScan[1], tempScan[2], tempScan[3]));
        }
        return EducationList;
    }

    public String getSelfIntroduction() {
        System.out.println("자기소개서를 입력하세요.");
        IntroductionScanner introductionScanner = new IntroductionScanner();
        return introductionScanner.getSelfIntroduction();
    }

    public String getPhotoFileName() {
        PersonalScanner personalScanner = new PersonalScanner();
        return personalScanner.getPhotoFileName();
    }

    public void generateExcelResume() throws IOException {
        ResumeController resumeController = new ResumeController(this.getPhotoFileName());
        resumeController.createMemberInformationSheet();
        resumeController.generatePersonalHeader();
        resumeController.insertPersonalInformation(this.getPersonalDTO());
        resumeController.generateEducationHeader();
        resumeController.insertEducationInformation(this.getListOfEducationDTO());
        resumeController.generateCareerHeader();;
        resumeController.insertCareerInformation(this.getListOfCareerDTO());
        resumeController.resizeResumeSheet();
        resumeController.setRowIndex(0);
        resumeController.createSelfIntroductionSheet();
        resumeController.insertSelfIntroduction(this.getSelfIntroduction());
        if (resumeController.generateExcelResume()) {
            System.out.println("엑셀 파일이 저장되었습니다. >> " + resumeController.getFilename());
        }
    }
}