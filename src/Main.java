import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getByName("www.daum.net");
            System.out.println(inetAddress.getHostName());
            System.out.println(inetAddress.getHostAddress());

            InetAddress[] inetAddresses = InetAddress.getAllByName("www.naver.net");
            for (InetAddress inetAddress1 : inetAddresses) {
                System.out.println(inetAddress1.getHostAddress());
            }
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
    }
}