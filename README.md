# JavaAutoResume

---

### 과제 제목: 이력 정보와 자기소개서를 입력 받아서 Excel 파일을 생성하는 프로그램

---

**요구사항**

1.Model, View, Controller 클래스를 만들어서 MVC 패턴을 구성한다. (완료)

2.사용자로부터 정보를 입력받기 위해 View 클래스에서 Scanner를 사용하여 입력 받는다. (완료)

3.입력 받은 정보를 이용하여 Model 클래스에 저장한다. (완료)

4.Controller 클래스에서 Model에 저장된 정보를 이용하여 엑셀 파일을 생성한다. (완료)

5.엑셀 파일을 생성할 때, Apache POI 라이브러리를 사용한다. (완료)

6.엑셀 파일 생성이 완료되면 View 클래스에서 "이력서가 생성되었습니다." 라는 메시지를 출력한다. (완료)

7.자기소개서의 경우 Scanner.nextLine() 메소드를 이용하여 여러 줄에 걸쳐 입력을 받을 수 있도록 하고, 엑셀 파일에 저장할 때는 "\n"을 이용하여 줄바꿈을 유지하도록 한다. (완료)

8.PNG 형식의 이미지를 엑셀 파일에 저장하기 위해, Apache POI 라이브러리 사용하여 이미지를 삽입할 수 있도록 한다. (완료)

---

**구현한 기능 목록**

- 사용자로부터 몇가지 자료를 입력받아 엑셀 포멧으로 일정한 형식의 이력서를 제작하는 프로그램
- 기본적인 사용자 정보(이름, 이메일, 주소, 전화번호, 생년월일)를 입력받은 이후 

**입력과 출력**

* 입력 자료형
  - 사진(.png)
  - 이름 (String)
  - 이메일 (String)
  - 주소 (String)
  - 전화번호 (String)

**개발도구**
- IntelliJ
- Maven 
- Java 11

**그외 상세한 내용**
- 해당 리포지토리는 Kernel360과정의 사전과제 제출을 위하여 제작되었습니다.
- 이력서에 입력되는 사진의 경우 src안의 default_profile_image.png만을 입력받습니다.
- 이력서 파일명은 resume.xlsx로만 출력되게 되어있습니다.

