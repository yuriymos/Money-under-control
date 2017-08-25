import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Y on 17.08.2017.
 */

public class DataArray {

    private ArrayList arrayList = new ArrayList< DataString >();

    public DataArray() {

    }

    private void sortByDate() {
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

    public void readFromXMLFile() throws XMLStreamException, ParseException {
/*
        this.addDataString(new DataString(new Date(System.currentTimeMillis()), "Food", "none", "1597"));
        this.addDataString(new DataString(new Date(2503065604631L), "Clothes", "none", "507"));
        this.addDataString(new DataString(new Date(150306604631L), "Wife", "none", "7597"));
        this.addDataString(new DataString(new Date(1103065604631L), "Adventures", "none", "607"));
        this.addDataString(new DataString(new Date(1203065604631L), "Car", "none", "6778"));
        this.addDataString(new DataString(new Date(System.currentTimeMillis()), "Food", "none", "1597"));
        this.addDataString(new DataString(new Date(1203065604631L), "Car", "none", "6778"));
        this.addDataString(new DataString(new Date(1215065674631L), "Other", "Flat", "12345"));
        this.addDataString(new DataString(new Date(System.currentTimeMillis()), "Food", "none", "1597"));
        this.addDataString(new DataString(new Date(2993065604631L), "Clothes", "none", "7777"));
*/
        String xmlFile = "money.xml";
        int index = 0;
        DataString temp = null;

        try {
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(xmlFile, new FileInputStream(xmlFile));
            while (xmlr.hasNext()) {
                xmlr.next();
                if(index%4 == 0) {index = 0;}

                if (xmlr.hasText() && xmlr.getText().trim().length() > 0) {
                    //System.out.println(String.valueOf(index));
                    // get each item from a XML-file
                    //System.out.println(xmlr.getText());
                    if(index == 0) {
                        // get date
                        temp = new DataString();
                        DateFormat df = new SimpleDateFormat("dd MMMM yyyy",  Locale.ENGLISH);
                        Date date = new Date();
                        try {
                            date = df.parse(xmlr.getText());
                        }
                        catch (ParseException pe)
                        {
                            System.out.println(pe.toString());
                        }
                        //System.out.println("Create DataString");
                        temp.setDate(date);
                        //System.out.println(xmlr.getText());
                    } else if(index == 1) {
                        // get category
                        temp.setCategory(xmlr.getText());
                        //System.out.println(xmlr.getText());
                    } else if(index == 2) {
                        // get note
                        temp.setNote(xmlr.getText());
                        //System.out.println(xmlr.getText());
                    } else if (index == 3) {
                        // get money
                        temp.setMoney(xmlr.getText());
                        //System.out.println(xmlr.getText());
                        //System.out.println("Send to...");
                        this.addDataString(temp);
                    }
                    index++;
                }
            }
    } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }

        //this.sortByDate();
    }

    public void writeToXMLFile() throws IOException {
        this.sortByDate();
        BufferedWriter out = new BufferedWriter(new FileWriter("money.xml"));
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy",  Locale.ENGLISH);
        try {
            out.write("<?xml version = \"1.0\" encoding = \"utf-8\"?>\n");
            out.write("<data>\n"); // the top level tag
            out.write("<items number = \"" + this.howManyElementsInDataArray().toString() + "\">\n");
            // to create output XML-file
            for (int index = 0; index < arrayList.size(); index++) {
                DataString temp = (DataString)arrayList.get(index);
                /////////////////////////////////////////////////////////////////
                out.write("<item>\n <date>");
                out.write(df.format(temp.getDate()).toString());
                out.write("</date> \n<category>");
                out.write(temp.getCategory());
                out.write("</category> \n<note>");
                out.write(temp.getNote());
                out.write("</note> \n<money>");
                out.write(temp.getMoney());
                out.write("</money> \n");
                out.write("</item>\n");
                /////////////////////////////////////////////////////////////////
            }
            out.write("</items>\n");
            out.write("</data>"); // the close top level tag
            out.close();
        }
        catch (IOException ioe) {
            System.out.println(ioe);
        }
        finally {
            out.close();
        }
    }

    public Integer howManyElementsInDataArray() {
        return arrayList.size();
    }

    private class dataStringComp implements Comparator<DataString>{
        @Override
        public int compare(DataString ds1, DataString ds2) {
            return ds1.getDate().compareTo(ds2.getDate());
        }
    }
}
