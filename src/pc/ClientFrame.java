//채팅 클라이언트 서버
package pc;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import javax.swing.*;


@SuppressWarnings({ "serial", "unused" })
//클라이언트 프레임 생성
public class ClientFrame extends JFrame{

	JTextArea textArea; 
	JTextField tfMsg;
	JButton btnSend, btnExit;


	Socket socket;
	BufferedReader in;
	OutputStream out;
	DataInputStream dis;
	DataOutputStream dos;	

	String ip;
	int port;
	String servername;
	String clientname;
	
	//ip주소와 portnum 파라미터로 받기
	public ClientFrame(String ip, String port) {
		this.ip = ip;
		this.port = Integer.parseInt(port);
		
		//제목 생성
		setTitle("Client");
		setBounds(450, 400, 500, 350);

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
				if (exitOption == JOptionPane.YES_OPTION) { //yes버튼 클릭시 종료
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

			//키보드에서 키 하나를 눌렀을때 자동으로 실행되는 메소드 : 콜백 메소드
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
		ClientThread clientThread = new ClientThread();
		clientThread.setDaemon(true); //메인 끝나면 같이 종료
		clientThread.start();
		
		//클라이언트 프레임에 window(창) 관련 리스너 추가
		addWindowListener(new WindowAdapter() {			
			@Override 
			public void windowClosing(WindowEvent e) {				
				super.windowClosing(e);

				try {
					if(dos != null) dos.close();
					if(dis != null) dis.close();
					if(socket != null) socket.close();
				} catch (IOException e1) {					
					textArea.append("오류가 발생했습니다.\n");
				}
			}			
		});
	}
		
		//클라이언트스레드
		class ClientThread extends Thread {
			public void run() {
				try { 
					//닉네임 입력 받기
					//clientname = JOptionPane.showInputDialog("닉네임을 입력해주세요.\n", JOptionPane.INFORMATION_MESSAGE);		
					clientname = UserGUI.name;
					//입력받은 ip주소와 portnum으로 socket 생성
					socket = new Socket("127.0.0.1", port); 
					textArea.append("서버에 접속됐습니다.\n");

					//데이터 전송을 위한 스트림 생성
					InputStream is = socket.getInputStream();
					OutputStream os = socket.getOutputStream();

					//보조스트림으로 만들어서 데이터전송 작업 편하게 
					dis = new DataInputStream(is);
					dos = new DataOutputStream(os);	

					//입력받은 닉네임 write
					dos.writeUTF(clientname);
					//server에서 입력받는 닉네임 read
					servername = dis.readUTF();

					while(true) {//상대방 메시지 받기
						String msg = dis.readUTF();
						//TextArea에 표시
						textArea.append(" [" + servername + "] : " + msg + "\n");
						textArea.setCaretPosition(textArea.getText().length());
					}

				} catch (UnknownHostException e) {
					textArea.append("서버 주소가 이상합니다.\n");
				} catch (IOException e) {
					textArea.append("서버와 연결이 끊겼습니다.\n");
					e.printStackTrace();
				}
			}
		}
		

		//메시지 전송하는 기능 함수
		void sendMessage() {	
		String msg = tfMsg.getText(); //TextField에 써있는 글씨를 얻어오기
		tfMsg.setText(""); //입력 후 빈칸으로
		
		//TextArea에 표시
		textArea.append(" [" + clientname + "] : " + msg + "\n");
		//스크롤 따라가게 하기
		textArea.setCaretPosition(textArea.getText().length()); 
		
		//Server에 메시지 전송하기
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