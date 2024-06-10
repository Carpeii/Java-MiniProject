import DAO.CategoryDao;
import DAO.DayLogDao;
import DTO.CategoryDto;
import DTO.DayLogDto;
import DTO.UserDto;
import util.DialogClosedListener;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CategoryDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameTextField;
    private JButton addButton;
    private JLabel resultLabel;
    private JPanel mainPanel;
    private JComboBox categoryComboBox;
    private JButton deleteButton;
    private JLabel deleteResultLabel;

    private DialogClosedListener listener;
    private UserDto userDto;
    public CategoryDialog(UserDto userDto, DialogClosedListener listener) {

        this.userDto = userDto;
        setContentPane(mainPanel);
        setBounds(12,10,560,500);
        setResizable(false);

        this.listener = listener;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        resultLabel.setText(" ");
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!nameTextField.getText().isEmpty()) {
                    CategoryDao categoryDao = new CategoryDao();
                    if (categoryDao.insertCategories(userDto.getId(), nameTextField.getText())) {
                        resultLabel.setText(nameTextField.getText() + "를 카테고리에 추가했습니다");
                        nameTextField.setText("");
                    } else {
                        resultLabel.setText("실패");
                    }
                }else{
                    resultLabel.setText("카테고리 입력해주세요");
                }
            }
        });

        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CategoryDao categoryDao = new CategoryDao();
                DayLogDao dayLogDao = new DayLogDao();
                System.out.println(categoryComboBox.getSelectedIndex());
                CategoryDto to = categoryDao.getCategory(userDto.getId(), categoryComboBox.getSelectedItem().toString());

                if( dayLogDao.changeCategoryNull(to)){
                    if( categoryDao.deleteCategory(to)){
                        refreshCategoryComboBox();
                    }
                }
            }
        });

        refreshCategoryComboBox();
    }


    public void refreshCategoryComboBox(){
        categoryComboBox.removeAllItems();

        CategoryDao categoryDao = new CategoryDao();
        ArrayList<CategoryDto> categories = categoryDao.getCategories(userDto.getId());
        for (CategoryDto category : categories) {
            categoryComboBox.addItem(category.getName());
        }
    }

    @Override
    public void dispose(){
        super.dispose();
        if (listener != null) {
            listener.dialogClosed();
        }
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}