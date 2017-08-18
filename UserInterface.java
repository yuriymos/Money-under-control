import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Main window class <b>abc</b> è <b>abc</b>.
 * @autor yuriymos
 * @version 1.0
 */

public class UserInterface extends JFrame implements ActionListener {

    private AddWindow addWindow;

    private DataString dataString;

    private DataArray dataArray;

    /**
     * Constructor - creating a new main window
     */
    public UserInterface() {
        super("How do you spend your money?");
        this.setSize(400, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null); // set the main window on the center
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.createMenu();

        //dataArray.parseXML();

        JTable mainTable = this.createMainTable();

        // create a panel for four buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2)); // 2 rows and 2 column
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

        menu.add(fileMenuFile);
        menu.add(fileMenuHelp);
        this.setJMenuBar(menu);
    }

    /**
     * This is a private method to create a main table in the main window
     */
    private JTable createMainTable() {
        // This are titles for the main table.
        String[] columnNames = {"Number", "Date", "Category", "Note", "Money"}; // Column names
        // This is data for the main table.

        dataArray = new DataArray();

        // get String [] [] from dataArray-object to fill the main table
        String [] [] dataForMainTable = dataArray.getDataToMainTable();

        JTable mainTable = new JTable(dataForMainTable, columnNames);
        // to set up data in a cell to the center
        DefaultTableCellRenderer r = (DefaultTableCellRenderer) mainTable.getDefaultRenderer(String.class);
        r.setHorizontalAlignment(JLabel.CENTER);
        r.setVerticalAlignment(JLabel.CENTER);
        // set the first column less than 60 pixels
        mainTable.getColumnModel().getColumn(0).setMaxWidth(60);
        mainTable.setRowSelectionAllowed(true);
        return mainTable;
    }

    /**
     * This is an overloaded method
     * @param ae is an object-event
     */
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        else if(ae.getActionCommand().equals("Add Note")){
            // create a window to add data
            addWindow = new AddWindow();
            // if the button Ok was pressed in AddWindow
            // we create new dataString-object and get data
            if (addWindow.isPressedOk()){
                dataString = new DataString();
                dataString = addWindow.sendData();
                dataArray.addDataString(dataString);
                dataArray.getDataToMainTable();
                System.out.println(dataArray.howManyElementsInDataArray().toString());
            }
        }
        else if(ae.getActionCommand().equals("Delete Note")){
            System.out.println("Delete Note");
        }
        else if(ae.getActionCommand().equals("Report")){
            System.out.println("Report");
        }
    }
}




