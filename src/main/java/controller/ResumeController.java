package controller;

import model.CareerDTO;
import model.EducationDTO;
import model.PersonalDTO;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResumeController {
    private int rowIndex = 0;
    private String filename = "resume.xlsx";
    private Sheet currentSheet;
    private final XSSFWorkbook workbook=new XSSFWorkbook();

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
    }

    public Row createRowInCurrentSheet() {
        return this.currentSheet.createRow(rowIndex++);
    }
    public void generateHeader() {
        Row headerRow = this.createRowInCurrentSheet();
        headerRow.createCell(0).setCellValue("사진");
        headerRow.createCell(1).setCellValue("이름");
        headerRow.createCell(2).setCellValue("이메일");
        headerRow.createCell(3).setCellValue("주소");
        headerRow.createCell(4).setCellValue("전화번호");
        headerRow.createCell(5).setCellValue("생년월일");
    }

    public void testResumeGeneration() {
        try {
            createMemberInformationSheet();
            generateHeader();
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
