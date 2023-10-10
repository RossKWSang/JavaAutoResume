package controller;

import model.CareerDTO;
import model.EducationDTO;
import model.PersonalDTO;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

public class ResumeController {
    private int rowIndex = 0;
    private String filename = "resume.xlsx";
    private String photoFileName;
    private Sheet currentSheet;
    private XSSFWorkbook workbook;
    private int NEW_WIDTH = (int) (35 * 2.83465);
    private int NEW_HEIGHT = (int) (45 * 2.83465);


    public ResumeController() {
    }

    public ResumeController(String photoFileName, XSSFWorkbook workbook) {
        this.photoFileName = photoFileName;
        this.workbook = workbook;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    } //

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

    public void createMemberInformationSheet() { //
        this.currentSheet = workbook.createSheet("회원 정보");
    }

    public void createSelfIntroductionSheet() {
        this.currentSheet = workbook.createSheet("자기소개서");
    } //

    public Row createRowInCurrentSheet() {
        return this.currentSheet.createRow(rowIndex++);
    }
    public void generatePersonalHeader() { //
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

    public void generateCareerHeader() { //
        Row headerRow = this.createRowInCurrentSheet();
        headerRow.createCell(0).setCellValue("근무기간");
        headerRow.createCell(1).setCellValue("근무처");
        headerRow.createCell(2).setCellValue("담당업무");
        headerRow.createCell(3).setCellValue("근속연수");
    }

    public void resizeResumeSheet() { //
        for (int i=0; i<6; i++) {
            this.currentSheet.autoSizeColumn(i);
        }
    }

    private BufferedImage resizeBufferedImage(BufferedImage originalImage) {
        Image resizedImage = originalImage.getScaledInstance(this.NEW_WIDTH, this.NEW_HEIGHT, Image.SCALE_SMOOTH);
        BufferedImage resizedBufferedImage = new BufferedImage(this.NEW_WIDTH, this.NEW_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = resizedBufferedImage.createGraphics();
        g2d.drawImage(resizedImage, 0, 0, null);
        g2d.dispose();
        return resizedBufferedImage;
    }

    private byte[] convertImageToByteArray(BufferedImage resizedBufferedImage) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(resizedBufferedImage, "png", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            System.out.println("이미지를 바이트 배열로 변환하는 중 오류가 발행했습니다.");
            e.printStackTrace();
            return new byte[0];
        }
    }

    private int addPictureWorkbook(byte[] imageBytes) {
        return workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);
    }

    private void insertImage(int imageIndex) {
        XSSFDrawing drawing = (XSSFDrawing) this.currentSheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, 0, 1, 1, 2);
        drawing.createPicture(anchor, imageIndex);
    }

    private void initiateImageInsertion(Row row) throws IOException {
        row.setHeightInPoints((float) (this.NEW_HEIGHT * 72) / 96);
        int columnWidth = (int) Math.floor(((float) this.NEW_WIDTH / (float) 8) * 256);
        this.currentSheet.setColumnWidth(0, columnWidth);

        String defaultImage = this.photoFileName;
        FileInputStream fileInputStream = new FileInputStream(defaultImage);
        BufferedImage originalImage = ImageIO.read(fileInputStream);

        insertImage(addPictureWorkbook(convertImageToByteArray(resizeBufferedImage(originalImage))));
    }

    public void insertPersonalInformation(PersonalDTO personalDTO) throws IOException { //
        Row personalInformationRow = this.createRowInCurrentSheet();
        this.initiateImageInsertion(personalInformationRow);
        personalInformationRow.createCell(1).setCellValue(personalDTO.getPersonName());
        personalInformationRow.createCell(2).setCellValue(personalDTO.getEmail());
        personalInformationRow.createCell(3).setCellValue(personalDTO.getAddress());
        personalInformationRow.createCell(4).setCellValue(personalDTO.getPhoneNumber());
        personalInformationRow.createCell(5).setCellValue(personalDTO.getBirthDate());
    }

    public void insertEducationInformation(List<EducationDTO> educationDTOArray) throws IOException { //
        for (EducationDTO educationDTO : educationDTOArray) {
            Row educationRow = this.createRowInCurrentSheet();
            educationRow.createCell(0).setCellValue(educationDTO.getGraduationDate());
            educationRow.createCell(1).setCellValue(educationDTO.getSchoolName());
            educationRow.createCell(2).setCellValue(educationDTO.getMajor());
            educationRow.createCell(3).setCellValue(educationDTO.getStatus());
        }
    }

    public void insertCareerInformation(List<CareerDTO> careerDTOArray) throws IOException { //
        for (CareerDTO careerDTO : careerDTOArray) {
            Row educationRow = this.createRowInCurrentSheet();
            educationRow.createCell(0).setCellValue(careerDTO.getWorkPeriod());
            educationRow.createCell(1).setCellValue(careerDTO.getWorkPlace());
            educationRow.createCell(2).setCellValue(careerDTO.getPosition());
            educationRow.createCell(3).setCellValue(careerDTO.getWorkYear());
        }
    }

    private XSSFCellStyle getWrapCellStyle() {
        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
        style.setWrapText(true);
        return style;
    }

    public void insertSelfIntroduction(String selfIntroduction) throws IOException { //
        Row educationRow = this.createRowInCurrentSheet();
        Cell selfIntroductionCell = educationRow.createCell(0);
        selfIntroductionCell.setCellValue(new XSSFRichTextString(selfIntroduction));
        selfIntroductionCell.setCellStyle(getWrapCellStyle());
        this.currentSheet.autoSizeColumn(0);
    }

    public boolean generateExcelResume() { //
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(filename));
            workbook.write(outputStream);
            workbook.close();
            return true;
        } catch (IOException e) {
            System.out.println("엑셀 파일 저장 중 오류가 발행했습니다.");
            e.printStackTrace();
            return false;
        }
    }
}
