import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        String address = "https://m.daum.net";
        URLConnection con = null;
        String imgAdd = "https://img1.daumcdn.net/thumb/S192x120ht.u/?fname=https%3A%2F%2Ft1.daumcdn.net%2Fnews%2F202406%2F03%2Finews24%2F20240603114955087fevu.jpg&scode=media2";
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        URL url = null;


        try {
            url = new URL(imgAdd);
            con = url.openConnection();

            bis = new BufferedInputStream(con.getInputStream());
            bos = new BufferedOutputStream(new FileOutputStream("./image.jpg"));

            int data = 0;
            while ((data = bis.read()) != -1) {
                bos.write(data);
            }
            System.out.println("이미지전송완");

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}