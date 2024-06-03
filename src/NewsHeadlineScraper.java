import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class NewsHeadlineScraper {
    private BufferedOutputStream bos;
    private BufferedInputStream bis;
    private URL url;
    private URLConnection urlConnection;

    private ArrayList<String> newsUrl;
    String strUrl;

    public NewsHeadlineScraper(String strUrl) {
        Init(strUrl);
    }
    private void Init(String strUrl) {
        this.strUrl = strUrl;
    }

    public ArrayList<String> getHeadLine() {
        BufferedReader br = null;
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> newUrl = new ArrayList<String>();
        newsUrl = newUrl;
        boolean flag = false;
        try {
            url = new URL(this.strUrl);
            urlConnection = url.openConnection();
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";

            while ((line = br.readLine()) != null) {
               if(line.contains("class=\"link_txt\" data-tiara-layer=\"article_main\"")) {
                   flag = true;
                   continue;
               }
               if(line.contains("</a>")){
                   flag = false;
               }
               if(flag) {
                   result.add(line.trim());
               }


               if(line.contains("https://v.daum.net")) {
                   System.out.println(line);
                   int start = line.indexOf("https://");
                   int end = line.indexOf("\\\\\\\"", start);
//                   System.out.println(link);
               }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void refreshUrl(){
    }

    public ArrayList<String> getNewsUrl() {
        return newsUrl;
    }
}
