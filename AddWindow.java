import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Y on 12.08.2017.
 */
public class AddWindow  extends JDialog implements ActionListener {

    private final static Integer MAX_MONEY = 10000;
    private final static Integer SINCE_YEAR = 2015;

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

    private boolean buttonOkPressed;

    /**
     * Constructor - creating a new main window
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
        this.setLocationRelativeTo(null); // set the main window on the center
        this.setVisible(true);
    }

    private void createDataForDate() {
        dayString = new String[30];
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
        //int index = 0;
        day = new JComboBox(dayString);
        gridForDate.add(day);
        month = new JComboBox(monthsString);
        gridForDate.add(month);
        year = new JComboBox(yearString);
        gridForDate.add(year);
    }

    private void createPanelForLabelsAndComboBoxes() {
        gridForBoxes = new JPanel(new GridLayout(1, 0, 5, 0));
        gridForBoxes.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createEtchedBorder(),
                        "Enter description of your spendings"),
                BorderFactory.createEmptyBorder(5, 10, 10, 10)));
        String[] someCategories = {"Food", "Clothes", "Wife", "Rent", "Adventures", "Car", "Children", "Other"};
        categoriesComboBox = new JComboBox(someCategories);
        gridForBoxes.add(categoriesComboBox);
        textFieldForNote = new JTextField("Note", JTextField.CENTER);
        gridForBoxes.add(textFieldForNote);
    }

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
     *
     * @param
     */
    public DataString sendData() {
        DataString dString = new DataString(
                day.getSelectedItem().toString(),
                month.getSelectedItem().toString(),
                year.getSelectedItem().toString(),
                categoriesComboBox.getSelectedItem().toString(),
                textFieldForNote.getText(),
                moneyComboBox.getSelectedItem().toString());
        return dString;
    }

    public boolean isPressedOk() {
        return buttonOkPressed;
    }

    /**
     * This is an overloaded method
     *
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
