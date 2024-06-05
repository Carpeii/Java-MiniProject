

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClientEx02 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Socket socket = null;

        BufferedWriter bw = null;
        BufferedReader br = null;

        try {
            br = new BufferedReader( new InputStreamReader( System.in ) );
            System.out.print( "단수 > " );
            String strDan = br.readLine();

            System.out.println( "서버와 연결합니다." );

            socket = new Socket( "localhost", 7777 );
            System.out.println( "서버와 연결되었습니다." );

            bw = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream(), "utf-8" ) );
            br = new BufferedReader( new InputStreamReader( socket.getInputStream(), "utf-8" ) );
            bw.write( strDan + System.lineSeparator() );
            bw.flush();

            System.out.println( br.readLine().replaceAll( ":", "\n" ) );

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            System.out.println( "[에러] " + e.getMessage() );
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println( "[에러] " + e.getMessage() );
        } finally {
            if( bw != null ) try { bw.close(); } catch( IOException e ) {}
            if( br != null ) try { br.close(); } catch( IOException e ) {}

            if( socket != null ) try { socket.close(); } catch( IOException e ) {}
        }
    }
}
