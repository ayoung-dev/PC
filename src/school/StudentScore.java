package school;

//학생 학기별 성적 - 학생 장바구니, 수강신청 내역
public class StudentScore {
 
 //변수
 //과목명
 private String leName;
 //학수번호
 private String leNum;
 //이수구분
 private String division;
 //학점
 private int credit;
 //강의시간
 private String time;
 //교수이름
 private String prName;
 //점수
 private int scoreCredit;
 //등급
 private String grade;
 //수강학기
 private String semester;
 
 public StudentScore(String leName, String leNum, String division, int credit,
       String time, String prName, int scoreCredit, String grade, String semester)
 {
    this.leName = leName;
    this.leNum = leNum;
    this.division = division;
    this.credit = credit;
    this.time = time;
    this.prName = prName;
    this.scoreCredit = scoreCredit;
    this.grade = grade;
    this.semester = semester;
 }   
}