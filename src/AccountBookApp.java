import DAO.UserDao;
import DTO.UserDto;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountBookApp extends JFrame{
    private JPanel mainPanel;
    private JLabel appTitleLabel;
    private JPanel loginPanel;
    private JTextField idTextField;
    private JTextField passwordTextField;
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
                SignUpDialog signUpDialog = new SignUpDialog();
                signUpDialog.setVisible(true);
            }
        });

        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String id = idTextField.getText();
                String password = passwordTextField.getText();
                if(!id.isEmpty() && !password.isEmpty()){
                    UserDao  userDao = new UserDao();
                    if(userDao.login(id, password)){
                        UserDto userDto = new UserDto();
                        setVisible(false);
                        new AccountBook(userDto).setVisible(true);
                    }
                }
            }
        });
    }
}
