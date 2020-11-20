package ui.gui;

import model.MyMenu;
import model.item.BakedGoods;
import model.item.Drinks;
import model.item.Item;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class OwnerGUI {
    // starting the categories of the menu
    private MyMenu menu;
    private static final String DIRECTORY = "./data/NewMenu.json";
    private final String[] menuCategories = {"Drinks", "Baked Goods"};
    private final JList<Item> list = new JList<>();
    private final ArrayList<DefaultListModel<Item>> models = new ArrayList<>();

    // components for adding item
    private JTextField nameField;
    private JFormattedTextField priceField;
    private JFormattedTextField quantityField;
    private JComboBox category;

    public OwnerGUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Bakery Store Manager Application");
        frame.setPreferredSize(new Dimension(700, 400));
        for (int i = 0; i < menuCategories.length; i++) {
            models.add(new DefaultListModel<>());
        }
        loadMenu();

        frame.add(welcomeHeader(), BorderLayout.PAGE_START);
        frame.add(menuList());
        frame.add(itemPanel(), BorderLayout.PAGE_END);

        // https://github.students.cs.ubc.ca/CPSC210/SampleMidtermRepos/tree/master/JDrawing
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: Output a split panel including the item names on the left hand side
    // and display item's attributes on the right hand side that changes upon user selection.
    private JComponent menuList() {
        JSplitPane splitPane = new JSplitPane();

        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    Item p = list.getSelectedValue();
                    label.setText(p.getName() + ". Price: " + p.getPrice() + ". Quantity: " + p.getQuantity());
                }
            }
        });
        panel.add(label);

        splitPane.setLeftComponent(new JScrollPane(list));
        splitPane.setRightComponent(panel);

        return splitPane;
    }

    // EFFECTS: outputs a coffee logo and welcome message
    private JComponent welcomeHeader() {
        // Icon downloaded from https://www.flaticon.com/free-icon/coffee-cup_633652#
        ImageIcon image = new ImageIcon("data/coffee-cup.png");
        JPanel panel = new JPanel();
        JLabel label = new JLabel();

        label.setText("Hi! Please select a category to view the items.");
        label.setIcon(image);

        panel.add(label);
        panel.add(menuCategories());
        return panel;
    }

    // EFFECTS: create a panel that takes user input and add an item to the menu.
    private JComponent itemPanel() {
        JPanel panel = new JPanel();
        JButton addButton = new JButton();

        // Create labels
        JLabel nameLabel = new JLabel("Name");
        JLabel priceLabel = new JLabel("Price");
        JLabel quantityLabel = new JLabel("Quantity");

        userFields();

        // Add button
        addButton.setText("Add");
        addButton.addActionListener(new AddItem());

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(category);
        panel.add(addButton);
        return panel;
    }

    // EFFECTS: create user input fields to determine the attributes of a new item to the menu.
    private void userFields() {
        // Name field
        nameField = new JTextField();
        nameField.setColumns(10);

        // Price fields
        priceField = new JFormattedTextField();
        priceField.setValue(new Double(0));
        priceField.setColumns(10);

        // Quantity fields
        quantityField = new JFormattedTextField();
        quantityField.setValue(new Integer(0));
        quantityField.setColumns(10);

        // Category field
        category = new JComboBox(menuCategories);
    }

    // MODIFIES: this
    // EFFECTS: produces a drop-down list of categories in the menu that
    // accepts user selection and affect outputs of items in that category.
    private JComponent menuCategories() {
        JPanel panel = new JPanel();
        JComboBox chooseCategory = new JComboBox(menuCategories);
        chooseCategory.addActionListener(e -> {
            int i = chooseCategory.getSelectedIndex();
            list.setModel(models.get(i));
        });
        panel.add(chooseCategory);
        return panel;
    }

    // MODIFIES: MyMenu, this
    // EFFECTS: loads menu from file if file exists,
    //      otherwise, throw IOException and create a new empty menu.
    private void loadMenu() {
        try {
            JsonReader jsonReader = new JsonReader(DIRECTORY);
            menu = jsonReader.read();
            for (Item i : menu.getItems()) {
                if (i instanceof Drinks) {
                    models.get(0).addElement(new Drinks(i.getName(), i.getPrice(), i.getQuantity()));
                } else {
                    models.get(1).addElement(new BakedGoods(i.getName(), i.getPrice(), i.getQuantity()));
                }
            }
        } catch (IOException e) {
            menu = new MyMenu();
        }
    }

    // https://github.students.cs.ubc.ca/CPSC210/SampleMidtermRepos/tree/master/JDrawing
    // EFFECTS: creates a pop up that asks user option to save the file.
    public void exit() {
        Toolkit.getDefaultToolkit().beep();
        int userSays = JOptionPane.showConfirmDialog(null,"save before exiting ?",
                "Exits from the program",JOptionPane.YES_NO_CANCEL_OPTION);
        if (userSays == JOptionPane.CANCEL_OPTION) {
            return;
        } else if (userSays == JOptionPane.YES_OPTION) {
            saveMenu();
        }
        System.exit(0);
    }

    // The method saveMenu() is based on the following Github code
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: save the menu to file
    private void saveMenu() {
        try {
            JsonWriter writer = new JsonWriter(DIRECTORY);
            writer.write(menu);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save to: " + DIRECTORY);
        }
    }

    /*
    The implementation of adding an item to the list using ActionListener is based on the following documentation
    https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
    */
    // EFFECTS: priceField > 0
    //          quantityField > 0
    // MODIFIES: this
    // EFFECTS: add item to the menu from user inputs.
    private class AddItem implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            double price = (double) priceField.getValue();
            int quantity = (int) quantityField.getValue();
            int categorySelected = category.getSelectedIndex();
            
            if (name.equals("") || price <= 0 || quantity <= 0) {
                Toolkit.getDefaultToolkit().beep();
                return;
            }

            switch (categorySelected) {
                case 0:
                    models.get(0).addElement(new Drinks(name, price, quantity));
                    menu.addItem(new Drinks(name, price, quantity));
                    break;
                default:
                    models.get(1).addElement(new BakedGoods(name, price, quantity));
                    menu.addItem(new BakedGoods(name, price, quantity));
                    break;
            }

            //Reset the text field.
            nameField.setText("");
            priceField.setValue(0);
            quantityField.setValue(0);
        }
    }
}

