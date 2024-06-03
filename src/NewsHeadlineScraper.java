import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class NewsHeadlineScraper {
    private BufferedOutputStream bos;
    private BufferedInputStream bis;
    private URL url;
    private URLConnection urlConnection;

    private Document doc;
    String strUrl;

    public NewsHeadlineScraper(String strUrl) {
        Init(strUrl);
    }
    private void Init(String strUrl) {
        this.strUrl = strUrl;
    }

    public ArrayList<String> getHeadLine() {
        try {
            doc = Jsoup.connect(strUrl).get();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Elements elements = doc.getElementsByClass("link_txt");
        Elements headlineElements = doc.getElementsByClass("tit_g");
        System.out.println(headlineElements.get(0).text());

        System.out.println(elements.get(0).select("a").attr("href"));

        return null;
    }

    public void refreshUrl(){
    }

}
