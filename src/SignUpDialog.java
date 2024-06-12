import DAO.UserDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignUpDialog extends JDialog {
    private JPanel mailPanel;
    private JTextField idTextField;
    private JPasswordField passwordField;
    private JButton idTestButton;
    private JButton signUpButton;
    private JButton quitButton;
    private JLabel idTestResultLabel;
    private JPanel resultPanel;
    private JLabel resultLabel;
    private JTextField nameTextField;

    private UserDao userDao;

    public SignUpDialog(){
        setContentPane(mailPanel);
        setTitle("회원가입");
        setBounds(12,10,560,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        idTestButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

        signUpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!idTextField.getText().isEmpty() && !passwordField.getText().isEmpty() ){
                    userDao = new UserDao();
                    boolean isSuccess = false;
                    isSuccess = userDao.signUp(idTextField.getText(), passwordField.getText(), nameTextField.getText());

                    if(isSuccess){
                        resultLabel.setText("회원가입 성공");

                        idTextField.setText("");
                        passwordField.setText("");
                        nameTextField.setText("");
                    } else{
                        resultLabel.setText("회원가입 실패");
                    }
                }

            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
