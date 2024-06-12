package model;

import DAO.CategoryDao;
import DAO.DayLogDao;
import DTO.DayLogDto;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class MoneyLogTableModel extends AbstractTableModel {
    private ArrayList<DayLogDto> datas;
    private String[] columnNames = {"date", "category","type", "money", "description"};
    private String userId;
    public MoneyLogTableModel(String userId) {
        this.userId = userId;
        DayLogDao dayLogDao = new DayLogDao();
        datas = dayLogDao.getDayLogArrayList(userId);
    }
    @Override
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return columnNames[column];
    }
    @Override
    public int getRowCount() {
        return datas.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DayLogDto dayLogDto = datas.get(rowIndex);
        CategoryDao categoryDao = new CategoryDao();

        String result = "";

        switch (columnIndex) {
            case 0:
                result = dayLogDto.getDate().toString();
                break;
            case 1:
                if(categoryDao.getCategory(dayLogDto.getCategoryId()) ==null){
                    result = "";
                }else{
                    result = String.valueOf(categoryDao.getCategory(dayLogDto.getCategoryId()).getName());
                }
                break;
            case 2:
                if (dayLogDto.getType() == 0) {
                    result = "지출";
                } else {
                    result = "수입";
                }
                break;
            case 3:
                result = Integer.toString(dayLogDto.getMoney());
                break;
            case 4:
                result = dayLogDto.getDescription();
                break;
        }
        return result;
    }
}
