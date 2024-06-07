import javax.swing.*;

public class SignUpDialog extends JDialog {
    private JPanel mailPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton idTestButton;
    private JButton signUpButton;
    private JButton quitButton;
    private JLabel idTestResultLabel;
    private JPanel resultPanel;
    private JLabel resultLabel;

    public SignUpDialog(){
        setContentPane(mailPanel);
        setTitle("회원가입");
        setBounds(12,10,560,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
