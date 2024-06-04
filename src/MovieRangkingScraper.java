import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieRangkingScraper {
    private String strUrl;
    private URL url = null;
    Document doc = null;

    private Elements titles;
    private Elements ranks;
    private Elements audiCnt;

    public MovieRangkingScraper(String strUrl) {
        this.strUrl = strUrl;
        init();
    }
    public void init(){
        try {
            url = new URL(strUrl);
            readFile();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFile() {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+ System.lineSeparator());
            }
            doc = Jsoup.parse(sb.toString());
            titles = doc.getElementsByTag("MovieNm");
            ranks = doc.getElementsByTag("rank");
            audiCnt = doc.getElementsByTag("audiCnt");


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Elements getTitles() {
        return titles;
    }

    public Elements getRanks() {
        return ranks;
    }

    public Elements getAudiCnt() {
        return audiCnt;
    }
}
