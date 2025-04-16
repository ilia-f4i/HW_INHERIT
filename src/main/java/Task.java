// Task.java
import java.util.Objects;

public abstract class Task {
    protected final int id;

    public Task(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive");
        }
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public abstract boolean matches(String query);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                '}';
    }
}