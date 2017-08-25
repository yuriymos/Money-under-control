import java.util.Date;

/**
 * Created by Y on 18.08.2017.
 */
public class DataString {
    private Date date;
    private String category;
    private String note;
    private String money;

    public DataString() {

    }
    public DataString(Date date, String category, String note, String money) {
        this.date = date;
        this.category = category;
        this.note = note;
        this.money = money;
    }

    public void setDate(Date date) { this.date = date; }
    public void setCategory(String category) { this.category = category; }
    public void setNote(String note) { this.note = note; }
    public void setMoney(String money) { this.money = money; }

    public Date getDate() { return date; }
    public String getCategory() { return category; }
    public String getNote() { return note; }
    public String getMoney() { return money; }

}
