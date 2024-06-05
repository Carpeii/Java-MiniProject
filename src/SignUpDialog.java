import javax.swing.*;

public class SignUpDialog extends JDialog {
    private JPanel mailPanel;

    public SignUpDialog(){
        setContentPane(mailPanel);
        setTitle("회원가입");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(12,10,560,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
