import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerEx02 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ServerSocket serverSocket = null;
        Socket socket = null;

        BufferedReader br = null;
        BufferedWriter bw = null;
        while (true) {
            try {
                serverSocket = new ServerSocket(7777);
                System.out.println("서버가 준비되었습니다");

                socket = serverSocket.accept();
                System.out.println("클라이언트가 연결되었습니다.");

                br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
                bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));

                // 2단
                String strDan = br.readLine().replaceAll("단", "");
                System.out.println("구구단 생성 : " + strDan);

                StringBuilder sbResult = new StringBuilder();
                for (int i = 1; i <= 9; i++) {
                    sbResult.append(strDan + " X " + i + " = " + (Integer.parseInt(strDan) * i) + ":");
                }

                bw.write(sbResult.toString() + System.lineSeparator());
                bw.flush();

                System.out.println("전송 완료");

            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("[에러] " + e.getMessage());
            } finally {
                if (br != null) try {
                    br.close();
                } catch (IOException e) {
                }
                if (bw != null) try {
                    bw.close();
                } catch (IOException e) {
                }

                if (socket != null) try {
                    socket.close();
                } catch (IOException e) {
                }
                if (serverSocket != null) try {
                    serverSocket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
