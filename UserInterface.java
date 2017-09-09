import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.stream.XMLStreamException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * The Main window class.
 * @autor yuriymos
 * @version 1.0
 * This class creates the main window of the application.
 */
public class UserInterface extends JFrame implements ActionListener {
    // to operate with an add window
    private AddWindow addWindow;
    // for a main table of the main window
    private JTable mainTable;
    // to operate with an main table
    private DefaultTableModel tableModel;
    // reference for working with data
    private DataArray dataArray;

    /**
     * Constructor - creating a new main window
     */
    public UserInterface() {
        super("How do you spend your money?");
        this.setSize(400, 400);
        this.setResizable(false);
        // set the main window on the center
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        this.createMenu();
        this.createMainTable();

        // create a panel for four buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2)); // 2 rows and 2 columns
        // for buttons on the main window
        for(String buttonName : new String[] {"Add Note", "Delete Note", "Report", "Exit"}) {
            JButton button = new JButton(buttonName);
            panel.add(button);
            button.addActionListener(this);
        }

        // for JFrame, JWindow, JDialog BorderLayout manager is by default
        Container container = getContentPane();
        container.add(new JScrollPane(mainTable));
        container.add(panel, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    /**
     * This is a private method to create menu bar on the main window
     */
    private void createMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu fileMenuFile = new JMenu("File");
        JMenu fileMenuHelp = new JMenu("Help");

        for(String fileItem : new String[] {"Add Note", "Delete Note", "Plot Diagram", "Report", "Exit"}) {
            JMenuItem item = new JMenuItem(fileItem);
            item.setActionCommand(fileItem);
            item.addActionListener(this);
            fileMenuFile.add(item);
        }

        for(String fileItem : new String[] {"Help", "About"}) {
            JMenuItem item = new JMenuItem(fileItem);
            item.setActionCommand(fileItem);
            item.addActionListener(this);
            fileMenuHelp.add(item);
        }

        menu.add(fileMenuFile);
        menu.add(fileMenuHelp);

        this.setJMenuBar(menu);
    }

    /**
     * This is a private method to create a main table in the main window
     */
    private void createMainTable() {
        // This are titles for the main table.
        String[] columnNames = {"N-r", "Date", "Category", "Note", "Money"}; // Column names
        // This is data for the main table.
        dataArray = new DataArray();
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        // add all data from XML-file
        try {
            dataArray.readFromXMLFile();
        }
        catch (XMLStreamException ex)
        {
            ex.printStackTrace();
        }
        catch (FileNotFoundException ex)
        {
            //ex.printStackTrace();
        }

        // add all strings to the main table
        for (int index = 0; index < dataArray.howManyElementsInDataArray(); index++) {
            tableModel.addRow(dataArray.getFormatedDataString(index));
        }

        // set this table model to the main window
        mainTable = new JTable(tableModel);
        // to set up data in a cell to the center
        DefaultTableCellRenderer r = (DefaultTableCellRenderer) mainTable.getDefaultRenderer(String.class);
        r.setHorizontalAlignment(JLabel.CENTER);
        r.setVerticalAlignment(JLabel.CENTER);

        // set the first column less than 60 pixels
        mainTable.getColumnModel().getColumn(0).setMaxWidth(30);
        mainTable.getColumnModel().getColumn(1).setMinWidth(130);
        mainTable.setSelectionForeground(Color.red); // the chosen row becomes red
        mainTable.setShowHorizontalLines(false);
        mainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * This is an overloaded method
     * @param ae is an object-event
     */
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("Exit")){
            System.out.println("Exit");
            dataArray.writeToXMLFile();
            System.exit(0);

        } else if(ae.getActionCommand().equals("Add Note")){
            // create a window to add data
            addWindow = new AddWindow();
            // if the button Ok was pressed in AddWindow
            // we create new dataString-object and get data
            if (addWindow.isPressedOk()){
                try {
                    dataArray.addDataString(addWindow.sendData());
                }
                catch (ParseException pe) {
                    pe.printStackTrace();
                }
                Integer i = dataArray.howManyElementsInDataArray();
                tableModel.addRow(dataArray.getFormatedDataString(i-1));
                System.out.println(i.toString());
            }
        } else if (ae.getActionCommand().equals("Delete Note")){
            System.out.println("Delete Note");
            // this row index was chosen by an user
            int rowIndex = mainTable.getSelectedRow();
            // if rowIndex == -1 than there is no elements
            if(rowIndex != -1) {
                tableModel.removeRow(rowIndex);
                dataArray.deleteDataString(rowIndex);
            }
        } else if(ae.getActionCommand().equals("Report")){
            System.out.println("Report");
        } else if(ae.getActionCommand().equals("Plot Diagram")){
            System.out.println("Plot Diagram");
        } else if(ae.getActionCommand().equals("Help")){
            System.out.println("Help");
        } else if(ae.getActionCommand().equals("About")){
            System.out.println("About");
        }
    }
}




