import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        String urlStr = "http://www.codechobo.com:80/main";

        try {
            URL url = new URL(urlStr);
            System.out.println(url.getProtocol());
            System.out.println(url.getHost());
            System.out.println(url.getPort());
        }catch (MalformedURLException e){}
    }
}