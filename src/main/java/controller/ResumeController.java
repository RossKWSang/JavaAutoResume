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
    private final XSSFWorkbook workbook=new XSSFWorkbook();
    private final XSSFCreationHelper helper = workbook.getCreationHelper();


    public ResumeController() {
    }

    public ResumeController(String photoFileName) {
        this.photoFileName = photoFileName;
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

    public void insertDefaultImage(Row row) throws IOException {
        String defaultImage = this.photoFileName;
        FileInputStream fileInputStream = new FileInputStream(defaultImage);
        BufferedImage originalImage = ImageIO.read(fileInputStream);

        // 증명사진 크기로 이미지를 조절합니다. (가로 35mm, 세로 45mm)
        int newWidth = (int) (35 * 2.83465); // mm 단위를 픽셀 단위로 변환합니다 (1mm = 2.83465px).
        int newHeight = (int) (45 * 2.83465); // mm 단위를 픽셀 단위로 변환합니다 (1mm = 2.83465px).
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedBufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = resizedBufferedImage.createGraphics();
        g2d.drawImage(resizedImage, 0, 0, null);
        g2d.dispose();

        // 조절된 이미지를 바이트 배열로 변환합니다.
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedBufferedImage, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        int imageIndex = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);

        // Drawing 객체를 생성하고 이미지를 삽입합니다.
        XSSFDrawing drawing = (XSSFDrawing) this.currentSheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, 0, 1, 1, 2);
        drawing.createPicture(anchor, imageIndex);

        // 이미지가 삽입된 행의 높이와 열의 너비를 조정합니다.
        // 96은 화면의 DPI(Dots Per Inch, 인치당 도트 수)
        // Excel에서 셀의 높이는 포인트(point) 단위로 표시(1 포인트는 1/72 인치입니다)
        row.setHeightInPoints(newHeight*72/96); // 픽셀을 point로변경
        // 8이란 값은, 엑셀에서 사용되는 기본 문자 폭의 값
        // 엑셀에서는 한 개의 문자가 차지하는 너비를 1/256 단위로 계산
        int columnWidth = (int) Math.floor(((float) newWidth / (float) 8) * 256);
        this.currentSheet.setColumnWidth(0, columnWidth);
    }

    public void insertPersonalInformation(PersonalDTO personalDTO) throws IOException {
        Row personalInformationRow = this.createRowInCurrentSheet();
        this.insertDefaultImage(personalInformationRow);
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

    private XSSFCellStyle getWrapCellStyle() {
        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
        style.setWrapText(true);
        return style;
    }

    public void insertSelfIntroduction(String selfIntroduction) throws IOException {
        Row educationRow = this.createRowInCurrentSheet();
        Cell selfIntroductionCell = educationRow.createCell(0);
        selfIntroductionCell.setCellStyle(getWrapCellStyle());
        selfIntroductionCell.setCellValue(new XSSFRichTextString(selfIntroduction));
    }

    public void generateExcelResume() {
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(filename));
            workbook.write(outputStream);
            workbook.close();
            // System.out.println("엑셀 파일이 저장되었습니다." + filename);
        } catch (IOException e) {
            System.out.println("엑셀 파일 저장 중 오류가 발행했습니다.");
            e.printStackTrace();
        }
    }
}
