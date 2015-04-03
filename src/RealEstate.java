
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//all the gui
public class RealEstate extends JFrame {

    public static JLabel emptyLabel, messageLabel, lotNumberLabel, firstNameLabel, lastNameLabel, priceLabel, squareFeetLabel, bedRoomsLabel;
    public static JTextField lotNumberText, firstNameText, lastNameText, priceText, squareFeetText, bedRoomsText;
    public static JButton resetButton, nextButton, addButton, deleteButton, clearButton, findButton;
    public static int houseIndex = 0;


    public static HouseFile file = new HouseFile();
    public static SortedList list = new SortedList();

    public RealEstate() {
        super("title goes here");
        setLayout(new GridBagLayout());
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints x = new GridBagConstraints();

        //message label
        messageLabel = new JLabel("");
        x.gridx = 0;
        x.gridy = 0;
        x.gridwidth = 2;
        x.weighty = 1.0;
        x.weightx = 0.1;
        x.fill = GridBagConstraints.BOTH;
        x.insets = new Insets(2, 5, 2, 5);
        add(messageLabel, x);
        //end of message label

        //lot number
        lotNumberLabel = new JLabel("Lot Number");
        lotNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        x = createGbc(0, 1);
        add(lotNumberLabel, x);

        lotNumberText = new JTextField("");
        x = createGbc(1, 1);
        add(lotNumberText, x);
        //end of lot number

        //first name
        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        x = createGbc(0, 2);
        add(firstNameLabel, x);

        firstNameText = new JTextField("");
        x = createGbc(1, 2);
        add(firstNameText, x);
        //end of first name

        //last name
        lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        x = createGbc(0, 3);
        add(lastNameLabel, x);

        lastNameText = new JTextField("");
        x = createGbc(1, 3);
        add(lastNameText, x);
        //end of last name

        //price
        priceLabel = new JLabel("Price");
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        x = createGbc(0, 4);
        add(priceLabel, x);

        priceText = new JTextField("");
        x = createGbc(1, 4);
        add(priceText, x);
        //end of price

        //square feet
        squareFeetLabel = new JLabel("Square Feet");
        squareFeetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        x = createGbc(0, 5);
        add(squareFeetLabel, x);

        squareFeetText = new JTextField("");
        x = createGbc(1, 5);
        add(squareFeetText, x);
        //end of square feet

        //rooms
        bedRoomsLabel = new JLabel("Number of Rooms");
        bedRoomsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        x = createGbc(0, 6);
        add(bedRoomsLabel, x);

        bedRoomsText = new JTextField("");
        x = createGbc(1, 6);
        add(bedRoomsText, x);
        //end of rooms

        //_____________________Buttons__________________________//
        //reset
        resetButton = new JButton("Reset");
        x = createGbc(0, 7);
        add(resetButton, x);
        //end of reset

        //next
        nextButton = new JButton("Next");
        x = createGbc(1, 7);
        add(nextButton, x);
        //end of next

        //add
        addButton = new JButton("Add");
        x = createGbc(0, 8);
        add(addButton, x);
        //end of add

        //delete
        deleteButton = new JButton("Delete");
        x = createGbc(1, 8);
        add(deleteButton, x);
        //end of delete

        //clear
        clearButton = new JButton("Clear");
        x = createGbc(0, 9);
        add(clearButton, x);
        //end of clear

        //find
        findButton = new JButton("Find");
        x = createGbc(1, 9);
        add(findButton, x);
        //end of find

        //----------------end of buttons---------------------//
        //initialization
        //initialization
        //actions for buttons
        Listener listener = new Listener();
        resetButton.addActionListener(listener);
        nextButton.addActionListener(listener);
        addButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
        clearButton.addActionListener(listener);
        findButton.addActionListener(listener);

//        displayHouse();
        //end of actions for buttons
        setVisible(true);

    }

    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == resetButton) {
                houseIndex = 0;
                if (list.getSize() > 0) {
                    displayHouse(list.getItem(0));
                    messageLabel.setText("");
                } else {
                    messageLabel.setText("No houses to display");
                    clearForm();
                }
                System.out.println("Reset Button");

            } else if (e.getSource() == nextButton) {
                houseIndex++;
                if (houseIndex < list.getSize()) {
                    displayHouse(list.getItem(houseIndex));
                    messageLabel.setText("");
                } else {
                    messageLabel.setText("No more houses to display");
                }
                System.out.println("Next Button");

            } else if (e.getSource() == addButton) {

                if (list.findItem(getCurrentHouse()) == false) {
                    list.insertItem(getCurrentHouse());
                    messageLabel.setText("");
                } else {
                    messageLabel.setText("House already exists with this lot number");
                }

                System.out.println("Add Button");

            } else if (e.getSource() == deleteButton) {

                if (list.findItem(getCurrentHouse()) == true) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure u want to delete the \n house with the lot number : " + getCurrentHouse().lotNumber() + " ?",
                            "Are you sure?",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        list.removeItem(getCurrentHouse());

                        houseIndex = 0;
                        if (list.getSize() > 0) {
                            displayHouse(list.getItem(0));
                            messageLabel.setText("");
                        } else {
                            messageLabel.setText("No houses to display");
                            clearForm();
                        }
                    }

                } else {
                    messageLabel.setText("No houses found with this lot number");
                }

                System.out.println("Delete Button");
            } else if (e.getSource() == clearButton) {

                System.out.println("Clear Button");
                houseIndex = 0;
                if (list.getSize() > 0) {
                    displayHouse(list.getItem(0));
                    messageLabel.setText("");
                } else {
                    messageLabel.setText("No houses to display");
                    clearForm();
                }
                clearForm();

            } else if (e.getSource() == findButton) {
                
                String findLotNumber = JOptionPane.showInputDialog("Enter lot number");
                
                if (findLotNumber != null && findLotNumber.length() > 0) {
                    ListHouse newHouse = new ListHouse(Integer.parseInt(findLotNumber), "", "", 0, 0, 0);
                    if(list.findItem(newHouse) == true){
                        displayHouse(list.getItem(newHouse));
                    }else{
                        messageLabel.setText("No house information found with the lot number : "+findLotNumber);
                    }
                }
                System.out.println("Find Button");

            }
        }
    }

    private static ListHouse getCurrentHouse() {
        int lotNumber = Integer.parseInt(lotNumberText.getText());
        String firstName = firstNameText.getText();
        String lastName = lastNameText.getText();
        int price = Integer.parseInt(priceText.getText());
        int squareFeet = Integer.parseInt(squareFeetText.getText());
        int bedRooms = Integer.parseInt(bedRoomsText.getText());

        ListHouse house = new ListHouse(lotNumber, firstName, lastName, price, squareFeet, bedRooms);

        return house;
    }

    private static void displayHouse(ListHouse house) {
        lotNumberText.setText(String.valueOf(house.lotNumber()));
        firstNameText.setText(String.valueOf(house.firstName()));
        lastNameText.setText(String.valueOf(house.lastName()));
        priceText.setText(String.valueOf(house.price()));
        squareFeetText.setText(String.valueOf(house.squareFeet()));
        bedRoomsText.setText(String.valueOf(house.bedRooms()));
    }

    private static void clearForm() {
//        messageLabel.setText("");
        lotNumberText.setText("");
        firstNameText.setText("");
        lastNameText.setText("");
        priceText.setText("");
        squareFeetText.setText("");
        bedRoomsText.setText("");
    }

    private GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 1.0;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 5, 2, 5);
        return gbc;
    }

    public static void main(String[] args) {
        RealEstate jf = new RealEstate();

        messageLabel.setText("");

        file.resetToRead();
        while (file.hasNextHouse()) {
            list.insertItem(file.getNextHouse());
        }
        System.out.println("list size = " + list.getSize());
        if (list.getSize() > 0) {
            displayHouse(list.getItem(0));
        } else {
            messageLabel.setText("No houses to display");
            clearForm();
        }

        jf.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                file.addHouses(list);
            }
        });

    }
}
