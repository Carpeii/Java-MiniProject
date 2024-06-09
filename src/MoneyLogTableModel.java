import DTO.DayLogDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MoneyLogTableModel extends AbstractTableModel {
    private ArrayList<DayLogDto> datas;
    private String[] columnNames = {"date", "type", "money", "description"};

    public MoneyLogTableModel() {
        DayLogDto detailLogDao = new DayLogDto();
//        datas = detailLogDao.getDetalLogDtoArray();
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
//        DayLogDto detailLogDto = datas.get(rowIndex);
//
        String result = "";
//
//        switch (columnIndex) {
//            case 0:
//                result = "test";
//                break;
//            case 1:
//                if (detailLogDto.getType() == 0) {
//                    result = "지출";
//                } else {
//                    result = "수입";
//                }
//                break;
//            case 2:
//                result = Integer.toString(detailLogDto.getMoney());
//                break;
//            case 3:
//                result = detailLogDto.getDescription();
//                break;
//        }
        return result;
    }
}
