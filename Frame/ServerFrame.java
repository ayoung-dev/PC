 //채팅 서버
package pc;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

@SuppressWarnings({ "serial", "unused" })
//서버 프레임 생성
public class ServerFrame extends JFrame {

	JTextArea textArea; //멤버 참조변수
	JTextField tfMsg;
	JButton btnSend, btnExit;
	ServerSocket serverSocket;
	Socket socket;
	BufferedReader in;
	OutputStream out;
	DataInputStream dis;
	DataOutputStream dos;
	
	int port;
	String clientname;
	String servername;
	
	//portnum 파라미터로 받기
	public ServerFrame(String port) {
		
		this.port = Integer.parseInt(port);
		
		//제목 생성
		setTitle("Server");
		setBounds(450, 50, 500, 350);

		textArea = new JTextArea();		
		textArea.setEditable(false); //쓰기 금지

		JScrollPane scrollPane = new JScrollPane(textArea);
		add(scrollPane,BorderLayout.CENTER);

		//패널 생성
		JPanel msgPanel = new JPanel();
		JPanel msgPanel_btn = new JPanel();
		msgPanel.setLayout(new BorderLayout());

		tfMsg = new JTextField();
		
		//보내기, 종료 버튼 생성
		btnSend = new JButton("send");
		btnExit	= new JButton("exit");
		
		//패널에 버튼 올리기
		msgPanel_btn.add(btnSend, BorderLayout.WEST);
		msgPanel_btn.add(btnExit, BorderLayout.EAST);
		
		msgPanel.add(tfMsg, BorderLayout.CENTER);
		msgPanel.add(msgPanel_btn, BorderLayout.EAST);
		
		add(msgPanel,BorderLayout.SOUTH);
		
		

		//send 버튼 클릭에 반응하는 리스너 추가
		btnSend.addActionListener(new ActionListener() {			

			@Override
			public void actionPerformed(ActionEvent e) {
				//sendmessage 함수 실행
				sendMessage();
			}

		});
		
		//exit 버튼 클릭에 반응하는 리스너 추가
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//종료 전 한 번 더 물어보기
				int exitOption = JOptionPane.showConfirmDialog(null, "접속을 끊으시겠습니까?", "접속 종료", JOptionPane.YES_NO_OPTION);						
				if (exitOption == JOptionPane.YES_OPTION) {//yes버튼 클릭시 종료
					try {
						//dis, dos, socket 닫아서 연결 끊기
						dis.close();
						dos.close();
						socket.close();
					} catch (Exception e1) {
						textArea.append("접속 종료에 실패했습니다.\n");
					}
				}
				else if ((exitOption == JOptionPane.NO_OPTION) || (exitOption == JOptionPane.CLOSED_OPTION)) //no버튼 클릭하거나 창을 닫을 경우 return
					return; 
				//창 닫기
				dispose();
				}
		});

		//엔터키 눌렀을 때 반응하기
		tfMsg.addKeyListener(new KeyAdapter() {

			//키보드에서 키 하나를 눌렀을때 자동으로 실행되는 메소드..: 콜백 메소드
			@Override
			public void keyPressed(KeyEvent e) {				
				super.keyPressed(e);

			//입력받은 키가 엔터인지 알아내기, KeyEvent 객체가 키에대한 정보 갖고있음
			int keyCode = e.getKeyCode();
			switch(keyCode) {
			case KeyEvent.VK_ENTER:
				sendMessage();
				break;
			}

		}

	});		
		setVisible(true);
		tfMsg.requestFocus();
		
		//서버와 연결하는 네트워크 작업 : 클라이언트 스레드 객체 생성 및 실행
		ServerThread serverThread = new ServerThread();
		serverThread.setDaemon(true); //메인 끝나면 같이 종료
		serverThread.start();

		//클라이언트 프레임에 window(창) 관련 리스너 추가
		addWindowListener(new WindowAdapter() {			
			@Override 
			public void windowClosing(WindowEvent e) {				
				super.windowClosing(e);
				try {
					if(dos != null) dos.close();
					if(dis != null) dis.close();
					if(socket != null) socket.close();
					if(serverSocket != null) serverSocket.close();
				} catch (IOException e1) {		
					textArea.append("오류가 발생했습니다.\n");
				}
			}			
		});
	}
	
		//서버스레드
		class ServerThread extends Thread {
			public void run() {			
				try { 
					//닉네임 입력 받기
					//servername = JOptionPane.showInputDialog("닉네임을 입력해주세요.\n", JOptionPane.INFORMATION_MESSAGE);
					servername = "Admin";
					//입력받은 portnum으로 socket 생성
					serverSocket = new ServerSocket(port); 
					textArea.append("서버소켓이 준비됐습니다.\n");
					textArea.append("클라이언트의 접속을 기다립니다.\n");	
					
					//클라이언트가 접속할때까지 커서(스레드)가 대기
					socket = serverSocket.accept();
					
					//통신을 위한 스트림 생성
					dis = new DataInputStream(socket.getInputStream());
					dos = new DataOutputStream(socket.getOutputStream());

					//입력받은 닉네임 write
					dos.writeUTF(servername);
					//client에서 입력받는 닉네임 read
					clientname = dis.readUTF();

					//ip주소와 클라이언트 닉네임 화면창에 출력해주기
					textArea.append( "ip 주소 : " + socket.getInetAddress().getHostAddress() + "\n 닉네임 : " + clientname + "님이 접속하셨습니다.\n");


					while(true) {//상대방이 보내온 데이터를 읽기
						
						//상대방이 보낼때까지 대기
						String msg = dis.readUTF();
						//TextArea에 표시
						textArea.append(" [" + clientname + "] : " + msg + "\n");
						textArea.setCaretPosition(textArea.getText().length());
					}					
				} catch (IOException e) {
					textArea.append("클라이언트가 나갔습니다.\n");
				}
			}
		}
		
		//메시지 전송하는 기능 메소드
		void sendMessage() {	
			String msg = tfMsg.getText(); //TextField에 써있는 글씨를 얻어오기
			tfMsg.setText(""); //입력 후 빈칸으로
			
			//TextArea에 표시
			textArea.append(" [" + servername + "] : " + msg + "\n");
			//스크롤 따라가게
			textArea.setCaretPosition(textArea.getText().length()); 
			
			//Client에 메시지 전송하기
			Thread t = new Thread() {
				@Override
				public void run() {
					try {
						dos.writeUTF(msg);
						dos.flush();
					} catch (IOException e) {
						textArea.append("오류가 발생했습니다.\n");
					}
				}
			};		
			t.start();
		}	
	
}
