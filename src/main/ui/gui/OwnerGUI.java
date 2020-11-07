package ui.gui;

import model.item.Drinks;
import model.item.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class OwnerGUI {
    JFrame frame = new JFrame();
    JList<Item> list = new JList<>();
    private ArrayList<DefaultListModel<Item>> models = new ArrayList<>();

    // EFFECTS: initialise the menu
    public OwnerGUI() {
        init();
        models.add(new DefaultListModel<>());
        models.add(new DefaultListModel<>());
        models.get(0).addElement(new Drinks("Tea1", 2.5, 25));
        models.get(1).addElement(new Drinks("Tea", 2.5, 25));
    }

    private void init() {
        frame.setTitle("Bakery Store Manager Application");
        frame.setPreferredSize(new Dimension(400, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(welcomeHeader(), BorderLayout.PAGE_START);
        frame.add(menuCategories());
        frame.add(menuList(), BorderLayout.PAGE_END);
//        frame.add(itemPanel(), BorderLayout.PAGE_END);


        frame.pack();
        frame.setVisible(true);
    }

    // The implementation of Java Swing Split panel comes from:
    // https://github.com/BranislavLazic/SwingTutorials
    // EFFECTS: Initialize a split panel including the item names on the left hand side
    // and the price and quantity on the right hand side
    private JComponent menuList() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        JSplitPane splitPane = new JSplitPane();

        list.getSelectionModel().addListSelectionListener(e -> {
            Item p = list.getSelectedValue();
            label.setText("Name: " + p.getName() + " ::: Price: "
                        + p.getPrice() + " ::: Quantity: " + p.getQuantity());
        });

        splitPane.setLeftComponent(new JScrollPane(list));
        panel.add(label);
        splitPane.setRightComponent(panel);

        return splitPane;
    }

    // EFFECTS: initialize a header including a welcome message and photo
    private JComponent welcomeHeader() {
        // Icon downloaded from https://www.flaticon.com/free-icon/coffee-cup_633652#
        ImageIcon image = new ImageIcon("data/coffee-cup.png");
        JPanel panel = new JPanel();
        JLabel label = new JLabel();

        label.setText("Welcome to Starbucks!");
        label.setIcon(image);
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);

        panel.add(label);
        return panel;
    }

    private JComponent itemPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        JButton addButton = new JButton();
        JButton removeButton = new JButton();

        // Name field
        JTextField nameField = new JTextField();
        nameField.setColumns(10);

        // Price fields
        JFormattedTextField priceField = new JFormattedTextField();
        priceField.setValue(new Double(0));
        priceField.setColumns(10);
//        priceField.addPropertyChangeListener("price", (PropertyChangeListener) this);

        // Quantity fields
        JFormattedTextField quantityField = new JFormattedTextField();
        quantityField.setValue(new Double(0));
        quantityField.setColumns(10);
//        quantityField.addPropertyChangeListener("quantity", this);

        // Add button
        addButton.setText("Add");

        // Remove button
        removeButton.setText("Remove");

        panel.add(nameField);
        panel.add(priceField);
        panel.add(quantityField);
        panel.add(addButton);
        panel.add(removeButton);
        return panel;
    }

    private JComponent menuCategories() {
        JPanel panel = new JPanel();
        String[] menuCategories = {"Drinks", "Baked Goods"};
        JComboBox chooseCategory = new JComboBox(menuCategories);
        chooseCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = chooseCategory.getSelectedIndex();
                list.setModel(models.get(i));
            }
        });
        panel.add(chooseCategory);
        return panel;
    }
}
