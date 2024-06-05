import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountBookApp extends JFrame{
    private JPanel mainPanel;
    private JLabel appTitleLabel;
    private JPanel loginPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JButton loginButton;
    private JButton signUpButton;
    private JLabel idLabel;
    private JLabel passwordLabel;

    public AccountBookApp(){
        setContentPane(mainPanel);
        setTitle("가계부");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(12,10,560,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        signUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            
            }
        });

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                new AccountBook().setVisible(true);
            }
        });
    }
}
