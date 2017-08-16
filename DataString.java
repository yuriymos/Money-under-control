import java.util.Date;

/**
 * Created by Y on 16.08.2017.
 */
public final class DataString {

    private Date date;
    private String category;
    private String note;
    private String money;

    public DataString(){
    }

    public DataString(Date date, String category, String note, String money) {
        this.date = date;
        this.category = category;
        this.note = note;
        this.money = money;
    }

    public void setData(Date date, String category, String note, String money) {
        this.date = date;
        this.category = category;
        this.note = note;
        this.money = money;
    }

    public Date getDate() {
        return this.date;
    }

    public String getCategory() {
        return this.category;
    }

    public String getNote() {
        return this.note;
    }

    public String getMoney() {
        return this.money;
    }
}
