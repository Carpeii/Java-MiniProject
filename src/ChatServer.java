import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class ChatServer {
    private HashMap<String, OutputStream> clients;

    public static void main(String[] args) {
        new ChatServer().start();
    }
    public ChatServer(){
        clients = new HashMap<String, OutputStream>();
    }
    public void start() {
        ServerSocket serverSocket = null;
        Socket socket = null;

        try {
            serverSocket = new ServerSocket(7777);
            System.out.println("서버시작");
            //항상 대기중인 서버
            while (true) {
                //클라에서 접속
                socket = serverSocket.accept();
                System.out.println(socket.getInetAddress() + " : " + socket.getPort() + "에서 접속" );

                //접속 유지용 스레드
                ServerReceiver thread = new ServerReceiver(socket);
                thread.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendToAll(String msg) {
        Iterator<String> it = clients.keySet().iterator();

        while (it.hasNext()) {
            try {
                DataOutputStream out = new DataOutputStream(clients.get(it.next()));
                out.writeUTF(msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class ServerReceiver extends Thread {
        private Socket socket;
        private DataInputStream in;
        private DataOutputStream out;

        public ServerReceiver(Socket socket) {
            this.socket = socket;

            try {
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void run() {
            String name = "";
            try {
                name = in.readUTF();
                System.out.println(name);
                sendToAll("#" + name + "님이 들어옴");
                clients.put(name, out);

                System.out.println("서버접속자수 : "+ clients.size());

                while (in!=null){
                    sendToAll(in.readUTF());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                sendToAll("#" + name + "님이 나갔음");
                clients.remove(name);
                System.out.println(socket.getInetAddress() + " : " + socket.getPort() + "접속종료");
            }
        }
    }
}
