import DAO.DayLogDao;
import DTO.DayLogDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MoneyLogTableModel extends AbstractTableModel {
    private ArrayList<DayLogDto> datas;
    private String[] columnNames = {"date", "type", "money", "description"};

    public MoneyLogTableModel(String userId) {
        DayLogDao dayLogDao = new DayLogDao();
        datas = dayLogDao.getDayLogArrayList();
    }
    @Override
    public int getRowCount() {
        return datas.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DayLogDto dayLogDto = datas.get(rowIndex);

        String result = "";

        switch (columnIndex) {
            case 0:
                result = dayLogDto.getDate().toString();
                break;
            case 1:
                if (dayLogDto.getType() == 0) {
                    result = "지출";
                } else {
                    result = "수입";
                }
                break;
            case 2:
                result = Integer.toString(dayLogDto.getMoney());
                break;
            case 3:
                result = dayLogDto.getDescription();
                break;
        }
        return result;
    }
}
