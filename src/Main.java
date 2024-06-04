import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
    public static void main(String[] args) {
        StringBuilder sb = readFile("./data.json");
        JSONParser parser = new JSONParser();


        try {
            JSONArray arr = (JSONArray) parser.parse(sb.toString());
            JSONObject obj = (JSONObject) arr.get(0);
            String name = obj.get("name").toString();
//            System.out.println(name);
//            System.out.println(obj);
//            System.out.println(arr);

            for (int i = 0; i < arr.size(); i++) {
                JSONObject data = (JSONObject) arr.get(i);
//                System.out.println(data);
                System.out.println(obj.get("name"));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}