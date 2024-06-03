import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        String address = "http://www.codechobo.com/post/notice/list";
        String line= "";
        BufferedReader in = null;
        URL url = null;

        try {
            url = new URL(address);
            in = new BufferedReader(new InputStreamReader(url.openStream()));

            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}