import org.jsoup.nodes.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class NewsApp extends JFrame {
    private JPanel mainPanel;
    private JButton searchButton;
    private JPanel newsPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;


    private NewsHeadlineScraper newsHeadlineScraper;
    private String strUrl = "https://news.daum.net/?nil_profile=mini&nil_src=news";

    public NewsApp(){
        newsHeadlineScraper = new NewsHeadlineScraper(strUrl);
        setContentPane(mainPanel);
        setTitle("뉴스 보기");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(12,10,560,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshUi();
            }
        });
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("test");
            }
        });
    }

    public void refreshUi(){
        ArrayList<String> result = newsHeadlineScraper.getHeadLine();

//        textField1.setText("");
//        textField2.setText("");
//        textField3.setText("");
//        textField4.setText("");
//        textField5.setText("");
//        textField6.setText("");
//        textField7.setText("");
//        textField8.setText("");
//        textField9.setText("");
//        textField10.setText("");
//
//        textField1.setText(result.get(0));
//        textField2.setText(result.get(1));
//        textField3.setText(result.get(2));
//        textField4.setText(result.get(3));
//        textField5.setText(result.get(4));
//        textField6.setText(result.get(5));
//        textField7.setText(result.get(6));
//        textField8.setText(result.get(7));
//        textField9.setText(result.get(8));
//        textField10.setText(result.get(9));
    }

    public static void main(String[] args) {
        new NewsApp();
    }
}
