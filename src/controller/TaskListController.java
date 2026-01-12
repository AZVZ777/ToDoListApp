package controller;

import model.TaskDAO;
import view.TaskListView;

public class TaskListController {
    private TaskListView view;
    private TaskDAO dao;

    public TaskListController(TaskListView view, TaskDAO dao) {
        this.view = view;
        this.dao = dao;

        // Load tasks on startup
        refreshTasks();

        // Refresh button
        this.view.setRefreshListener(e -> refreshTasks());
    }

    private void refreshTasks() {
        view.updateTaskList(dao.getAllTasks());
    }

    public void showView() {
        view.setVisible(true);
    }
}