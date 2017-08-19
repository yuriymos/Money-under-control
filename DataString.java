import java.util.Date;

/**
 * Created by Y on 18.08.2017.
 */
public class DataString {
    private Date data;
    private String category;
    private String note;
    private String money;

    public DataString() {

    }
    public DataString(Date data, String category, String note, String money) {
        this.data = data;
        this.category = category;
        this.note = note;
        this.money = money;
    }

    public Date getDate() { return data; }
    public String getCategory() { return category; }
    public String getNote() { return note; }
    public String getMoney() { return money; }

    public void showToConcole() {
        System.out.println(data.toString() + " " + category + " " + note + " " + money);
    }
}
