import view.ResumeView;

public class ResumeMain {
    public static void main(String[] args) {

        ResumeView resumeView = new ResumeView();

        System.out.println(resumeView.getPersonalDTO());
        System.out.println(resumeView.getListOfCareerDTO());
        System.out.println(resumeView.getListOfEducationDTO());
        System.out.println(resumeView.getSelfIntroduction());
        resumeView.generateExcelResume();
        System.out.println("Hello world");
    }
}
