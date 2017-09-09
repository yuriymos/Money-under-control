import java.util.Date;
/**
 * Created by Y on 17.08.2017.
 *  This class needs to contain each data string
 */
public class DataString {

    // each data string contains date, category, note, money
    private Date date;
    private String category;
    private String note;
    private String money;

    // constructor without arguments
    public DataString() {

    }

    // constructor with arguments to set all data
    public DataString(Date date, String category, String note, String money) {
        this.date = date;
        this.category = category;
        this.note = note;
        this.money = money;
    }

    // these are methods to set each field of the data string
    public void setDate(Date date) { this.date = date; }
    public void setCategory(String category) { this.category = category; }
    public void setNote(String note) { this.note = note; }
    public void setMoney(String money) { this.money = money; }

    // these are methods to get each field of the data string
    public Date getDate() { return date; }
    public String getCategory() { return category; }
    public String getNote() { return note; }
    public String getMoney() { return money; }

    // this is a method to get money as a number
    public int getMoneyInt() {return Integer.parseInt(money);}
}
