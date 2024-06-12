import DAO.CategoryDao;
import DAO.DayLogDao;
import DTO.CategoryDto;
import DTO.DayLogDto;
import util.DialogClosedListener;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UpdateDayLogDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel modifyPanel;
    private JTextField dateTextField;
    private JTextField moneyTextField;
    private JComboBox categoryComboBox;
    private JTextField descriptionTextField;
    private JComboBox typeComboBox;
    private DialogClosedListener listener;
    private DayLogDto dayLogDto;
    public UpdateDayLogDialog(DayLogDto dayLogDto, DialogClosedListener listener) {
        this.dayLogDto = dayLogDto;
        setContentPane(contentPane);
        setBounds(12,10,1000,100);
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

        setInfo();
    }
    @Override
    public void dispose(){
        super.dispose();
        if(listener != null){
            listener.dialogClosed();
        }
    }
    private void onOK() {
        // add your code here
        DayLogDao dayLogDao = new DayLogDao();
        CategoryDao categoryDao = new CategoryDao();


        dayLogDao.updateDayLog(
                dayLogDto,
                Date.valueOf(dateTextField.getText()),
                typeComboBox.getSelectedIndex(),
                Integer.parseInt(moneyTextField.getText()),
                descriptionTextField.getText(),
                categoryDao.getCategory(dayLogDto.getUserId(), categoryComboBox.getSelectedItem().toString()).getId()
        );

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    public void setInfo(){
        dateTextField.setText(dayLogDto.getDate().toString());
        moneyTextField.setText(String.valueOf(dayLogDto.getMoney()));
        descriptionTextField.setText(dayLogDto.getDescription());

        typeComboBox.removeAll();
        typeComboBox.addItem("지출");
        typeComboBox.addItem("수입");

        typeComboBox.setSelectedIndex(dayLogDto.getType());

        refreshCategoryComboBox();
    }
    public void refreshCategoryComboBox(){
        categoryComboBox.removeAllItems();

        CategoryDao categoryDao = new CategoryDao();
        ArrayList<CategoryDto> categories = categoryDao.getCategories(dayLogDto.getUserId());
        for (CategoryDto category : categories) {
            categoryComboBox.addItem(category.getName());
            if(category.getId() == dayLogDto.getCategoryId()){
                categoryComboBox.setSelectedItem(category.getName());
            }
        }
    }
}
