package view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskEntryView extends JFrame {
    private JTextField titleField;
    private JTextArea descriptionArea;
    private JTextField dueDateField;
    private JComboBox<String> priorityCombo;
    private JButton addButton;

    public TaskEntryView() {
        setTitle("Add New Task");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Title:"));
        titleField = new JTextField();
        panel.add(titleField);

        panel.add(new JLabel("Description:"));
        descriptionArea = new JTextArea(5, 20);
        JScrollPane scroll = new JScrollPane(descriptionArea);
        panel.add(scroll);

        panel.add(new JLabel("Due Date (YYYY-MM-DD):"));
        dueDateField = new JTextField(LocalDate.now().plusDays(1).format(DateTimeFormatter.ISO_DATE));
        panel.add(dueDateField);

        panel.add(new JLabel("Priority:"));
        priorityCombo = new JComboBox<>(new String[]{"Low", "Medium", "High"});
        panel.add(priorityCombo);

        addButton = new JButton("Add Task");
        panel.add(addButton);

        add(panel);
    }

    // Getters for controller to access input
    public String getTitle() { return titleField.getText().trim(); }
    public String getDescription() { return descriptionArea.getText().trim(); }
    public LocalDate getDueDate() {
        try {
            return LocalDate.parse(dueDateField.getText().trim());
        } catch (Exception e) {
            return null;
        }
    }
    public String getPriority() { return (String) priorityCombo.getSelectedItem(); }

    // Button listener for controller
    public void setAddButtonListener(java.awt.event.ActionListener listener) {
        addButton.addActionListener(listener);
    }

    // Show success/error message
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void clearForm() {
        titleField.setText("");
        descriptionArea.setText("");
        dueDateField.setText(LocalDate.now().plusDays(1).format(DateTimeFormatter.ISO_DATE));
        priorityCombo.setSelectedIndex(0);
    }
}