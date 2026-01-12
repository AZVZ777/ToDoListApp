package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    private static final String DB_URL = "jdbc:sqlite:tasks.db";

    public TaskDAO() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String createTable = """
                CREATE TABLE IF NOT EXISTS tasks (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    description TEXT,
                    due_date TEXT,
                    priority TEXT,
                    completed INTEGER DEFAULT 0
                )
                """;
            stmt.execute(createTable);
            System.out.println("Database and table ready.");

        } catch (SQLException e) {
            System.err.println("Database setup failed: " + e.getMessage());
        }
    }

    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (title, description, due_date, priority, completed) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getDueDate() != null ? task.getDueDate().toString() : null);
            pstmt.setString(4, task.getPriority());
            pstmt.setInt(5, task.isCompleted() ? 1 : 0);

            pstmt.executeUpdate();
            System.out.println("Task added: " + task.getTitle());

        } catch (SQLException e) {
            System.err.println("Error adding task: " + e.getMessage());
        }
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks ORDER BY due_date ASC";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String dueStr = rs.getString("due_date");
                LocalDate dueDate = dueStr != null ? LocalDate.parse(dueStr) : null;
                String priority = rs.getString("priority");
                boolean completed = rs.getInt("completed") == 1;

                tasks.add(new Task(id, title, description, dueDate, priority, completed));
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving tasks: " + e.getMessage());
        }

        return tasks;
    }

    // Test method - run this to verify
    public static void main(String[] args) {
        TaskDAO dao = new TaskDAO();

        Task sample = new Task(
                "Finish the project",
                "Complete TaskDAO and start UI",
                LocalDate.now(),
                "High"
        );
        dao.addTask(sample);

        System.out.println("\nTasks in database:");
        dao.getAllTasks().forEach(System.out::println);
    }
}
