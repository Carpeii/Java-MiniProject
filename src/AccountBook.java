import DAO.CategoryDao;
import DAO.DayLogDao;
import DTO.CategoryDto;
import DTO.DayLogDto;
import DTO.UserDto;
import model.MoneyLogTableModel;
import util.DialogClosedListener;

import javax.swing.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AccountBook extends JFrame {

    private JPanel mainPanel;
    private JScrollPane resultScrollPane;
    private JPanel utilPanel;
    private JPanel insertPanel;
    private JPanel userInfoPanel;
    private JPanel searhPanel;
    private JButton refreshButton;
    private JTable resultTable;
    private JTextField moneyTextField;
    private JTextField descriptionTextField;
    private JButton insertButton;
    private JRadioButton expenseRadioButton;
    private JButton deleteButton;
    private JRadioButton incomeRadioButton;
    private JLabel userNameLabel;
    private JTextField dateTextField;
    private JComboBox categoryComboBox;
    private JButton settingCategoryButton;
    private JButton updateButton;

    private UserDto userDto;
    public boolean isValidDate() {
        if(dateTextField.getText().isEmpty()){
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);  // 날짜 형식을 엄격하게 체크하도록 설정합니다.
        try {
            sdf.parse((dateTextField.getText()));  // 파싱을 시도합니다.
            return true;  // 파싱이 성공하면, 유효한 날짜입니다.
        } catch (ParseException e) {
            return false;  // 파싱 중 오류가 발생하면, 유효한 날짜가 아닙니다.
        }
    }

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

        resultTable.setModel(new MoneyLogTableModel(userDto.getId()));
        buttonGroup.add(expenseRadioButton);
        buttonGroup.add(incomeRadioButton);

        expenseRadioButton.setSelected(true);
        userNameLabel.setText("로그인 정보 : "+ userDto.getName());
        refreshCategoryComboBox();


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
                if(!moneyTextField.getText().isEmpty() && isValidDate()){
                    DayLogDao dayLogDao = new DayLogDao();
                    if(expenseRadioButton.isSelected()) {
                        String date = dateTextField.getText();

                        if(dayLogDao.insertExpenseDayLog(userDto.getId(), date, moneyTextField.getText(), descriptionTextField.getText(), getCategoryComboBoxItem())){
                            moneyTextField.setText("");
                            descriptionTextField.setText("");
                            System.out.println("insert 지출 성공");
                            resultTable.setModel(new MoneyLogTableModel(userDto.getId()));
                        }
                    }
                    else{
                        String date = dateTextField.getText();
                        if(dayLogDao.insertIncomeDayLog(userDto.getId(), date, moneyTextField.getText(), descriptionTextField.getText(), getCategoryComboBoxItem())){
                            moneyTextField.setText("");
                            descriptionTextField.setText("");
                            System.out.println("insert 수입 성공");
                            resultTable.setModel(new MoneyLogTableModel(userDto.getId()));
                        }
                    }
                }
            }
        });

        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int  selectRow = resultTable.getSelectedRow();
                if(selectRow != -1) {
                    DayLogDao dayLogDao = new DayLogDao();
                    ArrayList<DayLogDto> datas = dayLogDao.getDayLogArrayList(userDto.getId());
                    DayLogDto toDelete = datas.get(selectRow);
                    dayLogDao.deleteDayLog(toDelete);
                    resultTable.setModel(new MoneyLogTableModel(userDto.getId()));
                }
            }
        });
        moneyTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        dateTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == '-' || c == '/')) {
                    e.consume();  // 입력을 무시합니다
                }
                if(dateTextField.getText().length() >= 8) {
                    e.consume();
                }
            }
        });
        settingCategoryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CategoryDialog insertCategoryDialog = new CategoryDialog(userDto, new DialogClosedListener() {
                    @Override
                    public void dialogClosed() {
                        refreshCategoryComboBox();
                    }
                });
                insertCategoryDialog.setVisible(true);
            }
        });

        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int  selectRow = resultTable.getSelectedRow();
                if(selectRow != -1) {

                    DayLogDao dayLogDao = new DayLogDao();
                    ArrayList<DayLogDto> datas = dayLogDao.getDayLogArrayList(userDto.getId());
                    DayLogDto toModify = datas.get(selectRow);
                    UpdateDayLogDialog categoryDialog = new UpdateDayLogDialog(toModify, new DialogClosedListener() {
                        @Override
                        public void dialogClosed() {
                            resultTable.setModel(new MoneyLogTableModel(userDto.getId()));
                        }
                    });
                    categoryDialog.setVisible(true);
                }
            }
        });
    }

    public void refreshCategoryComboBox(){
        categoryComboBox.removeAllItems();

        CategoryDao categoryDao = new CategoryDao();
        ArrayList<CategoryDto> categories = categoryDao.getCategories(userDto.getId());
        categoryComboBox.addItem("없음");
        for (CategoryDto category : categories) {
            categoryComboBox.addItem(category.getName());
        }
    }

    public CategoryDto getCategoryComboBoxItem(){
        if(categoryComboBox.getSelectedIndex() == 0){
            return null;
        }else{
            CategoryDao categoryDao = new CategoryDao();
            CategoryDto category = categoryDao.getCategory(userDto.getId(), categoryComboBox.getSelectedItem().toString());
            return category;
        }
    }
}
