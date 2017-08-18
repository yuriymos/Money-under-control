import java.util.Collections;
import java.util.Vector;

/**
 * Created by Y on 17.08.2017.
 */
public class DataArray {

    private Vector vector;

    public DataArray() {
        vector = new Vector< DataString >();
    }

    public void addDataString(DataString ds) {
        vector.addElement(ds);
    }

    public void parseXML() {
        // read file if it is
        // else vector = null;
    }

    public Integer howManyElementsInDataArray() {
        return vector.size();
    }

    public String [] [] getDataToMainTable() {
       String [] [] temp;
        if(vector.size() == 0) {
            temp  = new String[][] {{ "1", "no data", "no data", "no data", "no data"}};
        }
        else {
            temp = new String[][]{{"1", "01/08/2017", "Food", "Note", "3434"},
                    {"2", "03/08/2017", "Adventures", "Note", "434"},
                    {"300", "22/09/2017", "Rent", "Note", "1034"}};
        }
        return temp;

    }

}
