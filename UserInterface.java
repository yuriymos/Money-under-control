import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

/**
 * The Main window class <b>abc</b> � <b>abc</b>.
 * @autor yuriymos
 * @version 1.0
 */

public class UserInterface extends JFrame implements ActionListener {

    private AddWindow addWindow;

    private JTable mainTable;

    private DefaultTableModel tableModel;

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

        //dataArray.readFromXMLFile();

        this.createMainTable();

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
    private void createMainTable() {
        // This are titles for the main table.
        String[] columnNames = {"N-r", "Date", "Category", "Note", "Money"}; // Column names
        // This is data for the main table.
        dataArray = new DataArray();
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        // add all data from XML-file
        dataArray.readFromXMLFile();

        for (int index = 0; index < dataArray.howManyElementsInDataArray(); index++) {
            tableModel.addRow(dataArray.getFormatDataString(index));
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
        //return mainTable;
    }

    /**
     * This is an overloaded method
     * @param ae is an object-event
     */
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getActionCommand().equals("Exit")){
            System.exit(0);
        } else if(ae.getActionCommand().equals("Add Note")){
            // create a window to add data
            addWindow = new AddWindow();
            // if the button Ok was pressed in AddWindow
            // we create new dataString-object and get data
            if (addWindow.isPressedOk()){
                dataArray.addDataString(addWindow.sendData());
                Integer i = dataArray.howManyElementsInDataArray();
                tableModel.addRow(dataArray.getFormatDataString(i-1));
                System.out.println(i.toString());
            }
        } else if (ae.getActionCommand().equals("Delete Note")){
            System.out.println("Delete Note");
        } else if(ae.getActionCommand().equals("Report")){
            System.out.println("Report");
        }
    }
}




