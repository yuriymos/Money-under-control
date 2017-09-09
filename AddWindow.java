import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Y on 12.08.2017.
 * This class create a window to add information about spending money
 */
public class AddWindow  extends JDialog implements ActionListener {
    // value for set up maximum amount of money you could enter
    private final static Integer MAX_MONEY = 20000;
    // value for year
    private final static Integer SINCE_YEAR = 2000;

    // arrays with data to form any date
    private String[] dayString;
    private String[] monthsString;
    private String[] yearString;

    // data to create all panels
    private JPanel gridForDate;
    private JPanel gridForBoxes;
    private JPanel gridForMoney;
    private JPanel gridForButtons;

    // to get data from comboBoxes and textField
    private JComboBox day, month, year;
    private JComboBox moneyComboBox, categoriesComboBox;
    private JTextField textFieldForNote;

    // for information about the button OK, it was pressed or not.
    private boolean buttonOkPressed;

    /**
     * Constructor - creating a new window.
     */
    public AddWindow() {
        this.setResizable(false);
        this.setModal(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // create panel for date
        createPanelForDate();
        // create panel for labels and comboBoxes
        createPanelForLabelsAndComboBoxes();
        // create panel for components witch needs to enter money
        createPanelForMoney();
        // create panel for buttons Ok and Cancel
        createPanelForButtonsOkCancel();
        // create panel to group all panels
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flow.add(gridForButtons);
        // create panel to group all panels
        JPanel grid = new JPanel(new GridLayout(2, 1, 5, 0));
        grid.add(gridForBoxes);
        grid.add(gridForMoney);
        // add all panels to the frame
        Container container = getContentPane();
        container.add(gridForDate, BorderLayout.NORTH);
        container.add(grid, BorderLayout.CENTER);
        container.add(flow, BorderLayout.SOUTH);
        // set an optimal size of the window
        this.pack();
        // set the main window on the center
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * The createDataForDate method is private, it needs to create data for date
     */
    private void createDataForDate() {
        dayString = new String[31]; // where 31 is maximum days possible in a month
        for (Integer value = 1; value <= dayString.length; value++) {
            dayString[value - 1] = value.toString();
        }
        monthsString = new String[]{
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December"};
        yearString = new String[100]; // how many years? of course 100 years
        for (Integer index = SINCE_YEAR; index < yearString.length + SINCE_YEAR; index++) {
            yearString[index - SINCE_YEAR] = index.toString();
        }
    }

    /**
     * The createPanelForDate method is private,
     * it needs to add data about date
     */
    private void createPanelForDate() {
        gridForDate = new JPanel(new GridLayout(2, 3, 5, 0));
        gridForDate.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(),
                        "Enter date"),
                BorderFactory.createEmptyBorder(0, 10, 10, 10)));
        createDataForDate();
        for (String labelTitle : new String[]{"Day:", "Month:", "Year:"}) {
            gridForDate.add(new JLabel(labelTitle, JLabel.CENTER));
        }
        day = new JComboBox(dayString);
        month = new JComboBox(monthsString);
        year = new JComboBox(yearString);

        this.setCurrentDate();

        gridForDate.add(day);
        gridForDate.add(month);
        gridForDate.add(year);
    }

    /**
     * The setCurrentDate method is private,
     * it needs to set current data to the add window
     */
    private void setCurrentDate() {
        Date currentDate = new Date(System.currentTimeMillis());

        SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

        int currentDay = Integer.parseInt(sdfDay.format(currentDate));
        String currentMonth = sdfMonth.format(currentDate);
        int currentYear = Integer.parseInt(sdfYear.format(currentDate));

        day.setSelectedItem((String.valueOf(currentDay)));
        month.setSelectedItem(currentMonth);
        year.setSelectedItem((String.valueOf(currentYear)));
    }

    /**
     * The createPanelForLabelsAndComboBoxes method is private,
     * it needs to set current data to the add window
     */
    private void createPanelForLabelsAndComboBoxes() {
        gridForBoxes = new JPanel(new GridLayout(1, 0, 5, 0));
        gridForBoxes.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(),
                        "Enter description of your spendings"),
                BorderFactory.createEmptyBorder(5, 10, 10, 10)));
        // these are possible categories, you could add more
        String[] someCategories = {"Food", "Clothes", "Wife", "Rent",
                "Adventures", "Car", "Children", "Public transport", "Other"};
        categoriesComboBox = new JComboBox(someCategories);
        gridForBoxes.add(categoriesComboBox);
        textFieldForNote = new JTextField("note", JTextField.CENTER);
        gridForBoxes.add(textFieldForNote);
    }

    /**
     * The createPanelForMoney method is private,
     * it needs to add data about your money spending
     * You should chose one number from combo box
     * from 0 to MAX_MONEY
     */
    private void createPanelForMoney() {
        gridForMoney = new JPanel(new GridLayout(1, 2, 5, 0));
        gridForMoney.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(),
                        "How much money have you spent?"),
                BorderFactory.createEmptyBorder(5, 10, 10, 10)));
        String[] moneyString = new String[MAX_MONEY];
        for (Integer value = 0; value < moneyString.length; value++) {
            moneyString[value] = value.toString();
        }
        moneyComboBox = new JComboBox(moneyString);
        gridForMoney.add(moneyComboBox);
    }

    /**
     * The createPanelForButtonsOkCancel method is private,
     * it needs to add buttons to the add window
     */
    private void createPanelForButtonsOkCancel() {
        gridForButtons = new JPanel(new GridLayout(1, 2, 5, 0));
        for (String buttonName : new String[]{"Ok", "Cancel"}) {
            JButton button = new JButton(buttonName);
            gridForButtons.add(button);
            button.addActionListener(this); // ActionListener
        }
    }

    /**
     * This method needs to send entered data
     * @return String []
     */
    public DataString sendData() throws ParseException {
        //String string = "January 2, 2010";
        String string = new String(month.getSelectedItem().toString() + " " +
                day.getSelectedItem().toString() + ", " + year.getSelectedItem().toString());
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date date = format.parse(string);
        DataString dString = new DataString(date,
                categoriesComboBox.getSelectedItem().toString(),
                textFieldForNote.getText(),
                moneyComboBox.getSelectedItem().toString());
        return dString;
    }

    /**
     * This method needs to send information about button "Ok",
     * @return boolean This returns the button "Ok" was pressed or not.
     */
    public boolean isPressedOk() {
        return buttonOkPressed;
    }

    /**
     * This is an overloaded method
     * @param ae is an object-event
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Ok")) {
            buttonOkPressed = true;
            this.dispose();
        } else {
            buttonOkPressed = false;
            this.dispose();
        }
    }
}
