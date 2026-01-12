package view;

import model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TaskListView extends JFrame {
    private JTable taskTable;
    private DefaultTableModel tableModel;
    private JButton refreshButton;

    public TaskListView() {
        setTitle("Task Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new String[]{"ID", "Title", "Description", "Due Date", "Priority", "Completed"}, 0);
        taskTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(taskTable);

        refreshButton = new JButton("Refresh List");

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(refreshButton);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void updateTaskList(List<Task> tasks) {
        tableModel.setRowCount(0); // Clear table
        for (Task t : tasks) {
            tableModel.addRow(new Object[]{
                    t.getId(),
                    t.getTitle(),
                    t.getDescription(),
                    t.getDueDate(),
                    t.getPriority(),
                    t.isCompleted() ? "Yes" : "No"
            });
        }
    }

    public void setRefreshListener(java.awt.event.ActionListener listener) {
        refreshButton.addActionListener(listener);
    }
}