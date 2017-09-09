import javax.xml.stream.*;
import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Y on 17.08.2017.
 *  This class needs to contain all data
 */
public class DataArray {
    /**
     * This is an array with all data.
     */
    private ArrayList arrayList;

    // the constructor without arguments to create an object
    public DataArray() {
        arrayList = new ArrayList< DataString >();
    }

    /**
     * The sortByDate method sorts arrayList by date, it is private
     */
    private void sortByDate() {
        Collections.sort(arrayList, new dataStringComp());
    }

    /**
     * The getFormatedDataString method sorts arrayList by date
     * @param index The argument for transferring index number of the array
     * @return the String-vector in order to set up data into the main window
     */
    public String[] getFormatedDataString(Integer index) {
        DataString unformatedResult = (DataString)arrayList.get(index);
        String [] formatedResult = new String[5]; // 5 because there are 5 columns
        // in order to number each data row
        Integer temp = index + 1;
        formatedResult[0] = temp.toString();
        // in order to set date format
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy",  Locale.ENGLISH);
        // in order to set data for each data row
        formatedResult[1] =  df.format(unformatedResult.getDate()).toString();
        formatedResult[2] = unformatedResult.getCategory();
        formatedResult[3] = unformatedResult.getNote();
        formatedResult[4] = unformatedResult.getMoney();
        return formatedResult;
    }

    /**
     * The addDataString method adds data to array
     * @param ds The argument for adding DataString-object
     */
    public void addDataString(DataString ds) {
        arrayList.add(ds);
    }

    /**
     * The addDataString method adds data to array
     * @param index The argument for removing DataString
     *              from the array with that index number
     */
    public void deleteDataString(int index) {
        arrayList.remove(index);
    }

    /**
     * The readFromXMLFile method reads data from XML-file
     * and write data to arrayList
     */
    public void readFromXMLFile() throws XMLStreamException, FileNotFoundException {
        int index = 0;
        DataString temp = null;
        FileInputStream fileInputStream = new FileInputStream("money.xml");
        XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader("money.xml", fileInputStream);
        // get data from XML-file
        while (xmlr.hasNext()) {
            xmlr.next();
            if(index%4 == 0) {index = 0;}

            if (xmlr.hasText() && xmlr.getText().trim().length() > 0) {
                // get each item from a XML-file
                if(index == 0) {
                    // get date
                    temp = new DataString();
                    // in order to set date format
                    DateFormat df = new SimpleDateFormat("dd MMMM yyyy",  Locale.ENGLISH);
                    Date date = new Date();
                    try {
                        date = df.parse(xmlr.getText());
                    }
                    catch (ParseException pe)
                    {
                        System.out.println(pe.toString());
                    }
                    // Create DataString
                    temp.setDate(date);
                } else if(index == 1) {
                    // get category
                    temp.setCategory(xmlr.getText());
                } else if(index == 2) {
                    // get note
                    temp.setNote(xmlr.getText());
                } else if (index == 3) {
                    // get money
                    temp.setMoney(xmlr.getText());
                    // Send to...
                    this.addDataString(temp);
                }
                index++;
            }
        }
    }

    /**
     * The writeToXMLFile method adds data to XML-file
     */
    public void writeToXMLFile() {
        this.sortByDate(); // to sort all by date
        // in order to set date format
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy",  Locale.ENGLISH);
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("money.xml"));
            out.write("<?xml version = \"1.0\" encoding = \"utf-8\"?>\n");
            out.write("<data>\n"); // the top level tag
            out.write("<items number = \"" + this.howManyElementsInDataArray().toString() + "\">\n");
            // to create output XML-file
            for (int index = 0; index < arrayList.size(); index++) {
                DataString temp = (DataString)arrayList.get(index);
                out.write("<item>\n<date>");
                out.write(df.format(temp.getDate()).toString());
                out.write("</date> \n<category>");
                out.write(temp.getCategory());
                out.write("</category> \n<note>");
                out.write(temp.getNote());
                out.write("</note> \n<money>");
                out.write(temp.getMoney());
                out.write("</money> \n");
                out.write("</item>\n");
            }
            out.write("</items>\n");
            out.write("</data>"); // the close top level tag
            out.close();
        }
        catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    /**
     * The howManyElementsInDataArray method returns amount of elements in the array
     * @return the Integer-object
     */
    public Integer howManyElementsInDataArray() {
        return arrayList.size();
    }

    /**
     * This private class dataStringComp needs to compare DataString-objects
     */
    private class dataStringComp implements Comparator< DataString > {
        @Override
        public int compare(DataString ds1, DataString ds2) {
            return ds1.getDate().compareTo(ds2.getDate());
        }
    }
}
