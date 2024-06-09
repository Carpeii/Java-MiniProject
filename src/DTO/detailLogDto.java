package DTO;

public class DetailLogDto {
    private int id;
    private String userId;
    private int dayLogId;
    private char type;
    private int money;
    private String description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getDayLogId() {
        return dayLogId;
    }

    public void setDayLogId(int dayLogId) {
        this.dayLogId = dayLogId;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
