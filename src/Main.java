import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        String url = "http://www.codechobo.com:80/main";

        //프로토콜 : http
        //호스트명 : www.codechobo.com
        //포트명 : 80
        //StringTokenizer(url, ":/") 코드는 문자열에서 콜론(:) 또는 슬래시(/)가 나올때마다 해당 부분을 기준으로 문자열을 나눕니다. 이렇게 나눈 문자열들을 토큰이라고 부릅니다.

        StringTokenizer st = new StringTokenizer(url, ":/");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }
}