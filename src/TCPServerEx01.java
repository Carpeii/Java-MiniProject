import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerEx01 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket( 7777 );

            // 항상 처리 후 대기 상태
            while( true ) {
                try {
                    System.out.println( "서버가 준비되었습니다" );

                    socket = serverSocket.accept();
                    System.out.println( "클라이언트가 연결되었습니다." );

                } catch( IOException e ) {
                    // TODO Auto-generated catch block
                    System.out.println( "[에러] " + e.getMessage() );
                } finally {
                    if( socket != null ) try { socket.close(); } catch( IOException e ) {}
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println( "[에러] " + e.getMessage() );
        } finally {
            if( serverSocket != null ) try { serverSocket.close(); } catch( IOException e ) {}
        }
    }

}
