import javax.swing.*;

public class AccountBook extends JFrame {
    private JPanel mainPanel;

    private String id;
    private String name;

    public AccountBook(String id, String name) {
        setContentPane(mainPanel);
        setTitle("가계부 입니다");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(12,10,560,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        this.id = id;
        this.name = name;

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
