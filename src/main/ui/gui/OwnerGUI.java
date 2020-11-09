package ui.gui;

import model.item.Drinks;
import model.item.Item;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class OwnerGUI {
    JFrame frame = new JFrame();
    JLabel label;
    JButton addButton;
    JButton removeButton;
    JComboBox chooseCategory;
    String[] menuCategories = {"Drinks", "Baked Goods"};
    JList<Item> list = new JList<>();
    JTextField nameField;
    private ArrayList<DefaultListModel<Item>> models = new ArrayList<>();

    // EFFECTS: initialise the menu
    public OwnerGUI() {
        init();
    }

    private void init() {
        frame.setTitle("Bakery Store Manager Application");
        frame.setPreferredSize(new Dimension(700, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        models.add(new DefaultListModel<>());
        models.add(new DefaultListModel<>());
        models.get(0).addElement(new Drinks("Tea1", 2.5, 25));
        models.get(1).addElement(new Drinks("Tea", 2.5, 25));

        frame.add(welcomeHeader(), BorderLayout.PAGE_START);
        frame.add(menuList());
        frame.add(itemPanel(), BorderLayout.PAGE_END);

        frame.pack();
        frame.setVisible(true);
    }

    // The implementation of Java Swing Split panel comes from:
    // https://github.com/BranislavLazic/SwingTutorials
    // EFFECTS: Initialize a split panel including the item names on the left hand side
    // and the price and quantity on the right hand side
    private JComponent menuList() {
        JPanel panel = new JPanel();
        label = new JLabel();
        JSplitPane splitPane = new JSplitPane();

        list.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    Item p = list.getSelectedValue();
                    label.setText(p.getName());
                }
            }
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

        label.setText("Hi! Please select a category to view the items.");
        label.setIcon(image);

        panel.add(label);
        panel.add(menuCategories());
        return panel;
    }

    private JComponent itemPanel() {
        JPanel panel = new JPanel();
        addButton = new JButton();
//        removeButton = new JButton();

        // Name field
        nameField = new JTextField();
        nameField.setColumns(10);

        // Price fields
        JFormattedTextField priceField = new JFormattedTextField();
        priceField.setValue(new Double(0));
        priceField.setColumns(10);

        // Quantity fields
        JFormattedTextField quantityField = new JFormattedTextField();
        quantityField.setValue(new Double(0));
        quantityField.setColumns(10);

        // Add button
        addButton.setText("Add");
        addButton.addActionListener(new AddItem());

        // Remove button
//        removeButton.setText("Remove");
//        removeButton.addActionListener(new RemoveItem());

        panel.add(nameField);
        panel.add(priceField);
        panel.add(quantityField);
        panel.add(addButton);
//        panel.add(removeButton);
        return panel;
    }

    private JComponent menuCategories() {
        JPanel panel = new JPanel();
        chooseCategory = new JComboBox(menuCategories);
        chooseCategory.addActionListener(e -> {
            int i = chooseCategory.getSelectedIndex();
            list.setModel(models.get(i));
        });
        panel.add(chooseCategory);
        return panel;
    }

    // https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDemoProject/src/components/ListDemo.java
    class AddItem implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();

            //User didn't type in a unique name...
            if (name.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                nameField.requestFocusInWindow();
                nameField.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            models.get(0).addElement(new Drinks(name, 2, 2));

            //Reset the text field.
            nameField.requestFocusInWindow();
            nameField.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }
}

