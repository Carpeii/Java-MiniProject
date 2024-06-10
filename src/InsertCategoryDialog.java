import DAO.CategoryDao;
import DTO.UserDto;

import javax.swing.*;
import java.awt.event.*;

public class InsertCategoryDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameTextField;
    private JButton addButton;
    private JLabel resultLabel;

    private DialogClosedListener listener;

    public InsertCategoryDialog(UserDto userDto, DialogClosedListener listener) {
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
                }
            }
        });
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