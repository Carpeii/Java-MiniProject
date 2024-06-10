import DAO.DayLogDao;
import DAO.UserDao;
import DTO.DayLogDto;
import DTO.UserDto;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
    private JLabel userNameLabel;

    private UserDto userDto;
    public AccountBook(UserDto userDto) {
        this.userDto = userDto;

        setContentPane(mainPanel);
        setTitle("가계부");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(12, 10, 1000, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        ButtonGroup buttonGroup = new ButtonGroup();

        buttonGroup.add(expenseRadioButton);
        buttonGroup.add(incomeRadioButton);

        expenseRadioButton.setSelected(true);

        userNameLabel.setText(userDto.getName());

        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //resultTable
                resultTable.setModel(new MoneyLogTableModel(userDto.getId()));
            }
        });

        insertButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!moneyTextField.getText().isEmpty()
                        && !yearTextField.getText().isEmpty()
                        && !monthTextField.getText().isEmpty()
                        && !dayTextField.getText().isEmpty()) {
                    DayLogDao dayLogDao = new DayLogDao();
                    if(expenseRadioButton.isSelected()) {
                        //TODO 날짜 체크할수 있게끔하기
                        String date = "2024-06-04";
                        if(dayLogDao.insertExpenseDayLog(
                                userDto.getId(),
                                date,
                                moneyTextField.getText(),
                                descriptionTextField.getText())){
                            moneyTextField.setText("");
                            System.out.println("insert 지출 성공");
                        }
                    }
                    else{
                        String date = "2024-06-04";
                        //TODO dayLogDao.insertIncomeDayLog 이함수도 expense 함수처럼 바꿔두기
                        if(dayLogDao.insertIncomeDayLog(
                                userDto.getId(),
                                date, moneyTextField.getText(),
                                descriptionTextField.getText())){
                            moneyTextField.setText("");
                            System.out.println("insert 수입 성공");
                        }
                    }
                }
            }
        });

        //TODO 테이블 선택해서 삭제하는거 만들기
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int  selectRow = resultTable.getSelectedRow();
                if(selectRow != -1) {
                    DayLogDao dayLogDao = new DayLogDao();
                    ArrayList<DayLogDto> datas = dayLogDao.getDayLogArrayList();
                    DayLogDto toDelete = datas.get(selectRow);
                    dayLogDao.deleteDayLog(toDelete);
                    resultTable.setModel(new MoneyLogTableModel(userDto.getId()));
                }
            }
        });
    }
}
