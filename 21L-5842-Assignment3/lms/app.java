import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import abstractclasses.Item;
import borrower.Borrower;
import items.*;
import java.util.*;
public class app extends JFrame {
    private Library library=new Library();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Borrower> borrowers = new ArrayList<>();

    public app() {
        setTitle("Library Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel hotPicksPanel = createHotPicksPanel();
        JPanel borrowItemPanel = createBorrowItemPanel();
        JPanel addItemPanel = createAddItemPanel();
        JPanel editItemPanel = createEditItemPanel();
        JPanel deleteItemPanel = createDeleteItemPanel();
        JPanel viewAllItemsPanel = createViewAllItemsPanel();
        JPanel viewItemPanel = createViewItemPanel();
        JPanel borrowersListPanel = createBorrowersListPanel();

        tabbedPane.addTab("Hot Picks", hotPicksPanel);
        tabbedPane.addTab("Borrow an Item", borrowItemPanel);
        tabbedPane.addTab("Add Item", addItemPanel);
        tabbedPane.addTab("Edit Item", editItemPanel);
        tabbedPane.addTab("Delete Item", deleteItemPanel);
        tabbedPane.addTab("View All Items", viewAllItemsPanel);
        tabbedPane.addTab("View Item by ID", viewItemPanel);
        tabbedPane.addTab("Borrowers List", borrowersListPanel);

        // Add the tabbed pane to the frame
        add(tabbedPane);

        // Initialize your library, load items, and set up event listeners here

        setVisible(true);
    }

    private JPanel createHotPicksPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create a label for the title
        JLabel titleLabel = new JLabel("Hot Picks");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Create a text area to display hot picks
        JTextArea hotPicksTextArea = new JTextArea();
        hotPicksTextArea.setEditable(false);
        hotPicksTextArea.setWrapStyleWord(true);
        hotPicksTextArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(hotPicksTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // You can set the text in the hot picks text area here
        // For example:
        hotPicksTextArea.setText("1. Book: 'The Great Novel' - Popularity Count: 100\n" +
                                 "2. Magazine: 'Tech Weekly' - Popularity Count: 80\n" +
                                 "3. Newspaper: 'Daily News' - Popularity Count: 60");

        return panel;
    }

    private JPanel createBorrowItemPanel() {
        JPanel panel = new JPanel();

        // Add components for borrowing an item
        JLabel itemIdLabel = new JLabel("Item ID:");
        JTextField itemIdField = new JTextField(10);
        JLabel borrowerIdLabel = new JLabel("Borrower ID:");
        JTextField borrowerIdField = new JTextField(10);
        JButton borrowButton = new JButton("Borrow");
    
        panel.add(itemIdLabel);
        panel.add(itemIdField);
        panel.add(borrowerIdLabel);
        panel.add(borrowerIdField);
        panel.add(borrowButton);
    
        // Set up an event listener for the Borrow button
        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int itemId = Integer.parseInt(itemIdField.getText());
                int borrowerId = Integer.parseInt(borrowerIdField.getText());
    
                // Call the borrowItem method from your library
                library.borrowItem(itemId, borrowerId);
    
                // Update the UI or show a confirmation message
                // You can add labels or text areas to display messages
            }
        });
    
        return panel;
    }

    private JPanel createAddItemPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleTextField = new JTextField();

        JLabel authorLabel = new JLabel("Author:");
        JTextField authorTextField = new JTextField();

        JLabel yearLabel = new JLabel("Year:");
        JTextField yearTextField = new JTextField();

        JButton addButton = new JButton("Add Item");

        // Add components to the panel
        panel.add(titleLabel);
        panel.add(titleTextField);
        panel.add(authorLabel);
        panel.add(authorTextField);
        panel.add(yearLabel);
        panel.add(yearTextField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve data from the text fields
                String title = titleTextField.getText();
                String author = authorTextField.getText();
                int year = Integer.parseInt(yearTextField.getText());

                // Create a new Book instance (assuming it's a Book) and add it to the items list
                Book book = new Book(title, author, year, 0, 0);
                items.add(book);

                // Optionally, you can clear the text fields after adding the item
                titleTextField.setText("");
                authorTextField.setText("");
                yearTextField.setText("");
            }
        });

        return panel;
    }

    private JPanel createEditItemPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel itemIdLabel = new JLabel("Item ID:");
        JTextField itemIdTextField = new JTextField();

        JLabel newTitleLabel = new JLabel("New Title:");
        JTextField newTitleTextField = new JTextField();

        JButton editButton = new JButton("Edit Item");

        // Add components to the panel
        panel.add(itemIdLabel);
        panel.add(itemIdTextField);
        panel.add(newTitleLabel);
        panel.add(newTitleTextField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(editButton);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the item ID and new title from the text fields
                int itemId = Integer.parseInt(itemIdTextField.getText());
                String newTitle = newTitleTextField.getText();

                // Find the item in the 'items' list by ID and update its title
                for (Item item : items) {
                    if (item.get_id() == itemId) {
                        item.set_title(newTitle);
                        break; // Exit the loop once the item is found and updated
                    }
                }

                // Optionally, you can clear the text fields after editing the item
                itemIdTextField.setText("");
                newTitleTextField.setText("");
            }
        });

        return panel;
    }

    private JPanel createDeleteItemPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel itemIdLabel = new JLabel("Item ID:");
        JTextField itemIdTextField = new JTextField();

        JButton deleteButton = new JButton("Delete Item");

        // Add components to the panel
        panel.add(itemIdLabel);
        panel.add(itemIdTextField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the item ID from the text field
                int itemId = Integer.parseInt(itemIdTextField.getText());

                // Find and remove the item in the 'items' list by ID
                Item itemToRemove = null;
                for (Item item : items) {
                    if (item.get_id() == itemId) {
                        itemToRemove = item;
                        break; // Exit the loop once the item is found
                    }
                }

                // If the item is found, remove it from the list
                if (itemToRemove != null) {
                    items.remove(itemToRemove);
                }

                // Optionally, you can clear the text field after deleting the item
                itemIdTextField.setText("");
            }
        });

        return panel;
    }

    private JPanel createViewAllItemsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
    
        // Create a title label for the view
        JLabel titleLabel = new JLabel("View All Items");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
    
        // Create a text area to display all items
        JTextArea allItemsTextArea = new JTextArea();
        allItemsTextArea.setEditable(false);
        allItemsTextArea.setWrapStyleWord(true);
        allItemsTextArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(allItemsTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        // Populate the text area with a list of all items
        StringBuilder itemsList = new StringBuilder();
        for (Item item : items) {
            itemsList.append(item.get_title()).append("\n");
            // Add more item details as needed
        }
        allItemsTextArea.setText(itemsList.toString());
    
        return panel;
    }

    private JPanel createViewItemPanel() {
        JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2, 2));

    JLabel itemIdLabel = new JLabel("Item ID:");
    JTextField itemIdTextField = new JTextField();
    JButton viewButton = new JButton("View Item");

    // Add components to the panel
    panel.add(itemIdLabel);
    panel.add(itemIdTextField);
    panel.add(new JLabel()); // Empty label for spacing
    panel.add(viewButton);

    JTextArea itemDetailsTextArea = new JTextArea();
    itemDetailsTextArea.setEditable(false);
    itemDetailsTextArea.setWrapStyleWord(true);
    itemDetailsTextArea.setLineWrap(true);
    JScrollPane scrollPane = new JScrollPane(itemDetailsTextArea);
    panel.add(scrollPane);

    viewButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int itemId = Integer.parseInt(itemIdTextField.getText());

            // Find and display the details of the item with the specified ID
            Item itemToView = library.getItemById(itemId);
            if (itemToView != null) {
                itemDetailsTextArea.setText(itemToView.toString());
            } else {
                itemDetailsTextArea.setText("Item not found.");
            }
        }
    });

    return panel;
    }

    private JPanel createBorrowersListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
    
        // Create a title label for the list of borrowers
        JLabel titleLabel = new JLabel("Borrowers List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
    
        // Create a text area to display the list of borrowers
        JTextArea borrowersListTextArea = new JTextArea();
        borrowersListTextArea.setEditable(false);
        borrowersListTextArea.setWrapStyleWord(true);
        borrowersListTextArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(borrowersListTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);
    
        // Populate the text area with a list of borrowers and their details
        StringBuilder borrowersList = new StringBuilder();
        for (Borrower borrower : borrowers) {
            borrowersList.append(borrower.toString()).append("\n");
            // Add more borrower details as needed
        }
        borrowersListTextArea.setText(borrowersList.toString());
    
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new app();
        });
    }
}
