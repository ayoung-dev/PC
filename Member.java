package pc;
import java.io.Serializable;

public class Member implements Serializable{
	private String user;		 //사용자인지 관리자인지 구분하는 필드
	private String name;		 // 이름 필드
	private String phoneNum;	 // 전화번호 필드
	//private int ip;				 // 아이피 필드
	private String id;			 // 아이디 필드
	private String pw;			 // 비밀번호 필드
	private double time;		 // 사용시간 필드
	private String state;		//사용중인지 아닌지 구분하는 필드
	private String ip;
	
	//기본 생성자
	public Member(String user, String name, String phoneNum, String id, String pw, double time, String state, String ip){ 
		this.user=user;
		this.name=name;
		this.phoneNum = phoneNum;
		this.ip = ip;
		this.id = id;
		this.pw = pw;
		this.time = time;
		this.state = state;
	}
	public Member() {
		this.user = user;
		this.name=null;
		this.phoneNum = null;
		this.ip = ip;
		this.id = id;
		this.pw = pw;
		this.time = time;
		this.state = state;
	}
	
	public void setUser(String user){ 		// 유저 설정자
		this.user = user;
	}
	public void setName(String name){ 		// 이름 설정자
		this.name=name;
	}		
	public void setPhoneNum(String phoneNum){ 	// 전화번호 설정자
		this.phoneNum = phoneNum;
	}
	public void setIp(String ip) {	//ip 설정자
		this.ip = ip;
	}
	public void setId(String id) {	// 아이디 설정자
		this.id = id;
	}
	public void setPw(String pw){ 		// 비밀번호 설정자
		this.pw = pw;
	}
	public void setTime(double time) {	//이용시간 설정자
		this.time = time;
	}
	public void setState(String state) {	//삳태 설정자
		this.state = state;
	}
	public String getUser(){ 			// 유저 접근자
		return user;
	}
	public String getName(){ 			// 이름 접근자
		return name;
	}
	public String getPhoneNum(){ 		// 전화번호 접근자
		return phoneNum;
	}
	public String getIp() {	//ip 접근자
		return ip;
	}
	public String getId() {		//아이디 접근자
		return id;
	}
	public String getPw(){ 		// 비밀번호 접근자
		return pw;
	}
	public double getTime() {	//이용시간 접근자
		return time;
	}
	public String getState() {	//상태 접근자
		return state;
	}
}
