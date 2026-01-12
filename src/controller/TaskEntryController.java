package controller;

import model.Task;
import model.TaskDAO;
import view.TaskEntryView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class TaskEntryController {
    private TaskEntryView view;
    private TaskDAO dao;

    public TaskEntryController(TaskEntryView view, TaskDAO dao) {
        this.view = view;
        this.dao = dao;

        // Attach listener to Add button
        this.view.setAddButtonListener(new AddTaskListener());
    }

    class AddTaskListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = view.getTitle();
            String desc = view.getDescription();
            LocalDate due = view.getDueDate();
            String priority = view.getPriority();

            if (title.isEmpty()) {
                view.showMessage("Title is required!");
                return;
            }

            Task newTask = new Task(title, desc, due, priority);
            dao.addTask(newTask);

            view.showMessage("Task added successfully!");
            view.clearForm();
        }
    }

    // Launch the window
    public void showView() {
        view.setVisible(true);
    }
}