import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        String address = "https://news.daum.net/?nil_profile=mini&nil_src=news";

        BufferedReader br = null;
        URL url = null;

        try {
            url = new URL(address);
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line  = "";
            boolean flag = false;
            while ((line = br.readLine()) != null) {
                if(line.contains("<ul class=\"list_newsissue\">")){
                    flag = true;
                    continue;
                }
                if(line.contains("</ul>")){
                    flag = false;
                }
                if(flag){
                    System.out.println(line);
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}