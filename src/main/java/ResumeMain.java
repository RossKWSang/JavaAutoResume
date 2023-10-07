import view.ResumeView;

import java.io.IOException;

public class ResumeMain {
    public static void main(String[] args) throws IOException {
        ResumeView resumeView = new ResumeView();
        resumeView.generateExcelResume();
    }
}
