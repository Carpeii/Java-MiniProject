import javax.swing.*;

public class AccountBook extends JFrame {
    private JPanel mainPanel;

    public AccountBook() {
        setContentPane(mainPanel);
        setTitle("가계부 입니다");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(12,10,560,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
