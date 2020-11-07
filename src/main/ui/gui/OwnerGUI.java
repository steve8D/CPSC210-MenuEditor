package ui.gui;

import model.item.BakedGoods;
import model.item.Drinks;
import model.item.Item;

import javax.swing.*;
import java.awt.*;

// The implementation of Java Swing Split panel comes from:
// https://github.com/BranislavLazic/SwingTutorials
public class OwnerGUI {
    JList<Item> list = new JList<>();
    DefaultListModel<Item> model = new DefaultListModel<>();
    JFrame frame = new JFrame();

    // EFFECTS: intialise the menu
    public OwnerGUI() {
        init();
    }

    private void init() {
        frame.setTitle("Bakery Store Manager Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(welcomeHeader(), BorderLayout.PAGE_START);
        frame.add(menuList());
        frame.setVisible(true);
    }

    // EFFECTS: Initialize a split panel including the item names on the left hand side
    // and the price and quantity on the right hand side
    private JComponent menuList() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        JSplitPane splitPane = new JSplitPane();

        list.setModel(model);

        model.addElement(new BakedGoods("Tiramisu", 10, 25));
        model.addElement(new Drinks("Tea", 2.5, 25));

        list.getSelectionModel().addListSelectionListener(e -> {
            Item p = list.getSelectedValue();
            label.setText("Name: " + p.getName() + " ::: "
                        + p.getPrice() + " ::: " + p.getQuantity());
        });

        splitPane.setLeftComponent(new JScrollPane(list));
        panel.add(label);
        splitPane.setRightComponent(panel);

        return splitPane;
    }

    // EFFECTS: initialize a header including a welcome message and photo
    private JComponent welcomeHeader() {
        // Icon from https://www.flaticon.com/free-icon/coffee-cup_633652#
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
}
