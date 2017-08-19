import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Y on 17.08.2017.
 */

public class DataArray {

    private ArrayList arrayList = new ArrayList< DataString >();

    public DataArray() {
    }

    public void sortByDate() {
        Collections.sort(arrayList, new dataStringComp());
    }

    public String[] getFormatDataString(Integer index) {
        DataString unformatedResult = new DataString();
        unformatedResult = (DataString)arrayList.get(index);
        String [] formatedResult = new String[5]; // magic number :)
        Integer temp = index + 1;
        formatedResult[0] = temp.toString();
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy",  Locale.ENGLISH);
        formatedResult[1] =  df.format(unformatedResult.getDate()).toString();
        formatedResult[2] = unformatedResult.getCategory();
        formatedResult[3] = unformatedResult.getNote();
        formatedResult[4] = unformatedResult.getMoney();
        return formatedResult;
    }

    public void addDataString(DataString ds) {
        arrayList.add(ds);
    }

    public void deleteDataString(int index) {
        System.out.println("deleteDataString()");
        arrayList.remove(index);
    }

    public void readFromXMLFile() {
        this.addDataString(new DataString(new Date(System.currentTimeMillis()), "Food", "none", "1597"));
        this.addDataString(new DataString(new Date(2503065604631L), "Clothes", "none", "507"));
        this.addDataString(new DataString(new Date(150306604631L), "Wife", "none", "7597"));
        this.addDataString(new DataString(new Date(1103065604631L), "Adventures", "none", "607"));
        this.addDataString(new DataString(new Date(1203065604631L), "Car", "none", "6778"));
        this.sortByDate();
    }

    public Integer howManyElementsInDataArray() {
        return arrayList.size();
    }

    public void testIt() {
        for (int index = 0; index < arrayList.size(); index++) {
            DataString temp = (DataString)arrayList.get(index);
            temp.showToConcole();
        }
    }

    class dataStringComp implements Comparator<DataString>{
        @Override
        public int compare(DataString ds1, DataString ds2) {
            return ds1.getDate().compareTo(ds2.getDate());
        }
    }
}
