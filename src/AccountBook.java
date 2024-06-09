import DTO.UserDto;

import javax.swing.*;

public class AccountBook extends JFrame {

    private JPanel mainPanel;
    private JScrollPane resultScrollPane;
    private JPanel utilPanel;
    private JPanel insertPanel;
    private JPanel userInfoPanel;
    private JPanel searhPanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton refreshButton;
    private JTable table1;
    private JTextField moneyTextField;
    private JTextField descriptionTextField;
    private JButton insertButton;
    private JRadioButton expenseRadioButton;
    private JButton deleteButton;
    private JRadioButton incomeRadioButton;
    private JTextField monthTextField;
    private JTextField yearTextField;
    private JTextField dayTextField;

    public AccountBook() {
        init();
    }

    public AccountBook(UserDto userDto) {
        init();
    }

    public void init(){
        setContentPane(mainPanel);
        setTitle("가계부");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(12, 10, 1000, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

//        for(int year = 2020; year < 2040; year++){}
//        yearComboBox.addItem();
    }

    public static void main(String[] args) {
        new AccountBook();
    }
}
