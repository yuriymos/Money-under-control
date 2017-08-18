import java.util.ArrayList;

/**
 * Created by Y on 17.08.2017.
 */

public class DataArray {

    private ArrayList arrayList = new ArrayList< String [] >();;

    public DataArray() {
    }

    private void sortByDate() {

    }

    public String[] getFormatDataString(Integer index) {
        String [] unformatedResult = new String[6]; // magic number :)
        unformatedResult = (String [])arrayList.get(index);
        String [] formatedResult = new String[5]; // magic number :)
        Integer temp = index + 1;
        formatedResult[0] = temp.toString();
        formatedResult[1] = unformatedResult[0] + " " + unformatedResult[1] + " " +
                unformatedResult[2];
        formatedResult[2] = unformatedResult[3];
        formatedResult[3] = unformatedResult[4];
        formatedResult[4] = unformatedResult[5];
        return formatedResult;
    }

    public void addDataString(String[] ds) {
        arrayList.add(ds);
    }

    public void readFromXMLFile() {
        this.addDataString(new String[]{"25", "November", "2017", "Food", "none", "1597"});
        this.addDataString(new String[]{"25", "May", "2017", "Car", "none", "5097"});
        this.addDataString(new String[]{"5", "February", "2015", "Car", "none", "97"});
        this.addDataString(new String[]{"7", "June", "2016", "Car", "none", "5097"});
        this.addDataString(new String[]{"25", "May", "2018", "Other", "none", "557"});
        this.sortByDate();
    }

    public Integer howManyElementsInDataArray() {
        return arrayList.size();
    }

}
