import DTO.UserDto;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private JTable resultTable;
    private JTextField moneyTextField;
    private JTextField descriptionTextField;
    private JButton insertButton;
    private JRadioButton expenseRadioButton;
    private JButton deleteButton;
    private JRadioButton incomeRadioButton;
    private JTextField monthTextField;
    private JTextField yearTextField;
    private JTextField dayTextField;

    private UserDto userDto;
    public AccountBook(UserDto userDto) {
        this.userDto = new UserDto();
        this.userDto = userDto;

        setContentPane(mainPanel);
        setTitle("가계부");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(12, 10, 1000, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //resultTable
                resultTable.setModel(new MoneyLogTableModel(userDto.getId()));
            }
        });
    }
}
