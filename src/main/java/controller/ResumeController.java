package controller;

import model.CareerDTO;
import model.EducationDTO;
import model.PersonalDTO;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

public class ResumeController {
    private int rowIndex = 0;
    private String filename = "resume.xlsx";
    private Sheet currentSheet;
    private final XSSFWorkbook workbook=new XSSFWorkbook();
    private final XSSFCreationHelper helper = workbook.getCreationHelper();


    public ResumeController() {
    }

    public ResumeController(String filename) {
        this.filename = filename;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Sheet getCurrentSheet() {
        return currentSheet;
    }

    public void setCurrentSheet(Sheet currentSheet) {
        this.currentSheet = currentSheet;
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public void createMemberInformationSheet() {
        this.currentSheet = workbook.createSheet("회원 정보");
        /* this.currentSheet.setColumnWidth(0, 6000);
        this.currentSheet.setColumnWidth(1, 3000);
        this.currentSheet.setColumnWidth(2, 3000);
        this.currentSheet.setColumnWidth(3, 3000);
        this.currentSheet.setColumnWidth(4, 3000);
        this.currentSheet.setColumnWidth(5, 3000); */
    }

    public void createSelfIntroductionSheet() {
        this.currentSheet = workbook.createSheet("자기소개서");
    }

    public Row createRowInCurrentSheet() {
        return this.currentSheet.createRow(rowIndex++);
    }
    public void generatePersonalHeader() {
        Row headerRow = this.createRowInCurrentSheet();
        headerRow.createCell(0).setCellValue("사진");
        headerRow.createCell(1).setCellValue("이름");
        headerRow.createCell(2).setCellValue("이메일");
        headerRow.createCell(3).setCellValue("주소");
        headerRow.createCell(4).setCellValue("전화번호");
        headerRow.createCell(5).setCellValue("생년월일");
    }

    public void generateEducationHeader() {
        Row headerRow = this.createRowInCurrentSheet();
        headerRow.createCell(0).setCellValue("졸엽년도");
        headerRow.createCell(1).setCellValue("학교명");
        headerRow.createCell(2).setCellValue("전공");
        headerRow.createCell(3).setCellValue("졸업여부");
    }

    public void generateCareerHeader() {
        Row headerRow = this.createRowInCurrentSheet();
        headerRow.createCell(0).setCellValue("근무기간");
        headerRow.createCell(1).setCellValue("근무처");
        headerRow.createCell(2).setCellValue("담당업무");
        headerRow.createCell(3).setCellValue("근속연수");
    }

    public void insertDefaultImage() throws IOException {
        String defaultImage = "default_profile_image.png";
        FileInputStream fileInputStream = new FileInputStream(defaultImage);
        byte[] bytes = IOUtils.toByteArray(fileInputStream);
        int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        fileInputStream.close();
        Drawing drawing = this.currentSheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = helper.createClientAnchor();
        anchor.setCol1(0);
        anchor.setRow1(1);
        Picture pict = drawing.createPicture(anchor, pictureIdx);
        pict.resize(0.3);
    }

    public void insertPersonalInformation(PersonalDTO personalDTO) throws IOException {
        Row personalInformationRow = this.createRowInCurrentSheet();
        this.insertDefaultImage();
        personalInformationRow.createCell(1).setCellValue(personalDTO.getPersonName());
        personalInformationRow.createCell(2).setCellValue(personalDTO.getEmail());
        personalInformationRow.createCell(3).setCellValue(personalDTO.getAddress());
        personalInformationRow.createCell(4).setCellValue(personalDTO.getPhoneNumber());
        personalInformationRow.createCell(5).setCellValue(personalDTO.getBirthDate());
    }

    public void insertEducationInformation(List<EducationDTO> educationDTOArray) throws IOException {
        for (EducationDTO educationDTO : educationDTOArray) {
            Row educationRow = this.createRowInCurrentSheet();
            educationRow.createCell(0).setCellValue(educationDTO.getGraduationDate());
            educationRow.createCell(1).setCellValue(educationDTO.getSchoolName());
            educationRow.createCell(2).setCellValue(educationDTO.getMajor());
            educationRow.createCell(3).setCellValue(educationDTO.getStatus());
        }
    }

    public void insertCareerInformation(List<CareerDTO> careerDTOArray) throws IOException {
        for (CareerDTO careerDTO : careerDTOArray) {
            Row educationRow = this.createRowInCurrentSheet();
            educationRow.createCell(0).setCellValue(careerDTO.getWorkPeriod());
            educationRow.createCell(1).setCellValue(careerDTO.getWorkPlace());
            educationRow.createCell(2).setCellValue(careerDTO.getPosition());
            educationRow.createCell(3).setCellValue(careerDTO.getWorkYear());
        }
    }

    public void generateExcelResume() {
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(filename));
            workbook.write(outputStream);
            workbook.close();
            System.out.println("엑셀 파일이 저장되었습니다." + filename);
        } catch (IOException e) {
            System.out.println("엑셀 파일 저장 중 오류가 발행했습니다.");
            e.printStackTrace();
        }
    }
}
