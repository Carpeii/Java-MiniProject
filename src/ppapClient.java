import java.io.*;
import java.net.Socket;

public class ppapClient {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedWriter bw = null;

        try {
            BufferedReader inputBufferdReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("단수 : ");
            String strDan = inputBufferdReader.readLine();

            socket = new Socket("localhost", 7777);
            System.out.println("서버연결됌");
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            bw.write( strDan + System.lineSeparator() );
            bw.flush();

            System.out.println( br.readLine().replaceAll( ":", "\n" ) );

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
