import controller.TaskEntryController;
import controller.TaskListController;
import model.TaskDAO;
import view.TaskEntryView;
import view.TaskListView;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        TaskDAO dao = new TaskDAO();

        // Simple launcher menu
        String[] options = {"Add Task Window", "Task List Dashboard"};
        int choice = JOptionPane.showOptionDialog(null, "Choose interface:", "ToDo App",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            TaskEntryView entryView = new TaskEntryView();
            new TaskEntryController(entryView, dao);
            entryView.setVisible(true);
        } else if (choice == 1) {
            TaskListView listView = new TaskListView();
            new TaskListController(listView, dao);
            listView.setVisible(true);
        }
    }
}