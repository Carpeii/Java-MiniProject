import org.jsoup.nodes.Element;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MovieRankingApp extends JFrame {
    private JButton searchButton;
    private JPanel mainPanel;
    private JTextField searchTextField;
    private JTable resultTable;

    private String[] columnNames = {"영화 제목", "순위", "괜객수"};
    private DefaultTableModel tableModel;

    public MovieRankingApp() {
        setContentPane(mainPanel);
        setTitle("뉴스 보기");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(12,10,560,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);


        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MovieRangkingScraper movieRangkingScraper = new MovieRangkingScraper("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=f5eef3421c602c6cb7ea224104795888&targetDt=20240603");
                tableModel = new DefaultTableModel();
                resultTable.setModel(tableModel);
                for (String columnName : columnNames) {
                    tableModel.addColumn(columnName);
                }
                tableModel.addRow(columnNames);
                for (int i = 0; i < movieRangkingScraper.getTitles().size(); i++) {
                    tableModel.addRow(new Object[]{
                            movieRangkingScraper.getTitles().get(i).text(),
                            movieRangkingScraper.getRanks().get(i).text(),
                            movieRangkingScraper.getAudiCnt().get(i).text()
                    });
                }
            }
        });
    }

    public static void main(String[] args) {
        new MovieRankingApp();
    }
}
