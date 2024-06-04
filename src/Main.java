import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

public class Main {
    public static StringBuilder readFile(String fileName) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line = null;
            while ((line = br.readLine())!= null){
                sb.append(line+System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb;
    }
    public static void main(String[] args){
        StringBuilder sb = readFile("./data.xml");

        Document doc = Jsoup.parse(sb.toString());

        Elements names = doc.getElementsByTag("book");
        Elements titles = doc.getElementsByTag("title");
        Elements authors = doc.getElementsByTag("author");
        Elements years = doc.getElementsByTag("year");
        Elements prices = doc.getElementsByTag("price");

        for (int i = 0; i < names.size(); i++) {
            Element name = names.get(i);
            Element title = titles.get(i);
            Element author = authors.get(i);
            Element year = years.get(i);
            Element price = prices.get(i);

            System.out.println(name.text());
            System.out.println(title.text());
            System.out.println(author.text());
            System.out.println(year.text());
            System.out.println(price.text());
        }
    }
}